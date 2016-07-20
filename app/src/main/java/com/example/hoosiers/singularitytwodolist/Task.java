package com.example.hoosiers.singularitytwodolist;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoosiers on 7/19/2016.
 */
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
}
