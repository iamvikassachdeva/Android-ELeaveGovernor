package com.example.sachdeva.eleavegovernor;

import android.nfc.tech.TagTechnology;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationView extends AppCompatActivity {
    TextView EmployeeName, Department, Date, LeaveCategory, LeaveType, PayStatus, Adjustments,description;
    String username, employeeId, jsonString;
    JSONObject jsonObject;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_view);
        EmployeeName = (TextView)findViewById(R.id.flNVEmployeeName);
        Department = (TextView)findViewById(R.id.flNVDepartment);
        Date = (TextView)findViewById(R.id.flNVDate);
        LeaveCategory = (TextView)findViewById(R.id.flNVLeaveCategory);
        LeaveType = (TextView)findViewById(R.id.flNVLeaveType);
        PayStatus = (TextView)findViewById(R.id.flNVPayStatus);
        description = (TextView)findViewById(R.id.flNVDescription);
        Adjustments = (TextView)findViewById(R.id.flNVAdjustments);
        Adjustments.setText("");
        username = getIntent().getExtras().getString("EmployeeName");
        jsonString = getIntent().getExtras().getString("notificationViewData");
        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("result");
            JSONObject JO = jsonArray.getJSONObject(0);
            employeeId = JO.getString("EmployeeId");
            EmployeeName.setText(username);
            Department.setText(EmployeeData.getDepartment());
            Date.setText(JO.getString("Date"));
            LeaveCategory.setText(JO.getString("LeaveCategory"));
            LeaveType.setText(JO.getString("LeaveType"));
            description.setText(JO.getString("Description"));
            PayStatus.setText(JO.getString("PayStatus"));
            if(!(JO.getString("Lecture1st").isEmpty())){
                Adjustments.setText("Lecture 1st : "+JO.getString("Lecture1st")+"\n");
            }if(!(JO.getString("Lecture2nd").isEmpty())){
                Adjustments.setText(Adjustments.getText()+"Lecture 2nd : "+JO.getString("Lecture2nd")+"\n");
            }if(!(JO.getString("Lecture3rd").isEmpty())){
                Adjustments.setText(Adjustments.getText()+"Lecture 3rd : "+JO.getString("Lecture3rd")+"\n");
            }if(!(JO.getString("Lecture4th").isEmpty())){
                Adjustments.setText(Adjustments.getText()+"Lecture 4th : "+JO.getString("Lecture4th")+"\n");
            }if(!(JO.getString("Lecture5th").isEmpty())){
                Adjustments.setText(Adjustments.getText()+"Lecture 5th : "+JO.getString("Lecture5th")+"\n");
            }if(!(JO.getString("Lecture6th").isEmpty())){
                Adjustments.setText(Adjustments.getText()+"Lecture 6th : "+JO.getString("Lecture6th")+"\n");
            }if(!(JO.getString("Lecture7th").isEmpty())){
                Adjustments.setText(Adjustments.getText()+"Lecture 7th : "+JO.getString("Lecture7th")+"\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void approveLeave(View view){
        String type = "updateLeaveRequest";
        GetDbData getDbData = new GetDbData(this);
        getDbData.execute(employeeId,type,Date.getText().toString(),"APPROVED");
    }
    public void rejectLeave(View view){
        String type = "updateLeaveRequest";
        GetDbData getDbData = new GetDbData(this);
        getDbData.execute(employeeId,type,Date.getText().toString(),"REJECTED");
    }
}
