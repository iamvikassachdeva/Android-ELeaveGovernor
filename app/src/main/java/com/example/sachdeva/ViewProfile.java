package com.example.sachdeva.eleavegovernor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewProfile extends AppCompatActivity {
    TextView id, name, gender, department, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
            id = (TextView)findViewById(R.id.empID);
            id.setText(EmployeeData.getEmp_id());
            name =(TextView)findViewById(R.id.empName);
            name.setText(EmployeeData.getEmp_name());
            gender = (TextView)findViewById(R.id.empGender);
            gender.setText(EmployeeData.getGender());
            department = (TextView)findViewById(R.id.empDept);
            department.setText(EmployeeData.getDepartment());
            email = (TextView)findViewById(R.id.empEmail);
            email.setText(EmployeeData.getEmail());
    }
    public void clickOK(View view){
       finish();
    }
}
