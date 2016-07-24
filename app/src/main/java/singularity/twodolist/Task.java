package singularity.twodolist;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task {
    public String task_name;
    public String task_note;
    public boolean task_completed;
    //public ArrayList<Subtask> subtasks;

    Task(String task_name, String task_note, boolean task_completed) { //}, ArrayList<Subtask> subtasks){
        this.task_name = task_name;
        this.task_note = task_note;
        this.task_completed = task_completed;
        //this.subtasks = subtasks;
    }

    Task() {
        this.task_name = null;
        this.task_note = null;
        this.task_completed = false;
        //this.subtasks = null;
    }

    void set_task_name(String name)
    {
        this.task_name = name;
    }

    void set_task_note(String note)
    {
        this.task_note = note;
    }

    void set_task_completed(boolean task_completed)
    {
        this.task_completed = task_completed;
    }

    /*void set_subtasks (ArrayList<Subtask> subtasks)
    {
        this.subtasks = subtasks;
    }*/


    public String get_task_name()
    {
        return this.task_name;
    }
    public String get_task_note()
    {
        return this.task_note;
    }
    public boolean get_task_completed()
    {
        return this.task_completed;
    }
    /*public ArrayList<Subtask> get_subtasks()
    {
        return this.subtasks;
    }*/

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static ArrayList<Task> createTaskListFromJSON(JSONArray jsonArray) {
        ArrayList<Task> tasks = new ArrayList<>();


        Log.d("Task JSON", jsonArray.toString());
        int c = jsonArray.length();

        for (int i=0; i<c; ++i) {
            Task task = new Task();
            try {
                JSONObject taskObject;
                new JSONObject();
                taskObject = jsonArray.getJSONObject(i);
                task.set_task_name(taskObject.getString("name"));
                task.set_task_note(taskObject.getString("note"));
                task.set_task_completed(taskObject.getBoolean("complete"));
                //task.set_subtasks(Subtask.createSubtaskListFromJSON(taskObject.getJSONArray("subtasks").getJSONObject(0)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            tasks.add(task);
        }
        return tasks;
    }

    public static String createJSONFromTaskList(ArrayList<Task> tasks) {
        String json = null;

        for (int c=0; c<tasks.size(); ++c) {
            Task task = tasks.get(c);
            json += "{ ";
            json += "\"name\": \"" + task.get_task_name() + "\", ";
            json += "\"note\": \"" + task.get_task_note() + "\", ";
            json += "\"complete\": \"" + task.get_task_completed() + "\", ";
            /*json += "\"subtasks\": [ ";
            ArrayList<Subtask> subtasks = task.get_subtasks();
            for (int i=0; i<subtasks.size(); ++i) {
                json += Subtask.createJSONFromSubtaskList(subtasks);
            }
            json += " ]";*/
            json += " }";
            if ( c != (tasks.size() - 1) ) json +=", ";
        }
        return json;
    }
}
