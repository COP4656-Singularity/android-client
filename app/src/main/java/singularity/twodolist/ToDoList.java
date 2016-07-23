package singularity.twodolist;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    }

    void set_list_version(int version)
    {
        this.list_version = version;
    }

    void set_list_name(String name)
    {
        this.list_name = name;
    }

    void set_list_owner(String owner)
    {
        this.list_owner = owner;
    }

    void set_list_acl(List<String> acl)
    {
        this.list_acl = acl;
    }

    void set_list_tasks(List<Task> tasks)
    {
        this.list_tasks = tasks;
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
        ArrayList<ToDoList> toDoLists = new ArrayList<>();

        int c = 0;
        JSONArray Items = null;

        try {
            Items = json.getJSONObject("data").getJSONArray("Items");
            c = Items.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i=0; i<c; ++i) {
            ToDoList toDoList = new ToDoList();
            try {
                JSONObject o = Items.getJSONObject(i);
                toDoList.set_list_self(o.getString("self"));
                toDoList.set_list_version(o.getInt("version"));
                toDoList.set_list_name(o.getString("name"));
                toDoList.set_list_tasks(Task.createTaskListFromJSON(o.getJSONObject("tasks")));
                toDoList.set_list_owner(o.getString("owner"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            toDoLists.add(toDoList);
        }

        return toDoLists;
    }
}
