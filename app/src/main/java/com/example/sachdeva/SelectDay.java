package com.example.sachdeva.eleavegovernor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectDay extends AppCompatActivity {
    EditText etEmpid;
    String daySelected, departmentSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_day);
        etEmpid = (EditText)findViewById(R.id.Empid);
        Spinner select_day = (Spinner)findViewById(R.id.selectDay);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SelectDay.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.selectday));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_day.setAdapter(arrayAdapter);
        select_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                daySelected = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getBaseContext(),daySelected+ " selected",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner select_department = (Spinner)findViewById(R.id.selectDepartment);
        ArrayAdapter<String> arrayAdapterSelectDepartment = new ArrayAdapter<String>(SelectDay.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.selectdepartment));
        arrayAdapterSelectDepartment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_department.setAdapter(arrayAdapterSelectDepartment);
        select_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                departmentSelected = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(),departmentSelected+" selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void addTable(View view){
        if(etEmpid.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter Employee Id",Toast.LENGTH_LONG).show();
        }else {
            String emp_id = etEmpid.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putStringArray("addtimetable",new String[]{emp_id,daySelected,departmentSelected});
            Intent intent = new Intent(this, AddTimeTable.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
