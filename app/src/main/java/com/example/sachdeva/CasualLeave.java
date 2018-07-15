package com.example.sachdeva.eleavegovernor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class CasualLeave extends AppCompatActivity {
    EditText description;
    TextView date, leaveType;
    Button btn;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;
    String dateView;
    String itemSelected;
    String dayOfWeek;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casual_leave);
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        dateView = year_x + "-" + (month_x+1) + "-" +day_x;
        dayOfWeek = day_x+"-"+(month_x+1)+"-"+year_x;
        date = (TextView) findViewById(R.id.dateviewDL);
        description = (EditText)findViewById(R.id.descriptionDL);
        leaveType = (TextView)findViewById(R.id.leaveType);
        leaveType.setText(getIntent().getExtras().getString("leaveType"));
        date.setText(dateView);
        showDatePicker();
        Spinner typeChooser = (Spinner)findViewById(R.id.typePickerDL);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CasualLeave.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.type));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeChooser.setAdapter(arrayAdapter);
        typeChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSelected = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getBaseContext(),itemSelected+ " selected",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void showDatePicker(){
        btn = (Button)findViewById(R.id.showDateDialog1);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID){
            return  new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        }
        return null;
    }
    private  DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener(){

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear;
            day_x = dayOfMonth;
            dateView = year_x + "-" + (month_x+1) + "-" + day_x;
            dayOfWeek = (dayOfMonth)+"-"+(monthOfYear+1)+"-"+year;
            //Toast.makeText(getApplicationContext(), dateView, Toast.LENGTH_LONG).show();
            date.setText(dateView);
        }
    };
    public void casualOk(View view){
        if(date.getText().toString().isEmpty()){
            ViewDialogBox.showDialogBox("Select Date for Leave",this);
            //Toast.makeText(getApplicationContext(), "Select Date for Leave",Toast.LENGTH_LONG).show();
        }
        else if(description.getText().toString().isEmpty()){
            ViewDialogBox.showDialogBox("Add Description",this);
            //Toast.makeText(getApplicationContext(), "Add Description", Toast.LENGTH_LONG).show();
        }else {
            TimeTableDb timeTableDb = new TimeTableDb(this);
            timeTableDb.execute(leaveType.getText().toString(), date.getText().toString(), itemSelected.toString(), description.getText().toString(), dayOfWeek);
            //finish();
            /*Bundle bundle =  new Bundle();
            bundle.putStringArray("applyLeave", new String[]{leaveType.getText().toString(), date.getText().toString(),itemSelected,description.getText().toString()});
            Intent intent = new Intent(this, MarkAdjustment.class);
            intent.putExtras(bundle);
            startActivity(intent);*/
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
