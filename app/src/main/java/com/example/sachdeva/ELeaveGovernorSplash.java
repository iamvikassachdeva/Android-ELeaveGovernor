package com.example.sachdeva.eleavegovernor;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ELeaveGovernorSplash extends AppCompatActivity {
    ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleave_governor_splash);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    progressbar = (ProgressBar)findViewById(R.id.progressBar);
                    progressbar.setVisibility(View.VISIBLE);
                    sleep(2000);
                    Intent intent = new Intent(getApplicationContext(),WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
