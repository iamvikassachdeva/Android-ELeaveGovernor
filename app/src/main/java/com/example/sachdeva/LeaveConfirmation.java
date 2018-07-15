package com.example.sachdeva.eleavegovernor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LeaveConfirmation extends AppCompatActivity {
    TextView EmpId, EmpName, leaveCategory, leaveType, date, adjustments;
    String []finalConfirmationData;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_confirmation);
        Bundle bundle = getIntent().getExtras();
        EmpId = (TextView)findViewById(R.id.flLVEmpId);
        EmpName = (TextView)findViewById(R.id.flLVEmpName);
        leaveCategory = (TextView)findViewById(R.id.flLVLeaveCategory);
        leaveType = (TextView)findViewById(R.id.flLVLeaveType);
        date = (TextView)findViewById(R.id.flLVDate);
        adjustments = (TextView)findViewById(R.id.flLVAdjustments);
        finalConfirmationData = bundle.getStringArray("finalConfirmationData");
        EmpId.setText(EmployeeData.getEmp_id());
        EmpName.setText(EmployeeData.getEmp_name());
        leaveCategory.setText(finalConfirmationData[0]);
        leaveType.setText(finalConfirmationData[1]);
        date.setText(finalConfirmationData[2]);
        description = finalConfirmationData[3];
        json_string = finalConfirmationData[4];

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("result");
            JSONObject JO = jsonArray.getJSONObject(0);
            //String emp_id = JO.getString("Employee_Id");
            LeavesData.setCl(Float.parseFloat(JO.getString("Casual_Leaves")));
            LeavesData.setMl(Float.parseFloat(JO.getString("Medical_Leaves")));
            LeavesData.setAl(Float.parseFloat(JO.getString("Academic_Leaves")));
            LeavesData.setEl(Float.parseFloat(JO.getString("Earned_Leaves")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(!(AdjustmentList.getLec1().isEmpty())){
            adjustments.setText("Lec1 : "+AdjustmentList.getLec1()+"\n");
        }if (!(AdjustmentList.getLec2().isEmpty())){
            adjustments.setText(adjustments.getText().toString()+"Lec2 : "+AdjustmentList.getLec2()+"\n");
        }if (!(AdjustmentList.getLec3().isEmpty())){
            adjustments.setText(adjustments.getText().toString()+"Lec3 : "+AdjustmentList.getLec3()+"\n");
        }if (!(AdjustmentList.getLec4().isEmpty())){
            adjustments.setText(adjustments.getText().toString()+"Lec4 : "+AdjustmentList.getLec4()+"\n");
        }if (!(AdjustmentList.getLec5().isEmpty())){
            adjustments.setText(adjustments.getText().toString()+"Lec5 : "+AdjustmentList.getLec5()+"\n");
        }if (!(AdjustmentList.getLec6().isEmpty())){
            adjustments.setText(adjustments.getText().toString()+"Lec6 : "+AdjustmentList.getLec6()+"\n");
        }if (!(AdjustmentList.getLec7().isEmpty())){
            adjustments.setText(adjustments.getText().toString()+"Lec7 : "+AdjustmentList.getLec7());
        }
    }

    public void finalSubmission(View view){
        String payStatus = "With Pay";
        if(leaveCategory.getText().toString().equals("Casual Leave")){
            if(leaveType.getText().toString().equals("Full Day") && LeavesData.getCl()<1){
                payStatus = "Without Pay";
            }else if(leaveType.getText().toString().equals("Half Day") && LeavesData.getCl()<0.5){
                payStatus = "Without Pay";
            }
        }else if(leaveCategory.getText().toString().equals("Medical Leave")){
            if(leaveType.getText().toString().equals("Full Day") && LeavesData.getCl()<1){
                payStatus = "Without Pay";
            }else if(leaveType.getText().toString().equals("Half Day") && LeavesData.getCl()<0.5){
                payStatus = "Without Pay";
            }
        }else if(leaveCategory.getText().toString().equals("Academic Leave")){
            if(leaveType.getText().toString().equals("Full Day") && LeavesData.getCl()<1){
                payStatus = "Without Pay";
            }else if(leaveType.getText().toString().equals("Half Day") && LeavesData.getCl()<0.5){
                payStatus = "Without Pay";
            }
        }else if(leaveCategory.getText().toString().equals("Earned Leave")){
            if(leaveType.getText().toString().equals("Full Day") && LeavesData.getCl()<1){
                payStatus = "Without Pay";
            }else if(leaveType.getText().toString().equals("Half Day") && LeavesData.getCl()<0.5){
                payStatus = "Without Pay";
            }
        }
        //Toast.makeText(getApplicationContext(),leaveType.getText().toString()+" "+leaveCategory.getText().toString()+" "+casual+" "+ medical+" "+academic+" "+earned,Toast.LENGTH_LONG).show();
        finalLeaveSubmissionDB finalLeaveSubmissionDB = new finalLeaveSubmissionDB(this);
        finalLeaveSubmissionDB.execute(leaveCategory.getText().toString(), leaveType.getText().toString(),
                date.getText().toString(), description, payStatus);
        //finish();
    }
}
