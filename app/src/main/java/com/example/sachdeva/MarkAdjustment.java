package com.example.sachdeva.eleavegovernor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MarkAdjustment extends AppCompatActivity {
    String date, type, dayOfWeek, itemSelected, description, json_string;
    String LeaveData[];
    JSONObject jsonObject;
    JSONArray jsonArray;
    //TextView test;
    ListView listView;
    List<String> lecturesList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_adjustment);
        AdjustmentList.setLec1("");AdjustmentList.setLec2("");AdjustmentList.setLec3("");AdjustmentList.setLec4("");
        AdjustmentList.setLec5("");AdjustmentList.setLec6("");AdjustmentList.setLec7("");
        listView = (ListView)findViewById(R.id.listview);
        Bundle bundle = getIntent().getExtras();
        LeaveData = bundle.getStringArray("getTimeTable");
        type = LeaveData[0];
        date = LeaveData[1];
        dayOfWeek = LeaveData[2];
        itemSelected = LeaveData[3];
        description = LeaveData[4];
        json_string = LeaveData[5];
        //test =(TextView)findViewById(R.id.testing);
        //test.setText("\n"+type+" "+date+" "+dayOfWeek+" "+itemSelected+" "+description+"\n"+json_string);

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("TimeTabledata");
            final String EmpId, weekDays, lec1, lec2, lec3, lec4, lec5, lec6, lec7;
            JSONObject JO = jsonArray.getJSONObject(0);
            EmpId = JO.getString("Employee_Id");
            weekDays = JO.getString("Week_Days");
            lec1 = JO.getString("Lecture_Ist");
            lec2 = JO.getString("Lecture_2nd");
            lec3 = JO.getString("Lecture_3rd");
            lec4 = JO.getString("Lecture_4th");
            lec5 = JO.getString("Lecture_5th");
            lec6 = JO.getString("Lecture_6th");
            lec7 = JO.getString("Lecture_7th");
            if(lec1.equals("Yes")){
                lecturesList.add("Lecture_Ist");
            }
            if(lec2.equals("Yes")){
                lecturesList.add("Lecture_2nd");
            }
            if(lec3.equals("Yes")){
                lecturesList.add("Lecture_3rd");
            }
            if(lec4.equals("Yes")){
                lecturesList.add("Lecture_4th");
            }
            if(lec5.equals("Yes")){
                lecturesList.add("Lecture_5th");
            }
            if(lec6.equals("Yes")){
                lecturesList.add("Lecture_6th");
            }
            if(lec7.equals("Yes")){
                lecturesList.add("Lecture_7th");
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.lecture_list,lecturesList);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String value = (String)listView.getItemAtPosition(position);
                            //Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();
                            AdjustmentDbHandler adjustmentDbHandler = new AdjustmentDbHandler(MarkAdjustment.this);
                            adjustmentDbHandler.execute(value,EmployeeData.getDepartment(),weekDays,date);
                        }
                    }
            );
        } catch (JSONException e) {
            //test.setText(test.getText().toString()+"error");
            e.printStackTrace();
        }
    }
    public void doneMakingAdjustment(View view){
        //Toast.makeText(getApplicationContext(),AdjustmentList.getLec2()+" "+AdjustmentList.getLec3()+ " "+ AdjustmentList.getLec5()+ " "+ AdjustmentList.getLec7(),Toast.LENGTH_LONG).show();
//        Bundle bundle = new Bundle();
//        bundle.putStringArray("finalConfirmationData",new String[]{type,itemSelected,date,description});
//        Intent intent = new Intent(this, LeaveConfirmation.class);
//        intent.putExtras(bundle);
//        startActivity(intent);
        GetLeaveDb getLeaveDb = new GetLeaveDb(this);
        getLeaveDb.execute(type, itemSelected, date, description);
        finish();
        /*Bundle bundle = new Bundle();
        bundle.putStringArray("finalConfirmationData",new String[]{type,itemSelected,date,description});
        Intent intent = new Intent(this,LeaveConfirmation.class);
        intent.putExtras(bundle);
        startActivity(intent);*/
    }
}
