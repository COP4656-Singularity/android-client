package com.example.hoosiers.singularitytwodolist;

/**
 * Created by Hoosiers on 7/19/2016.
 */
public class Subtask {

    public String subtask_name;
    public String subtask_note;
    public boolean subtask_completed;

    Subtask(String task_name, String task_note, boolean task_completed){
        this.subtask_name = task_name;
        this.subtask_note = task_note;
        this.subtask_completed = task_completed;
    }

    Subtask() {
        this.subtask_name = null;
        this.subtask_note = null;
        this.subtask_completed = false;
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
}
