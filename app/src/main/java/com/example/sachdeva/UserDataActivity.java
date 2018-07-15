package com.example.sachdeva.eleavegovernor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class UserDataActivity extends AppCompatActivity {
    String []registerData;
    String Emp_id, username, email, password;
    RadioGroup radioGroup;
    RadioButton gender;
    String departmentSelected, designationSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        Bundle bundle = getIntent().getExtras();
        registerData = bundle.getStringArray("RegisterData");
        Emp_id = registerData[0];
        username = registerData[1];
        password = registerData[2];
        email = registerData[3];
        radioGroup = (RadioGroup)findViewById(R.id.selectGender);
        Spinner departmentChooser = (Spinner)findViewById(R.id.select_department);
        ArrayAdapter<String> arrayAdapterSelectDepartment = new ArrayAdapter<String>(UserDataActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.selectdepartment));
        arrayAdapterSelectDepartment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentChooser.setAdapter(arrayAdapterSelectDepartment);
        departmentChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                departmentSelected = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(),departmentSelected+" selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner designationChooser = (Spinner)findViewById(R.id.select_designation);
        ArrayAdapter<String> arrayAdapterSelectDesignation = new ArrayAdapter<String>(UserDataActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.selectdesignation));
        arrayAdapterSelectDesignation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        designationChooser.setAdapter(arrayAdapterSelectDesignation);
        designationChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                designationSelected = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(),departmentSelected+" selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void register(View view){
        String type = "register";
        int selectedId = radioGroup.getCheckedRadioButtonId();
        gender = (RadioButton)findViewById(selectedId);
        String selectedGender = gender.getText().toString();
        Background background = new Background(this);
        background.execute(type,Emp_id,username,selectedGender,departmentSelected,email,password, designationSelected);
        //finish();
    }
}
