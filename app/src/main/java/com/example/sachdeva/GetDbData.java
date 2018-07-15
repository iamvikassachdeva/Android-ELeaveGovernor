package com.example.sachdeva.eleavegovernor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Sachdeva on 12-Sep-17.
 */

public class GetDbData extends AsyncTask<String, Void, String> {
    String type, username;
    String leaveHistory_url, notificationView_url, notificationList_url, updateLeaveRequest_url;
    Context context;
    String JSON_STRING;
    public GetDbData(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        type = params[1];
        if (type.equals("leaveHistory")){
            String EmpId = params[0];
            try {
                URL url = new URL(leaveHistory_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String post_data = URLEncoder.encode("Emp_Id","UTF-8")+"="+URLEncoder.encode(EmpId,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String result = "";
                while((JSON_STRING = bufferedReader.readLine()) != null){
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
        }else if (type.equals("NotificationList")){
            String emp_id = params[0];
            String date = params[2];
            try {
                URL url = new URL(notificationList_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String post_data = URLEncoder.encode("Emp_Id", "UTF-8")+"="+URLEncoder.encode(emp_id, "UTF-8")+"&"
                        +URLEncoder.encode("Date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8");
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
        }else if (type.equals("NotificationView")){
            String date = params[0];
            String emp_id = params[3];
            username = params[2];
            try {
                URL url = new URL(notificationView_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String post_data = URLEncoder.encode("Emp_Id", "UTF-8")+"="+URLEncoder.encode(emp_id, "UTF-8")+"&"
                        +URLEncoder.encode("Date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("AssignedTo","UTF-8")+"="+URLEncoder.encode(EmployeeData.getEmp_id(),"UTF-8");
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
        }else if (type.equals("updateLeaveRequest")){
            String emp_id = params[0];
            String date = params[2];
            String status = params[3];
            try {
                URL url = new URL(updateLeaveRequest_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String post_data = URLEncoder.encode("Emp_Id", "UTF-8")+"="+URLEncoder.encode(emp_id, "UTF-8")+"&"
                        +URLEncoder.encode("Date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("AssignedTo","UTF-8")+"="+URLEncoder.encode(EmployeeData.getEmp_id(),"UTF-8")+"&"
                        +URLEncoder.encode("Status","UTF-8")+"="+URLEncoder.encode(status,"UTF-8");
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
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        leaveHistory_url = "http://"+IPContainer.ipAddress+"/AndroidProject/leaveHistory.php";
        notificationList_url = "http://"+IPContainer.ipAddress+"/AndroidProject/notificationList.php";
        notificationView_url = "http://"+IPContainer.ipAddress+"/AndroidProject/notificationView.php";
        updateLeaveRequest_url = "http://"+IPContainer.ipAddress+"/AndroidProject/updateLeaveRequest.php";
    }

    @Override
    protected void onPostExecute(String result) {
        if (type.equals("leaveHistory")){
            JSON_STRING = result;
            if(JSON_STRING.isEmpty()){
                ViewDialogBox.showDialogBox("Unable to Connect to the Server",context);
                //Toast.makeText(context.getApplicationContext(), "unable to fetch data",Toast.LENGTH_LONG).show();
            }
            else {
                Intent intent = new Intent(context, LeaveHistory.class);
                intent.putExtra("leaveData", JSON_STRING);
                context.startActivity(intent);
            }
        }else if (type.equals("NotificationList")){
            JSON_STRING = result;
            if(JSON_STRING.isEmpty()){
                ViewDialogBox.showDialogBox("Unable to Connect to the Server",context);
                //Toast.makeText(context.getApplicationContext(),"Unable to connect to the server",Toast.LENGTH_LONG).show();
            }else {
                //ViewDialogBox.showDialogBox(JSON_STRING,context);
                //Toast.makeText(context.getApplicationContext(),JSON_STRING,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,AdjustmentNotification.class);
                intent.putExtra("notificationData",JSON_STRING);
                context.startActivity(intent);
            }
        }else if (type.equals("NotificationView")){
            JSON_STRING = result;
            if(JSON_STRING.isEmpty()){
                ViewDialogBox.showDialogBox("Unable to Connect to the Server",context);
                //Toast.makeText(context.getApplicationContext(),"Unable to connect to the server",Toast.LENGTH_LONG).show();
            }else {
                //ViewDialogBox.showDialogBox(JSON_STRING,context);
                //Toast.makeText(context.getApplicationContext(),JSON_STRING,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,NotificationView.class);
                intent.putExtra("notificationViewData",JSON_STRING);
                intent.putExtra("EmployeeName",username);
                context.startActivity(intent);
            }
        }else if (type.equals("updateLeaveRequest")){
            JSON_STRING = result;
            if(JSON_STRING.isEmpty()){
                ViewDialogBox.showDialogBox("Unable to Connect to the Server",context);
                //Toast.makeText(context.getApplicationContext(),"Unable to connect to the server",Toast.LENGTH_LONG).show();
            }else {
                ViewDialogBox.showDialogBox(JSON_STRING,context);
                //Toast.makeText(context.getApplicationContext(),JSON_STRING,Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(context,NotificationView.class);
//                intent.putExtra("notificationViewData",JSON_STRING);
//                intent.putExtra("EmployeeName",username);
//                context.startActivity(intent);
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
