package com.example.hoosiers.singularitytwodolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public EditText editTextUser;
    public EditText editTextPass;
    public Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPass = (EditText) findViewById(R.id.editTextPass);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }

    public void userLogin (View v){
        String userName = editTextUser.getText().toString();
        String passWord = editTextPass.getText().toString();
        /*need to add user authentication before switching to new intent*/
        if (v.getId() == R.id.buttonLogin) {
            if (userName != null && passWord != null){
                /*send username and password to server
                * if correct, proceed*/
                startActivity(new Intent(MainActivity.this, mainScreen.class));
            }
        }

    }
}
