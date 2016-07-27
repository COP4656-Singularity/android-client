package singularity.twodolist;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by David on 7/24/2016.
 */
public class NewTask extends AppCompatActivity {

    EditText checkedTextView;
    EditText editTextTaskDesc;
    String id;
    JSONObject json;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_detail_layout);
        checkedTextView = (EditText) findViewById(R.id.checkedTextView);
        editTextTaskDesc = (EditText) findViewById(R.id.editTextTaskDesc);

    }



    public void createNewTask(View view) throws JSONException {

        //create new task from user data
        String taskName = checkedTextView.getText().toString();
        String taskNote = editTextTaskDesc.getText().toString();
        Task newTask = new Task(taskName, taskNote, false);

        //put new task in empty list
        ArrayList new_task_list = new ArrayList<Task>();
        new_task_list.add(newTask);

        // extend the client using an anonymous class:
        TodoClient c = new TodoClient() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try
                {
                    json = new JSONObject(s);
                    id = json.getString("id");
                }catch(JSONException je){
                    Toast.makeText(NewTask.this,"JSONException", Toast.LENGTH_SHORT).show();
                }
            }
        };
        
        //get task list from JSONObject
        JSONArray arr = json.getJSONArray("tasks");

        //put new task in JSONObject's task list
        arr.put(Task.createJSONFromTaskList(new_task_list));

        //put updated task list in JSONObject
        json.put("tasks", arr);

        //convert JSONObject to String
        String json_string = json.toString();
        //Update the TodoList with string version of JSONObject
        c.updateTodo(id, json_string).execute();

        // then exit. this is async so we wait for the thread to call the onPostExecute() method
        // we defined in the anonymous class above.
    }

}
