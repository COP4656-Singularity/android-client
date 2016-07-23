package singularity.twodolist;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoosiers on 7/19/2016.
 */
public class ToDoList {
    private String list_self, list_name, list_owner;
    private int list_version;
    private List<String> list_acl = null;
    private List<Task> list_tasks = null;

    ToDoList(String list_self, int list_version, String list_name, String list_owner, List<String> list_acl, List<Task> list_tasks) {
        this.list_self = list_self;
        this.list_version = list_version;
        this.list_name = list_name;
        this.list_owner = list_owner;
        this.list_acl = list_acl;
        this.list_tasks = list_tasks;
    }

    ToDoList() {
        this.list_self = null;
        this.list_version = -1;
        this.list_name = "ToDo List Item";
        this.list_owner = null;
        this.list_acl = null;
        this.list_tasks = null;
    }

    void set_list_self(String self)
    {
        this.list_self = self;
        return;
    }

    void set_list_version(int version)
    {
        this.list_version = version;
        return;
    }

    void set_list_name(String name)
    {
        this.list_name = name;
        return;
    }

    void set_list_owner(String owner)
    {
        this.list_owner = owner;
        return;
    }

    void set_list_acl(List<String> acl)
    {
        this.list_acl = acl;
        return;
    }

    void set_list_tasks(List<Task> tasks)
    {
        this.list_tasks = tasks;
        return;
    }

    String get_list_self()
    {
        return this.list_self;
    }

    int get_list_version()
    {
        return this.list_version;
    }

    String get_list_name()
    {
        return this.list_name;
    }

    String get_list_owner()
    {
        return this.list_owner;
    }

    List<String> get_list_acl()
    {
        return this.list_acl;
    }

    List<Task> get_list_tasks()
    {
        return this.list_tasks;
    }

    public static ArrayList<ToDoList> createToDoListFromJSON(JSONObject json) {
        ArrayList<ToDoList> toDoLists = new ArrayList<ToDoList>();

        int c = 0;
        JSONArray Items = null;

        try {
            Items = json.getJSONObject("data").getJSONArray("Items");
            c = Items.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("Payload Size", Integer.toString(c));

        for (int i=1; i<=c; ++i) {
            ToDoList toDoList = new ToDoList();
            JSONObject o = new JSONObject();
            try {
                o = Items.getJSONObject(i);
                toDoList.set_list_name(o.getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            toDoLists.add(toDoList);
        }

        return toDoLists;
    }
}
