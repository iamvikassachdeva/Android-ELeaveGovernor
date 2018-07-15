package com.example.sachdeva.eleavegovernor;

import android.content.Context;
import android.os.AsyncTask;
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

/**
 * Created by Vikas Sachdeva on 16-Oct-17.
 */

public class AddTimeTableDb extends AsyncTask<String,Void,String> {
    String emp_id, dayOfWeek, departmentSelected, lec1, lec2, lec3,lec4, lec5, lec6, lec7;
    Context context;
    String time_table_url;
    String JSON_STRING;
    public AddTimeTableDb(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        emp_id = params[0];
        dayOfWeek = params[1];
        departmentSelected = params[2];
        lec1 = params[3];
        lec2 = params[4];
        lec3 = params[5];
        lec4 = params[6];
        lec5 = params[7];
        lec6 = params[8];
        lec7 = params[9];
        try {
            URL url = new URL(time_table_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            String post_data = URLEncoder.encode("Emp_Id","UTF-8")+"="+URLEncoder.encode(emp_id,"UTF-8")+"&"
                    +URLEncoder.encode("Department","UTF-8")+"="+URLEncoder.encode(departmentSelected,"UTF-8")+"&"
                    +URLEncoder.encode("Week_Days","UTF-8")+"="+URLEncoder.encode(dayOfWeek,"UTF-8")+"&"
                    +URLEncoder.encode("Lecture_Ist","UTF-8")+"="+URLEncoder.encode(lec1,"UTF-8")+"&"
                    +URLEncoder.encode("Lecture_2nd","UTF-8")+"="+URLEncoder.encode(lec2,"UTF-8")+"&"
                    +URLEncoder.encode("Lecture_3rd","UTF-8")+"="+URLEncoder.encode(lec3,"UTF-8")+"&"
                    +URLEncoder.encode("Lecture_4th","UTF-8")+"="+URLEncoder.encode(lec4,"UTF-8")+"&"
                    +URLEncoder.encode("Lecture_5th","UTF-8")+"="+URLEncoder.encode(lec5,"UTF-8")+"&"
                    +URLEncoder.encode("Lecture_6th","UTF-8")+"="+URLEncoder.encode(lec6,"UTF-8")+"&"
                    +URLEncoder.encode("Lecture_7th","UTF-8")+"="+URLEncoder.encode(lec7,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String result = "";
            while((JSON_STRING=bufferedReader.readLine())!=null){
                result += JSON_STRING;
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

    @Override
    protected void onPreExecute() {
        time_table_url = "http://"+IPContainer.ipAddress+"/AndroidProject/addTimeTable.php";
    }

    @Override
    protected void onPostExecute(String result) {
        JSON_STRING = result;
        if(JSON_STRING.isEmpty()){
            Toast.makeText(context.getApplicationContext(),"Unable to connect to the server",Toast.LENGTH_LONG).show();
        }else{
            if (JSON_STRING.equals("1")){
                ViewDialogBox.showDialogBox("Time Table Updated Successfully",context);
                //Toast.makeText(context.getApplicationContext(),"Time Table Updated Successfully",Toast.LENGTH_LONG).show();
            }else if (JSON_STRING.equals("2")){
                ViewDialogBox.showDialogBox("Record Inserted Successfully",context);
                //Toast.makeText(context.getApplicationContext(),"Record Inserted Successfully",Toast.LENGTH_LONG).show();
            }else if (JSON_STRING.equals("3")){
                ViewDialogBox.showDialogBox("Register EmpId First",context);
                //Toast.makeText(context.getApplicationContext(),"Register EmpId First",Toast.LENGTH_LONG).show();
            }
        }

    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
