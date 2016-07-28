package singularity.twodolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoosiers on 7/17/2016.
 */
public class NoteDetail extends AppCompatActivity{

    private EditText checkedTextView;
    private EditText editTextTaskDesc;
    private EditText editTextDate;
    private Button buttonUpdateTask;
    //private Button buttonSubList;
    private Button buttonDeleteTask;
    private List<Task> taskList;
    //private ArrayList<Subtask> subTasks;
    String id;
    JSONObject json;
    String taskName = null;
    String taskNote = null;
    String taskDate = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_detail_layout);

        checkedTextView = (EditText) findViewById(R.id.checkedTextView);
        editTextTaskDesc = (EditText) findViewById(R.id.editTextTaskDesc);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        buttonUpdateTask = (Button) findViewById(R.id.buttonUpdateTask);
        //buttonSubList = (Button) findViewById(R.id.buttonSubList);
        buttonDeleteTask = (Button) findViewById(R.id.buttonDeleteTask);
        editTextDate = (EditText) findViewById(R.id.editTextDate);


        //String list_name = getIntent().getStringExtra("list_name");
    }

    /*public void getTaskName (View v){
        if (v.getId() == R.id.checkedTextView){
            taskName = checkedTextView.getText().toString();

        }
    }

    public void getTaskDesc (View v){
        if (v.getId() == R.id.editTextTaskDesc){
            taskNote = editTextTaskDesc.getText().toString();

        }
    }

    public void getDateDone (View v){
        if (v.getId() == R.id.editTextDate){
            taskDate = editTextDate.getText().toString();

        }
    }*/

    public void addTask (View v) throws JSONException{
        if (v.getId() == R.id.buttonUpdateTask){
          {

                //create new task from user data
                taskName = checkedTextView.getText().toString();
                taskNote = editTextTaskDesc.getText().toString();
                taskDate = editTextDate.getText().toString();
                boolean completed = false;
                if (taskDate != null){
                    completed = true;
                }
                Task newTask = new Task(taskName, taskNote, completed);

                //put new task in empty list
              ArrayList new_task_list = new ArrayList<Task>();
              new_task_list.add(newTask);

              //get task list from JSONObject
              JSONArray arr = json.getJSONArray("tasks");

              //put new task in JSONObject's task list
              arr.put(Task.createJSONFromTaskList(new_task_list));

              //put updated task list in JSONObject
              json.put("tasks", arr);

              //convert JSONObject to String
              String json_string = json.toString();

              // extend the client using an anonymous class:
              TodoClient c = new TodoClient() {
                  @Override
                  protected void onPostExecute(String s) {
                      super.onPostExecute(s);
                      try {
                          json = new JSONObject(s);
                          id = json.getString("id");
                      } catch (JSONException je) {
                          Toast.makeText(NoteDetail.this, "JSONException", Toast.LENGTH_SHORT).show();
                      }
                      startActivity(new Intent(NoteDetail.this, MainScreen.class));
                  }
              };
              //Update the TodoList with string version of JSONObject
              c.updateTodo(id, json_string).execute();

              // then exit. this is async so we wait for the thread to call the onPostExecute() method
              // we defined in the anonymous class above.
            }

        }
    }

    public List<Task> getTaskList()
    {
        return taskList;
    }

   /* public void addSubTask (View v){
        if (v.getId() == R.id.buttonSubList){
            if (taskName != null){

                Intent i = new Intent(NoteDetail.this, SubList.class);
                i.putExtra("sub_list_array", subTasks);
                startActivity(new Intent(NoteDetail.this, SubList.class));

            }

        }
    }*/

    public void deleteTask (View v){
        //GET task
        //taskList.remove(taskObject);
        //PUT
    }
}
