package com.example.sachdeva.eleavegovernor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LeaveHistoryDetailsView extends AppCompatActivity {
    TextView emp_id, name, leave_type,leave_category, tx_date, adjustments, status,leaveStatus, description;
    String json_data;
    JSONObject jsonObject;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_history_details_view);
        emp_id = (TextView)findViewById(R.id.flLVEmpId);
        name = (TextView)findViewById(R.id.flLVEmpName);
        leave_type = (TextView)findViewById(R.id.flLVLeaveType);
        leave_category = (TextView)findViewById(R.id.flLVLeaveCategory);
        tx_date = (TextView)findViewById(R.id.flLVDate);
        adjustments = (TextView)findViewById(R.id.flLVAdjustments);
        status = (TextView)findViewById(R.id.flLVLeaveStatus);
        leaveStatus = (TextView)findViewById(R.id.flLVStatus);
        description = (TextView)findViewById(R.id.flLVDescription);
        json_data = getIntent().getExtras().getString("leavehistoryDetails");
        try {
            jsonObject = new JSONObject(json_data);
            jsonArray = jsonObject.getJSONArray("result");
            JSONObject JO = jsonArray.getJSONObject(0);
            emp_id.setText(EmployeeData.getEmp_id());
            name.setText(EmployeeData.getEmp_name());
            leave_type.setText(JO.getString("Leave_Type"));
            leave_category.setText(JO.getString("Leave_Category"));
            tx_date.setText(JO.getString("Date"));
            status.setText(JO.getString("Pay_Status"));
            leaveStatus.setText(JO.getString("Status"));
            description.setText(JO.getString("Description"));
            if(!(JO.getString("Lec_1st").isEmpty())){
                adjustments.setText("Lec 1st : "+JO.getString("Lec_1st")+"\n");
            }if(!(JO.getString("Lec_2nd").isEmpty())){
                adjustments.setText(adjustments.getText()+"Lec 2nd : "+JO.getString("Lec_2nd")+"\n");
            }if(!(JO.getString("Lec_3rd").isEmpty())){
                adjustments.setText(adjustments.getText()+"Lec 3rd : "+JO.getString("Lec_3rd")+"\n");
            }if(!(JO.getString("Lec_4th").isEmpty())){
                adjustments.setText(adjustments.getText()+"Lec 4th : "+JO.getString("Lec_4th")+"\n");
            }if(!(JO.getString("Lec_5th").isEmpty())){
                adjustments.setText(adjustments.getText()+"Lec 5th : "+JO.getString("Lec_5th")+"\n");
            }if(!(JO.getString("Lec_6th").isEmpty())){
                adjustments.setText(adjustments.getText()+"Lec 6th : "+JO.getString("Lec_6th")+"\n");
            }if(!(JO.getString("Lec_7th").isEmpty())){
                adjustments.setText(adjustments.getText()+"Lec 7th : "+JO.getString("Lec_7th")+"\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void close(View view){
        finish();
    }
}
