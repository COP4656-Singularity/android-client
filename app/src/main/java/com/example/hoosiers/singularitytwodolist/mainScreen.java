package com.example.hoosiers.singularitytwodolist; /**
 * Created by Hoosiers on 7/17/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.widget.EditText;
import android.text.TextUtils;
import android.util.Log;

import com.example.hoosiers.singularitytwodolist.R;

public class mainScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button newListButton;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView.Adapter recyclerViewAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);


        newListButton = (Button) findViewById(R.id.newListButton);

        /*need to get all Lists from server in here and display them*/
    }

    public void goToNewListScreen (View v){

        /*need to add user authentication before switching to new intent*/
        if (v.getId() == R.id.newListButton) {
            startActivity(new Intent(mainScreen.this, newList.class));
        }
    }
}
