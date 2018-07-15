package com.example.sachdeva.eleavegovernor;
/**
 * Created by Vikas Sachdeva on 09-Oct-17.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AdjustmentMaker extends AppCompatActivity {
    String adjustmentList;
    JSONObject jsonObject;
    JSONArray jsonArray;
    TextView selectAd, Status;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String lectureNo;
    String date, weekdays;
    int length = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjustment_maker);
        radioGroup = (RadioGroup)findViewById(R.id.lectureList);
        selectAd = (TextView)findViewById(R.id.selectAdjustment);
        Status = (TextView)findViewById(R.id.textStatus);
        adjustmentList = getIntent().getExtras().getString("adjustmentList");
        lectureNo = getIntent().getExtras().getString("lectureNo");
        date = getIntent().getExtras().getString("date");
        weekdays = getIntent().getExtras().getString("weekDay");
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        try {
            jsonObject = new JSONObject(adjustmentList);
            jsonArray = jsonObject.getJSONArray("AdjustmentArray");
            String User_Name;
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject JO = jsonArray.getJSONObject(i);
                User_Name = JO.getString("User_Name");
                //Emp_id = JO.getString("Emp_Id");
                RadioButton rbn = new RadioButton(this);
                rbn.setId((i+1)+1000);
                rbn.setText(User_Name);
                rbn.setTextSize(26);
                rbn.setPadding(10,20,10,20);
                radioGroup.addView(rbn);
                length++;
            }
            if(length==0){
                Status.setText("No Adjustments Available for "+lectureNo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void doneSelectOk(View view){
        if(length==0){
            finish();
        }
        else {
            if (radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getApplicationContext(), "Select At Least One Item", Toast.LENGTH_LONG).show();
            } else {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                //Toast.makeText(getApplicationContext(), lectureNo + " " + radioButton.getText().toString(), Toast.LENGTH_LONG).show();
                if (lectureNo.equals("Lecture_Ist")) {
                    AdjustmentList.setLec1(radioButton.getText().toString());
                } else if (lectureNo.equals("Lecture_2nd")) {
                    AdjustmentList.setLec2(radioButton.getText().toString());
                } else if (lectureNo.equals("Lecture_3rd")) {
                    AdjustmentList.setLec3(radioButton.getText().toString());
                } else if (lectureNo.equals("Lecture_4th")) {
                    AdjustmentList.setLec4(radioButton.getText().toString());
                } else if (lectureNo.equals("Lecture_5th")) {
                    AdjustmentList.setLec5(radioButton.getText().toString());
                } else if (lectureNo.equals("Lecture_6th")) {
                    AdjustmentList.setLec6(radioButton.getText().toString());
                } else if (lectureNo.equals("Lecture_7th")) {
                    AdjustmentList.setLec7(radioButton.getText().toString());
                }
                String type = "pending_approval";
                DBTaskHandler dbTaskHandler = new DBTaskHandler(this);
                //Toast.makeText(getApplicationContext(),"test it",Toast.LENGTH_LONG).show();
                dbTaskHandler.execute(type, EmployeeData.getEmp_name(), radioButton.getText().toString(), lectureNo, "PENDING", date, weekdays);
                //finish();
            }
        }
    }
}
