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
 * Created by David on 7/24/2016.
 */
public class NewTask extends AppCompatActivity {

    EditText checkedTextView;
    EditText editTextTaskDesc;
    String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_detail_layout);
        checkedTextView = (EditText) findViewById(R.id.checkedTextView);
        editTextTaskDesc = (EditText) findViewById(R.id.editTextTaskDesc);

    }



    /*public void createNewTask(View view) throws JSONException {
        List<Task> taskList = new List<Task>;
        String taskName = checkedTextView.getText().toString();
        String taskNote = editTextTaskDesc.getText().toString();
        Task newTask = new Task(taskName, taskNote, false);
        taskList.add(newTask);

        // extend the client using an anonymous class:
        TodoClient c = new TodoClient() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        };
        JSONObject json = new JSONObject();
        json = ?;

        // call the method you want to invoke from the api:
        c.updateTodo(id, json).execute();

        // then exit. this is async so we wait for the thread to call the onPostExecute() method
        // we defined in the anonymous class above.
    }*/

}
