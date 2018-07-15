package com.example.sachdeva.eleavegovernor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class ViewRequestData extends AppCompatActivity {
    TextView AdjustedFacuty, RequestingFaculty, date, lectureno, department;
    String jsonString;
    JSONObject jsonObject;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request_data);
        AdjustedFacuty = (TextView)findViewById(R.id.flRVAssignedFaculty);
        RequestingFaculty = (TextView)findViewById(R.id.flRVRequestingFaculty);
        date = (TextView)findViewById(R.id.flRVDate);
        lectureno = (TextView)findViewById(R.id.flRVLectureNo);
        department = (TextView)findViewById(R.id.flRVDepartment);

        jsonString = getIntent().getExtras().getString("viewrequestdetails");
        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("result");
            JSONObject JO = jsonArray.getJSONObject(0);
            AdjustedFacuty.setText(JO.getString("AdjustedFaculty"));
            RequestingFaculty.setText(JO.getString("RequestingFaculty"));
            date.setText(JO.getString("Date"));
            lectureno.setText(JO.getString("LectureNo"));
            department.setText(JO.getString("Department"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void approveRequest(View view){
        String type = "updateRequest";
        DBTaskHandler dbTaskHandler = new DBTaskHandler(this);
        dbTaskHandler.execute(type,AdjustedFacuty.getText().toString(),date.getText().toString(), RequestingFaculty.getText().toString(), lectureno.getText().toString(),"APPROVED");
    }
    public void rejectRequest(View view){
        String type = "updateRequest";
        DBTaskHandler dbTaskHandler =  new DBTaskHandler(this);
        dbTaskHandler.execute(type,AdjustedFacuty.getText().toString(),date.getText().toString(), RequestingFaculty.getText().toString(), lectureno.getText().toString(),"REJECTED");
    }
}
