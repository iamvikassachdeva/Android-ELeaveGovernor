package com.example.sachdeva.eleavegovernor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegularUserLogin extends AppCompatActivity {
    String response;
    EditText UserNameEt, PasswordEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_user_login);
        UserNameEt = (EditText)findViewById(R.id.editUserName);
        PasswordEt = (EditText)findViewById(R.id.editPassword);
    }
    public void OnLogin(View view){
        String username = UserNameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        if(username.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter UserName",Toast.LENGTH_LONG).show();
        } else if(password.isEmpty()){
            Toast.makeText(getApplicationContext(),"Password Field cannot be left blank", Toast.LENGTH_LONG).show();
        } else{
            Background background = new Background(this);
            background.execute(type, username, password);
        }
    }
}
