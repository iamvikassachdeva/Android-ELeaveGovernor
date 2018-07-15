package com.example.sachdeva.eleavegovernor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String leaveData;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String EmpName;
    String welcomeUserName = "Welcome, ";
    TextView welcomeMsg;
    EmployeeData employeeData;
    SessionHandler sessionHandler ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leaveDb lDb = new leaveDb(HomeActivity.this);
                lDb.execute();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        welcomeMsg = (TextView)findViewById(R.id.welcomeMessage);
        leaveData = getIntent().getExtras().getString("data");
        try {
            jsonObject = new JSONObject(leaveData);
            jsonArray = jsonObject.getJSONArray("result");
            JSONObject JO =jsonArray.getJSONObject(0);
            //setting Employee Data
            employeeData = new EmployeeData(JO.getString("Employee_Id"), JO.getString("User_Name"), JO.getString("Gender"),
                                                         JO.getString("Department"), JO.getString("Email"), JO.getString("Designation"), JO.getString("Password"));
            EmpName = employeeData.getEmp_name();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        welcomeUserName += EmpName ;
        welcomeMsg.setText(welcomeUserName);
        sessionHandler = new SessionHandler(this);
        boolean isInserted = sessionHandler.insertData(employeeData.getEmp_id(), employeeData.getPassword(), "TRUE");
        if(isInserted){
            Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit").setMessage("Are You Sure?")
        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Integer deletedRows = sessionHandler.deleteData(employeeData.getEmp_id());
                if(deletedRows>0){
                    Toast.makeText(getApplicationContext(),"Logout Successfully",Toast.LENGTH_LONG).show();
                }
                finish();
            }
        }).setNegativeButton("Cancel",null).show();
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Integer deletedRows = sessionHandler.deleteData(employeeData.getEmp_id());
            if(deletedRows>0){
                Toast.makeText(getApplicationContext(),"Logout Successfully",Toast.LENGTH_LONG).show();
            }
            finish();
        }else if(id == R.id.changepassword){
            //change password activity
            Intent intent  =  new Intent(this, ChangePassword.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navProfile) {
            // Handle the View Profile action
            startActivity(new Intent(this,ViewProfile.class));
        } else if (id == R.id.navNotificationView) {
            //handles the action for Notification View
            int year_x,month_x,day_x;
            String dateView;
            final Calendar cal = Calendar.getInstance();
            year_x = cal.get(Calendar.YEAR);
            month_x = cal.get(Calendar.MONTH);
            day_x = cal.get(Calendar.DAY_OF_MONTH);
            dateView = year_x + "-" + (month_x+1) + "-" +day_x;
            //Toast.makeText(getApplicationContext(),dateView,Toast.LENGTH_LONG).show();
            String type = "NotificationList";
            GetDbData getDbData = new GetDbData(this);
            getDbData.execute(EmployeeData.getEmp_id(),type,dateView);
        }else if(id == R.id.navMarkAttendenceView){
            Intent intent = new Intent(this, MarkAttendence.class);
            startActivity(intent);
        } else if (id == R.id.nav_History) {
            //handles the action for leave history
            String type = "leaveHistory";
            GetDbData getDbData = new GetDbData(this);
            getDbData.execute(EmployeeData.getEmp_id(),type);
        } else if (id == R.id.nav_viewPendingLeaves) {
            //handles the action for leave view
            leaveDb lDb = new leaveDb(this);
            lDb.execute();
        } else if (id == R.id.nav_viewMyRequests) {
            String type = "requests_raised";
            DBTaskHandler dbTaskHandler = new DBTaskHandler(this);
            dbTaskHandler.execute(type);
        } else if (id == R.id.nav_viewRequests) {
            String type = "view_requests";
            DBTaskHandler dbTaskHandler = new DBTaskHandler(this);
            dbTaskHandler.execute(type);
        }
        //else if (id == R.id.navAttendanceView) {
//            String type = "view_attendance";
//            DBTaskHandler dbTaskHandler = new DBTaskHandler(this);
//            dbTaskHandler.execute(type);
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    // handles the casual leave action
    public void OnCasualLeaveClick(View view){
        Intent intent = new Intent(this, CasualLeave.class);
        intent.putExtra("leaveType", "Casual Leave");
        startActivity(intent);
    }
    public void OnDutyLeaveClick(View view){
        Intent intent = new Intent(this, CasualLeave.class);
        intent.putExtra("leaveType", "Duty Leave");
        startActivity(intent);
    }
    public void OnAcademicLeaveClick(View view){
        Intent intent = new Intent(this, CasualLeave.class);
        intent.putExtra("leaveType", "Academic Leave");
        startActivity(intent);
    }
    public void OnEarnedLeaveClick(View view){
        Intent intent = new Intent(this, CasualLeave.class);
        intent.putExtra("leaveType", "Earned Leave");
        startActivity(intent);
    }
    public void OnMedicalLeaveClick(View view){
        Intent intent = new Intent(this, CasualLeave.class);
        intent.putExtra("leaveType", "Medical Leave");
        startActivity(intent);
    }
}
