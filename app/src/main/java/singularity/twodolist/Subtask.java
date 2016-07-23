package singularity.twodolist;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Subtask {

    public String subtask_name;
    public String subtask_note;
    public boolean subtask_completed;

    Subtask(String subtask_name, String subtask_note, boolean subtask_completed){
        this.subtask_name = subtask_name;
        this.subtask_note = subtask_note;
        this.subtask_completed = subtask_completed;
    }

    Subtask() {
        this.subtask_name = null;
        this.subtask_note = null;
        this.subtask_completed = false;
    }

    void set_subtask_name(String name)
    {
        this.subtask_name = name;
    }

    void set_subtask_note(String note)
    {
        this.subtask_note = note;
    }

    void set_subtask_completed(boolean subtask_completed)
    {
        this.subtask_completed = subtask_completed;
    }

    public String get_subtask_name()
    {
        return this.subtask_name;
    }
    public String get_subtask_note()
    {
        return this.subtask_note;
    }
    public boolean get_subtask_completed()
    {
        return this.subtask_completed;
    }

    public static ArrayList<Subtask> createSubtaskListFromJSON(JSONObject json) {
        ArrayList<Subtask> subtasks = new ArrayList<>();

        int c = 0;
        JSONArray Subtasks = null;

        try {
            Subtasks = json.getJSONArray("subtasks");
            Log.d("Subtask JSON", Subtasks.toString());
            c = Subtasks.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i=0; i<c; ++i) {
            Subtask subtask = new Subtask();
            try {
                JSONObject o = Subtasks.getJSONObject(i);
                subtask.set_subtask_name(o.getString("name"));
                subtask.set_subtask_note(o.getString("note"));
                subtask.set_subtask_completed(o.getBoolean("complete"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            subtasks.add(subtask);
        }
        return subtasks;
    }
}
