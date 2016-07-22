package com.example.hoosiers.singularitytwodolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {

    public EditText editTextUser;
    public EditText editTextPass;
    public Button buttonLogin;
    public TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPass = (EditText) findViewById(R.id.editTextPass);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        textViewLogin = (TextView) findViewById(R.id.textViewLogin);

    }



    public void userLogin (View v){
        String userName = editTextUser.getText().toString();
        String passWord = editTextPass.getText().toString();
        /*need to add user authentication before switching to new intent*/
        if (v.getId() == R.id.buttonLogin) {
            if (userName != null && passWord != null){
                /*send username and password to server
                * if correct, proceed*/

                // extend the client using an anonymous class:
                TodoClient c = new TodoClient() {
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);

                        // write your integration code inside this method. it can call any
                        // thing in scope within this Activity class.

                        // to convert the string s to an InputStream use the convenience method
                        // on the TodoClient class:
                        // InputSteam in = TodoClient.toInputStream(s);
                        String firstName = null;
                        String lastName = null;
                        try {
                            JSONObject json = new JSONObject(s);
                        if (json.getInt("responseCode") == 200) {
                            firstName = ((JSONObject) json.getJSONObject("data").getJSONArray("Items").get(0)).getString("firstName");
                            lastName = ((JSONObject) json.getJSONObject("data").getJSONArray("Items").get(0)).getString("lastName");
                            textViewLogin.setText("Hello " +firstName);
                            startActivity(new Intent(MainActivity.this, mainScreen.class));
                        } else {
                            textViewLogin.setText("Login Failed, Try Again");
                            editTextUser.setText("");
                            editTextPass.setText("");
                        }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // call the method you want to invoke from the api:
                c.login(userName, passWord).execute();

                // then exit. this is async so we wait for the thread to call the onPostExecute() method
                // we defined in the anonymous class above.


            }
        }

    }
}
