package com.example.sachdeva.eleavegovernor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewLeave extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    TextView id, cl, ml, el, al, wpl;
    public String EmpId, casual, medical, earned, academic, withoutPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_leave);
        json_string = getIntent().getExtras().getString("leaveData");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("result");

                JSONObject JO = jsonArray.getJSONObject(0);
                EmpId = JO.getString("Employee_Id");
                casual = JO.getString("Casual_Leaves");
                medical = JO.getString("Medical_Leaves");
                earned = JO.getString("Earned_Leaves");
                academic = JO.getString("Academic_Leaves");
                withoutPay = JO.getString("WithoutPay_Leaves");
            LeavesData.setEl(Float.parseFloat(earned));
            LeavesData.setAl(Float.parseFloat(academic));
            LeavesData.setCl(Float.parseFloat(casual));
            LeavesData.setMl(Float.parseFloat(medical));
            LeavesData.setWpl(Float.parseFloat(withoutPay));
            id = (TextView)findViewById(R.id.empID);
            id.setText(EmployeeData.getEmp_id());
            cl =(TextView)findViewById(R.id.empName);
            cl.setText(casual);
            ml = (TextView)findViewById(R.id.empDOB);
            ml.setText(medical);
            el = (TextView)findViewById(R.id.elVL);
            el.setText(earned);
            al = (TextView)findViewById(R.id.alVL);
            al.setText(academic);
            wpl = (TextView)findViewById(R.id.wplVL);
            wpl.setText(withoutPay);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void dismiss(View view){
        finish();
    }
}
