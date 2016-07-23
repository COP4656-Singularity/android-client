package singularity.twodolist; /**
 * Created by Hoosiers on 7/17/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button newListButton;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView.Adapter recyclerViewAdapter;

    ArrayList<ToDoList> toDoLists;
    RecyclerView rvToDoLists;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        newListButton = (Button) findViewById(R.id.newListButton);

        getList();

        // Lookup the recyclerview in activity layout
        rvToDoLists = (RecyclerView) findViewById(R.id.task_list);

    }

    private void getList() {
        TodoClient c = new TodoClient() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    JSONObject json = new JSONObject(s);
                    if (json.getInt("responseCode") == 200) {
                        Log.d("JSON: ", s);
                        // Initialize contacts
                        toDoLists = ToDoList.createToDoListFromJSON(json);
                        // Create adapter passing in the sample user data
                        ToDoListAdapter adapter = new ToDoListAdapter(getParent(), toDoLists);
                        // Attach the adapter to the recyclerview to populate items
                        rvToDoLists.setAdapter(adapter);
                        // Set layout manager to position the items
                        rvToDoLists.setLayoutManager(new LinearLayoutManager(getParent()));
                        // That's all!
                    } else {
                        Log.d("JSON: ", "error");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        c.getTodos().execute();
    }

    public void goToNewListScreen (View v){

        /*need to add user authentication before switching to new intent*/
        if (v.getId() == R.id.newListButton) {
            startActivity(new Intent(MainScreen.this, NewList.class));
        }
    }
}
