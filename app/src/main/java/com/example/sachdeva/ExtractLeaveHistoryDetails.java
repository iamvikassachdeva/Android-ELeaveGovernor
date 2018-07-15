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
 * Created by Sachdeva on 29-Oct-17.
 */

public class ExtractLeaveHistoryDetails extends AsyncTask<String, Void, String> {
    String leave_history_detail_url;
    Context context;
    String leave_category, date;
    String json_Data;
    public ExtractLeaveHistoryDetails(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        leave_history_detail_url = "http://"+IPContainer.ipAddress+"/AndroidProject/leaveHistoryDetails.php";
    }

    @Override
    protected void onPostExecute(String result) {
        json_Data = result;
        if(json_Data.isEmpty()){
            ViewDialogBox.showDialogBox("Unable to connect to Server\n check your Internet Connection",context);
            //Toast.makeText(context.getApplicationContext(),"Unable to connect to Server\n check your Internet Connection",Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent(context,LeaveHistoryDetailsView.class);
            intent.putExtra("leavehistoryDetails",json_Data);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        leave_category = params[0];
        date = params[1];
        try {
            URL url = new URL(leave_history_detail_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            String post_data = URLEncoder.encode("Emp_Id","UTf-8")+"="+URLEncoder.encode(EmployeeData.getEmp_id(),"UTF-8")+"&"
                    +URLEncoder.encode("Leave_Category","UTF-8")+"="+URLEncoder.encode(leave_category,"UTf-8")+"&"
                    +URLEncoder.encode("Date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String result="";
            while((json_Data=bufferedReader.readLine())!=null){
                result += json_Data;
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
