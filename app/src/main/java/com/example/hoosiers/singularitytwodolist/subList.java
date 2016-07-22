package com.example.hoosiers.singularitytwodolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hoosiers on 7/20/2016.
 */
public class subList extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_task_screen);
        String task_name = getIntent().getStringExtra("task_name");
    }

//leaving for now, will copy most from note_detail_layout java if we end up using subtasks
}
