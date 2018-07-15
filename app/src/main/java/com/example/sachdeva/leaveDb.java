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
 * Created by Sachdeva on 22-Sep-17.
 */

public class leaveDb extends AsyncTask<String,Void,String>{
    String leave_url;
    Context context;
    String JSON_STRING;
    public leaveDb(Context ctx) {
        context = ctx;
    }

    @Override
    protected void onPreExecute() {
        leave_url = "http://"+IPContainer.ipAddress+"/AndroidProject/remainingLeaves.php";
    }

    @Override
    protected void onPostExecute(String result) {
        JSON_STRING = result;
        if(JSON_STRING.isEmpty()){
            ViewDialogBox.showDialogBox("Unable to Connect to the Server",context);
            //Toast.makeText(context.getApplicationContext(), "unable to fetch data",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(context, ViewLeave.class);
            intent.putExtra("leaveData",JSON_STRING);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(leave_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            String post_data = URLEncoder.encode("Emp_Id","UTF-8")+"="+URLEncoder.encode(EmployeeData.getEmp_id(),"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream));
            String result="";
            while((JSON_STRING = bufferedReader.readLine())!=null){
                result += JSON_STRING +"\n";
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
