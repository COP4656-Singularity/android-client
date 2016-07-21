package com.example.hoosiers.singularitytwodolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

/**
 * Created by Hoosiers on 7/17/2016.
 */
public class newList extends AppCompatActivity{

    private Button addTaskButton;
    private EditText listNameText;
    String newListsName = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_list_screen);

        addTaskButton = (Button) findViewById(R.id.addTaskButton);
        listNameText = (EditText) findViewById(R.id.listNameText);

    }

    public void newListName (View view){
        newListsName = listNameText.getText().toString();
        //PUT newListsName;
    }

    public void addNewTask (View v) {
        if (v.getId() == R.id.addTaskButton)
        {
            Intent i = new Intent(newList.this, noteDetail.class);
            i.putExtra("list_name", newListsName);
            startActivity(new Intent(newList.this, noteDetail.class));
        }
    }

    public void addNewList (View vw){
        if (vw.getId() == R.id.addListButton)
        {
            /*send list info back to server*/
        }
    }
}
