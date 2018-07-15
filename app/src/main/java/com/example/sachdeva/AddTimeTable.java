package com.example.sachdeva.eleavegovernor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Vikas Sachdeva on 09-Oct-17.
 */
public class AddTimeTable extends AppCompatActivity {
    private CheckBox l1,l2,l3,l4,l5,l6,l7;
    String lec1 = "", lec2 = "", lec3 = "", lec4 = "", lec5 = "", lec6 = "", lec7 = "";
    String []addTimeTableData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time_table);
        l1 = (CheckBox)findViewById(R.id.lecture1);
        l2 = (CheckBox)findViewById(R.id.lecture2);
        l3 = (CheckBox)findViewById(R.id.lecture3);
        l4 = (CheckBox)findViewById(R.id.lecture4);
        l5 = (CheckBox)findViewById(R.id.lecture5);
        l6 = (CheckBox)findViewById(R.id.lecture6);
        l7 = (CheckBox)findViewById(R.id.lecture7);
        Bundle bundle = getIntent().getExtras();
        addTimeTableData = bundle.getStringArray("addtimetable");
    }
    public void lec1Checked(View view){
        //Toast.makeText(getApplicationContext(),"lec1",Toast.LENGTH_LONG).show();
        lec1 = "Yes";
    }public void lec2Checked(View view){
        //Toast.makeText(getApplicationContext(),"lec2",Toast.LENGTH_LONG).show();
        lec2 = "Yes";
    }public void lec3Checked(View view){
        //Toast.makeText(getApplicationContext(),"lec2",Toast.LENGTH_LONG).show();
        lec3 = "Yes";
    }public void lec4Checked(View view){
        //Toast.makeText(getApplicationContext(),"lec2",Toast.LENGTH_LONG).show();
        lec4 = "Yes";
    }public void lec5Checked(View view){
        //Toast.makeText(getApplicationContext(),"lec2",Toast.LENGTH_LONG).show();
        lec5 = "Yes";
    }public void lec6Checked(View view){
        //Toast.makeText(getApplicationContext(),"lec2",Toast.LENGTH_LONG).show();
        lec6 = "Yes";
    }public void lec7Checked(View view){
        //Toast.makeText(getApplicationContext(),"lec2",Toast.LENGTH_LONG).show();
        lec7 = "Yes";
    }
    public void onSubmit(View view) {
        //Toast.makeText(getApplicationContext(),emp_id,Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),"testing",Toast.LENGTH_LONG).show();
        AddTimeTableDb addTimeTableDb = new AddTimeTableDb(this);
        addTimeTableDb.execute(addTimeTableData[0],addTimeTableData[1],addTimeTableData[2], lec1,lec2,lec3,lec4,lec5,lec6,lec7);
        finish();
        /*if (AddTimeTableDb.JSON_STRING.equals("1")){

            finish();
        }else if (AddTimeTableDb.JSON_STRING.equals("2")){
            Toast.makeText(getApplicationContext(),"Record Inserted Successfully",Toast.LENGTH_LONG).show();
            finish();
        }*/
    }
}
