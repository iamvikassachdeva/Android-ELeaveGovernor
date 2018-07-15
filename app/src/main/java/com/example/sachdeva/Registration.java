package com.example.sachdeva.eleavegovernor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText Emp_id, user_name, password, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Emp_id = (EditText)findViewById(R.id.etEmpId);
        user_name = (EditText)findViewById(R.id.etUserName);
        password = (EditText)findViewById(R.id.etPassword);
        email = (EditText)findViewById(R.id.etEmail);
    }
    public void register(View view){
        String empid, username, pass, em;
        if(Emp_id.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter Employee Id",Toast.LENGTH_LONG).show();
        }else if (user_name.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter User Name",Toast.LENGTH_LONG).show();
        }else if (password.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_LONG).show();
        } else if (email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Please Enter Email",Toast.LENGTH_LONG).show();
        }else{
            empid = Emp_id.getText().toString();
            username = user_name.getText().toString();
            pass = password.getText().toString();
            em = email.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putStringArray("RegisterData",new  String[]{empid,username,pass,em});
            Intent intent = new Intent(this, UserDataActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

}
