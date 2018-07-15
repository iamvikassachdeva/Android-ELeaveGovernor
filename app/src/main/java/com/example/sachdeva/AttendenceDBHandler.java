package com.example.sachdeva.eleavegovernor;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
 * Created by Vikas Sachdeva on 09-Nov-17.
 */

public class AttendenceDBHandler extends AsyncTask<String, Void, String>{
    Context context;
    String hours,minutes,day,month,year;
    String phpurl, updateurl;
    String Json_url;
    String type;
    String time;
    public AttendenceDBHandler(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        if(type.equals("fingerprint")){
            hours = params[1];
            minutes = params[2];
            day = params[3];
            month = params[4];
            year = params[5];
            try {
                URL url = new URL(phpurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String post_data = URLEncoder.encode("EmpId","UTF-8")+"="+URLEncoder.encode(EmployeeData.getEmp_id(),"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String result = "";
                while((Json_url=bufferedReader.readLine())!=null){
                    result += Json_url;
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
        }else if(type.equals("update")){
            String exceededTime = params[1];
            String cl = params[2];
            time = params[3];
            String date= params[4];
            String type = params[5];
            try {
                URL url = new URL(updateurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String post_data = URLEncoder.encode("Emp_Id","UTF-8")+"="+URLEncoder.encode(EmployeeData.getEmp_id(),"UTF-8")+"&"
                        +URLEncoder.encode("ExceedTime","UTF-8")+"="+URLEncoder.encode(exceededTime,"UTf-8")+"&"
                        +URLEncoder.encode("CasualLeave","UTF-8")+"="+URLEncoder.encode(cl,"UTF-8")+"&"
                        +URLEncoder.encode("Date", "UTF-8")+"="+URLEncoder.encode(date, "UTF-8")+"&"
                        +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8")+"&"
                        +URLEncoder.encode("Type","UTF-8")+"="+URLEncoder.encode(type,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String result="";
                while((Json_url=bufferedReader.readLine())!=null){
                    result += Json_url;
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
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        phpurl = "http://"+IPContainer.ipAddress+"/AndroidProject/exceedTimeDB.php";
        updateurl = "http://"+IPContainer.ipAddress+"/AndroidProject/updateDB.php";
    }
    @Override
    protected void onPostExecute(String s) {
        Json_url = s;
        if(Json_url.isEmpty()){
            ViewDialogBox.showDialogBox("Unable to connect to Server",context);
            //Toast.makeText(context.getApplicationContext(),"Unable to connect to server",Toast.LENGTH_LONG).show();
        }
        else{
            if(type.equals("fingerprint")){
                //ViewDialogBox.showDialogBox(Json_url,context);
                //Toast.makeText(context.getApplicationContext(),Json_url,Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putStringArray("dateData",new String[]{hours,minutes,day,month,year,Json_url});
                Intent intent = new Intent(context, AttendenceStatus.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }else if(type.equals("update")){
                ViewDialogBox.showDialogBox(Json_url,context);
                //Toast.makeText(context.getApplicationContext(),Json_url+" at "+time,Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
