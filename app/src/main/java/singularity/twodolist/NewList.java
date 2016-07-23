package singularity.twodolist;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by David on 7/21/2016.
 */
public class NewList extends AppCompatActivity {

    private Button addTaskButton;
    private EditText listNameText;
    String newListsName = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_list_screen);

        addTaskButton = (Button) findViewById(R.id.addTaskButton);
        listNameText = (EditText) findViewById(R.id.listNameText);

    }



    public void createNewList(View view) throws JSONException {
        JSONObject json = new JSONObject();
        EditText mEdit = (EditText) findViewById(R.id.listNameText);
        String value = mEdit.getText().toString();
        json.put("name", value);

        // extend the client using an anonymous class:
        TodoClient c = new TodoClient() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        };

        // call the method you want to invoke from the api:
        c.createTodo(json.toString()).execute();

        // then exit. this is async so we wait for the thread to call the onPostExecute() method
        // we defined in the anonymous class above.
    }

}
