package com.example.sachdeva.eleavegovernor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    EditText UserNameEt, PasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserNameEt = (EditText)findViewById(R.id.editUserName);
        PasswordEt = (EditText)findViewById(R.id.editPassword);
    }

    public void OnLogin(View view){
        String username = UserNameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        if(username.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter UserName",Toast.LENGTH_LONG).show();
        }else if (password.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_LONG).show();
        }else{
            if(username.equals("admin")&& password.equals("admin")){
                startActivity(new Intent(this, AdminHomeActivity.class));
            }else{
                Toast.makeText(getApplicationContext(),"Invalid UserName or Password",Toast.LENGTH_LONG).show();
            }
        }

        /*String type = "Adminlogin";
        Background background = new Background(this);
        background.execute(type, username, password);*/
    }

    public void registerHere(View view) {
        startActivity(new Intent(this, Registration.class));
    }
}