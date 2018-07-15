package com.example.sachdeva.eleavegovernor;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Vikas Sachdeva on 31-Mar-18.
 */

public class DBTaskHandler extends AsyncTask<String, Void, String> {
    String type;
    String pending_approval_url, JSON_STRING, request_raised_url,view_request_url, extract_request_data_url,update_request_url;
    Context context;
    public DBTaskHandler(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        if(type.equals("pending_approval")){
            String source = params[1];
            String destination = params[2];
            String lectureNo = params[3];
            String status = params[4];
            String date = params[5];
            String weekdays = params[6];
            try {
                URL url = new URL(pending_approval_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String post_data = URLEncoder.encode("RequestingFaculty","UTF-8")+"="+URLEncoder.encode(source,"UTF-8")+"&"
                                    +URLEncoder.encode("AdjustedFaculty", "UTF-8")+"="+URLEncoder.encode(destination,"UTF-8")+"&"
                                    +URLEncoder.encode("Date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                                    +URLEncoder.encode("Department","UTF-8")+"="+URLEncoder.encode(EmployeeData.getDepartment(),"UTF-8")+"&"
                                    +URLEncoder.encode("LectureNo","UTF-8")+"="+URLEncoder.encode(lectureNo,"UTF-8")+"&"
                                    +URLEncoder.encode("Status","UTF-8")+"="+URLEncoder.encode(status,"UTF-8")+"&"
                                    +URLEncoder.encode("weekdays","UTF-8")+"="+URLEncoder.encode(weekdays,"UTF-8");
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
        }else if(type.equals("requests_raised")){

            try {
                URL url = new URL(request_raised_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String post_data = URLEncoder.encode("RequestingFaculty","UTF-8")+"="+URLEncoder.encode(EmployeeData.getEmp_name(),"UTF-8");
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
        }else if(type.equals("view_requests")){

            try {
                URL url = new URL(view_request_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String post_data = URLEncoder.encode("AdjustedFaculty","UTF-8")+"="+URLEncoder.encode(EmployeeData.getEmp_name(),"UTF-8");
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
        }else if(type.equals("extract_request_data")){
            String date = params[1];
            String lectureNo = params[2];
            try {
                URL url = new URL(extract_request_data_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String post_data = URLEncoder.encode("AdjustedFaculty","UTF-8")+"="+URLEncoder.encode(EmployeeData.getEmp_name(),"UTF-8")+"&"
                                    +URLEncoder.encode("Date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                                    +URLEncoder.encode("LectureNo","UTF-8")+"="+URLEncoder.encode(lectureNo,"UTF-8");
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
        }else if(type.equals("updateRequest")){
            String date = params[2];
            String lectureNo = params[4];
            String RequestingFaculty = params[3];
            String AdjustedFaculty = params[1];
            String status = params[5];
            try {
                URL url = new URL(update_request_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String post_data = URLEncoder.encode("AdjustedFaculty","UTF-8")+"="+URLEncoder.encode(AdjustedFaculty,"UTF-8")+"&"
                        +URLEncoder.encode("Date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("LectureNo","UTF-8")+"="+URLEncoder.encode(lectureNo,"UTF-8")+"&"
                        +URLEncoder.encode("RequestingFaculty","UTF-8")+"="+URLEncoder.encode(RequestingFaculty,"UTF-8")+"&"
                        +URLEncoder.encode("Status","UTF-8")+"="+URLEncoder.encode(status,"UTF-8");
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
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        pending_approval_url = "http://"+IPContainer.ipAddress+"/AndroidProject/pendingApproval.php";
        request_raised_url = "http://"+IPContainer.ipAddress+"/AndroidProject/requestsRaised.php";
        view_request_url = "http://"+IPContainer.ipAddress+"/AndroidProject/requestForDest.php";
        extract_request_data_url = "http://"+IPContainer.ipAddress+"/AndroidProject/leaveRequestData.php";
        update_request_url = "http://"+IPContainer.ipAddress+"/AndroidProject/updateRequest.php";
    }

    @Override
    protected void onPostExecute(String response) {
        JSON_STRING = response;
        if(type.equals("pending_approval")){
            if(JSON_STRING.isEmpty()){
                Toast.makeText(context.getApplicationContext(),"Request cannot be fulfilled",Toast.LENGTH_LONG).show();
            }else {
                ViewDialogBox.showDialogBox(JSON_STRING,context);
            }
        }else if(type.equals("requests_raised")){
            if(JSON_STRING.isEmpty()){
                Toast.makeText(context.getApplicationContext(),"Request cannot be fulfilled",Toast.LENGTH_LONG).show();
            }else{
                Intent intent = new Intent(context, RequestsRaised.class);
                intent.putExtra("requestsRaised", JSON_STRING);
                context.startActivity(intent);
                //ViewDialogBox.showDialogBox(JSON_STRING,context);
            }
        }else if(type.equals("view_requests")){
            if(JSON_STRING.isEmpty()){
                Toast.makeText(context.getApplicationContext(),"Request cannot be fulfilled",Toast.LENGTH_LONG).show();
            }else{
                Intent intent = new Intent(context, RequestsForAdjustment.class);
                intent.putExtra("requestsForAdjustment", JSON_STRING);
                context.startActivity(intent);
                //ViewDialogBox.showDialogBox(JSON_STRING,context);
            }
        }else if(type.equals("extract_request_data")){
            if(JSON_STRING.isEmpty()){
                Toast.makeText(context.getApplicationContext(),"Request cannot be fulfilled",Toast.LENGTH_LONG).show();
            }else{
                Intent intent = new Intent(context, ViewRequestData.class);
                intent.putExtra("viewrequestdetails",JSON_STRING);
                context.startActivity(intent);
                //ViewDialogBox.showDialogBox(JSON_STRING,context);
            }
        }else if(type.equals("updateRequest")){
            if(JSON_STRING.isEmpty()){
                Toast.makeText(context.getApplicationContext(),"Request cannot be fulfilled",Toast.LENGTH_LONG).show();
            }else{
                ViewDialogBox.showDialogBox(JSON_STRING,context);
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
