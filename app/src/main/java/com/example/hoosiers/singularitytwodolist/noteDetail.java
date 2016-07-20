package com.example.hoosiers.singularitytwodolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoosiers on 7/17/2016.
 */
public class noteDetail extends AppCompatActivity{

    private CheckedTextView checkedTextView;
    private EditText editTextTaskDesc;
    private EditText editTextDate;
    private Button buttonUpdateTask;
    private Button buttonSubList;
    private Button buttonDeleteTask;
    private List<Task> taskList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_detail_layout);

        String list_name = getIntent().getStringExtra("list_name");
    }

    public void addTask (View v,View vw){
        String taskName = checkedTextView.getText().toString();
        String taskNote = editTextTaskDesc.getText().toString();
        Boolean taskCompleted = false;
        ArrayList<Subtask> subTasks = new ArrayList<Subtask>();
        Task taskObject = new Task(taskName, taskNote, taskCompleted, subTasks);
        taskList.add(taskObject);
    }

    public void deleteTask (View v){
        //GET task
        //taskList.remove(taskObject);
        //PUT
    }
}
