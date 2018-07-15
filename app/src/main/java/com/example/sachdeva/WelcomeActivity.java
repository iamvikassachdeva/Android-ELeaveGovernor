package com.example.sachdeva.eleavegovernor;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {
    SessionHandler sessionHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionHandler = new SessionHandler(this);
        Cursor result = sessionHandler.getData();
        if(result.getCount()==0){
            //Do Nothing
            setContentView(R.layout.activity_welcome);
        }else{
            while(result.moveToNext()) {
                //ViewDialogBox.showDialogBox("This is a test dialog box "+ result.getString(0)+" "+result.getString(1),this);
                String type = "login";
                Background background = new Background(this);
                background.execute(type, result.getString(0), result.getString(1));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_welcome);
    }

    public void adminLogin(View view){
        startActivity(new Intent(this,MainActivity.class));
    }
    public void regularUserLogin(View view){
        startActivity(new Intent(this, RegularUserLogin.class));
    }
}