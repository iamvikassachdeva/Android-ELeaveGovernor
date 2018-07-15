package com.example.sachdeva.eleavegovernor;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sachdeva on 04-Oct-17.
 */

public class TimeTableDb extends AsyncTask<String, Void, String> {
    String time_table_url;
    String JSON_STRING;
    Context context;
    String type, date, dayOfWeek, itemSelected, description;
    String weekday;
    public TimeTableDb(Context ctx) {
        context = ctx;
    }
    @Override
    protected void onPreExecute() {
        time_table_url = "http://"+IPContainer.ipAddress+"/AndroidProject/timeTableData.php";
    }

    @Override
    protected void onPostExecute(String result) {
        JSON_STRING = result;
        if(JSON_STRING.isEmpty()){
            ViewDialogBox.showDialogBox("Unable to Connect to the Server",context);
            //Toast.makeText(context.getApplicationContext(), "unable to fetch data",Toast.LENGTH_LONG).show();
        }
        else{
            //Toast.makeText(context.getApplicationContext(),JSON_STRING,Toast.LENGTH_LONG).show();
            Bundle bundle = new Bundle();
            bundle.putStringArray("getTimeTable", new String[]{type, date, weekday ,itemSelected, description, JSON_STRING});
            Intent intent = new Intent(context, MarkAdjustment.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected String doInBackground(String... params) {
            type = params[0];
            date = params[1];
            itemSelected = params[2];
            description = params[3];
            dayOfWeek = params[4];

        try {
            SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date d = inFormat.parse(dayOfWeek);
            SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
            weekday = outFormat.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

            try {
                URL url =  new URL(time_table_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String post_data = URLEncoder.encode("Emp_Id","UTF-8")+"="+URLEncoder.encode(EmployeeData.getEmp_id(),"UTF-8")+"&"
                        +URLEncoder.encode("Week_Days","UTF-8")+"="+URLEncoder.encode(weekday,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String result = "";
                while ((JSON_STRING=bufferedReader.readLine())!=null){
                    result += JSON_STRING + "\n";
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }
}
