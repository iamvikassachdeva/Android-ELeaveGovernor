package com.example.sachdeva.eleavegovernor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    String Emp_id;
    EditText oldpass, newpass, newpass1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldpass = (EditText)findViewById(R.id.oldPassword);
        newpass = (EditText)findViewById(R.id.newPassword1);
        newpass1 = (EditText)findViewById(R.id.newPassword2);
        Emp_id = EmployeeData.getEmp_id();
    }

    public void changePassword(View view){
        Toast.makeText(getApplicationContext(),"this is a test",Toast.LENGTH_LONG).show();
        if(oldpass.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter Old Password",Toast.LENGTH_LONG).show();
        }else if (newpass.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter New Password",Toast.LENGTH_LONG).show();
        }else if (newpass1.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter Re-enter New Password",Toast.LENGTH_LONG).show();
        } else{
            if(!newpass1.getText().toString().equals(newpass.getText().toString())){
                Toast.makeText(getApplicationContext(),"Passwords does not Match",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"test1",Toast.LENGTH_LONG).show();
                String oldPass, newPass1, type = "change_password";
                oldPass = oldpass.getText().toString();
                newPass1 = newpass.getText().toString();
                Background background = new Background(this);
                background.execute(type,Emp_id, oldPass, newPass1);
                oldpass.setText("");
                newpass.setText("");
                newpass1.setText("");
                //finish();
            }
        }
    }
}
