package com.example.sachdeva.eleavegovernor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AttendenceStatus extends AppCompatActivity {
    TextView date, ExceedTimeStatus;
    String currentDateTime[];
    int day, month,year, hour,minutes;
    int per_min=0, per_hr=9;
    int diff_min=0;
    int exceeded_time;
    String dateview, time;
    float cl;
    String jsondata;
    String status="";
    String attendenceType;
    JSONArray jsonArray;
    JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_status);
        date = (TextView)findViewById(R.id.dateTimeView);
        //ExceedTimeStatus = (TextView)findViewById(R.id.exceedTimeStatus);
        Bundle bundle = getIntent().getExtras();
        currentDateTime = bundle.getStringArray("dateData");
        hour =Integer.parseInt(currentDateTime[0]);
        minutes = Integer.parseInt(currentDateTime[1]);
        day = Integer.parseInt(currentDateTime[2]);
        month = Integer.parseInt(currentDateTime[3]);
        year = Integer.parseInt(currentDateTime[4]);
        jsondata = currentDateTime[5];
        //ViewDialogBox.showDialogBox(jsondata,this);
        dateview = year+"-"+month+"-"+day;
        time = hour+":"+minutes+":00";
        if(hour<13){
            attendenceType = "Login";
        }else{
            attendenceType = "Logout";
        }
        //date.setText("\nat "+hour+":"+minutes);
        try {
            jsonObject = new JSONObject(jsondata);
            jsonArray = jsonObject.getJSONArray("result");
            JSONObject Jo = jsonArray.getJSONObject(0);
            exceeded_time = Integer.parseInt(Jo.getString("ExceedTime"));
            cl = Float.parseFloat(Jo.getString("Casual_Leaves"));
        } catch (JSONException e) {
            ViewDialogBox.showDialogBox("This is a TEst",this);
            e.printStackTrace();
        }
        //Toast.makeText(getApplicationContext(),String.valueOf(cl)+" "+String.valueOf(exceeded_time),Toast.LENGTH_LONG).show();
        if(day>=1&&day<=31){
            if(day==1){
                exceeded_time=0;
            }
            if((hour>per_hr)||(minutes>30 && hour==per_hr)){
                cl -= 0.5;
                status = "Your Half Leave will be Deducted for today";
            }else if (minutes>per_min && hour==per_hr) {
                diff_min = minutes-per_min;
                exceeded_time += diff_min;
            }
            if(exceeded_time>80){
                cl-=0.5;
                exceeded_time = exceeded_time-80;
                status = "Your Half Leave will be Deducted \n& Your Exceed Time is "+exceeded_time;
            }
        }
        //Toast.makeText(getApplicationContext(),String.valueOf(cl)+" "+String.valueOf(exceeded_time),Toast.LENGTH_LONG).show();
        date.setText(status);
    }
    public void updateExceedTime(View view){
        //Toast.makeText(getApplicationContext(),"inside",Toast.LENGTH_LONG).show();
        String type = "update";
        AttendenceDBHandler attendenceDBHandler = new AttendenceDBHandler(this);
        attendenceDBHandler.execute(type,String.valueOf(exceeded_time),String.valueOf(cl),time,dateview,attendenceType);
        //finish();
    }
}
