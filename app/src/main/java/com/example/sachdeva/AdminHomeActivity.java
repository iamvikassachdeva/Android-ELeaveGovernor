package com.example.sachdeva.eleavegovernor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminHomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }
    public void registerHere(View view){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit").setMessage("Are You Sure?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("Cancel",null).show();
    }

    public void addTimeTable(View view){
        startActivity(new Intent(this,SelectDay.class));
    }
    /*public void updateTimeTable(View view){
        startActivity(new Intent(this,SelectDay.class));
    }*/
}
