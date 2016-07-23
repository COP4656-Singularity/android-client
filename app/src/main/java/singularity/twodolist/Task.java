package singularity.twodolist;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Task {
    public String task_name;
    public String task_note;
    public boolean task_completed;
    public ArrayList<Subtask> subtasks;

    Task(String task_name, String task_note, boolean task_completed, ArrayList<Subtask> subtasks){
        this.task_name = task_name;
        this.task_note = task_note;
        this.task_completed = task_completed;
        this.subtasks = subtasks;
    }

    Task() {
        this.task_name = null;
        this.task_note = null;
        this.task_completed = false;
        this.subtasks = null;
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

    void set_subtasks (ArrayList<Subtask> subtasks)
    {
        this.subtasks = subtasks;
    }


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
    public List<Subtask> get_subtasks()
    {
        return this.subtasks;
    }

    public static ArrayList<Task> createTaskListFromJSON(JSONObject json) {
        ArrayList<Task> tasks = new ArrayList<>();

        int c = 0;
        JSONArray Tasks = null;

        try {
            Tasks = json.getJSONArray("tasks");
            c = Tasks.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i=0; i<c; ++i) {
            Task task = new Task();
            try {
                JSONObject o = Tasks.getJSONObject(i);
                task.set_task_name(o.getString("name"));
                task.set_task_note(o.getString("note"));
                task.set_task_completed(o.getBoolean("complete"));
                task.set_subtasks(Subtask.createSubtaskListFromJSON(o.getJSONObject("subtasks")));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            tasks.add(task);
        }
        return tasks;
    }
}
