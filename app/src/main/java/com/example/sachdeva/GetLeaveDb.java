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
 * Created by Sachdeva on 12-Oct-17.
 */

public class GetLeaveDb extends AsyncTask<String, Void, String>{
    String leave_url;
    Context context;
    String JSON_STRING;
    String type, itemSelected, date, description;
    public GetLeaveDb(Context context) {
        this.context = context;
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
            Bundle bundle = new Bundle();
            bundle.putStringArray("finalConfirmationData",new String[]{type,itemSelected,date,description,JSON_STRING});
            Intent intent = new Intent(context,LeaveConfirmation.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        itemSelected = params[1];
        date = params[2];
        description = params[3];
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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
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
