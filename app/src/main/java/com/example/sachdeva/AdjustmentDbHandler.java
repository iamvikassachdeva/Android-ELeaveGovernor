package com.example.sachdeva.eleavegovernor;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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
 * Created by Vikas Sachdeva on 08-Oct-17.
 */

public class AdjustmentDbHandler extends AsyncTask<String, Void, String> {
    String db_url;
    String json_string="";
    String lecture, department, weekdays,date;
    Context context;
    public AdjustmentDbHandler(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        lecture = params[0];
        department = params[1];
        weekdays = params[2];
        date = params[3];
        try {
            URL url = new URL(db_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            String post_data = URLEncoder.encode("Lecture","UTF-8")+"="+URLEncoder.encode(lecture,"UTF-8")+"&"
                    +URLEncoder.encode("Department","UTF-8")+"="+URLEncoder.encode(department,"UTF-8")+"&"
                    +URLEncoder.encode("Week_Days","UTF-8")+"="+URLEncoder.encode(weekdays,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String result="";
            while((json_string=bufferedReader.readLine())!=null){
                result += json_string;
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
        db_url = "http://"+IPContainer.ipAddress+"/AndroidProject/adjustmentMaker.php";
    }

    @Override
    protected void onPostExecute(String result) {
        json_string = result;
        if (json_string.isEmpty()){
            ViewDialogBox.showDialogBox("Unable to connect to Server",context);
            //Toast.makeText(context.getApplicationContext(),"Unable to connect to Server",Toast.LENGTH_LONG).show();
        }else {
            //Toast.makeText(context.getApplicationContext(),json_string,Toast.LENGTH_LONG).show();
            //ViewDialogBox.showDialogBox(json_string,context);
            Intent intent = new Intent(context,AdjustmentMaker.class);
            intent.putExtra("lectureNo",lecture);
            intent.putExtra("adjustmentList",json_string);
            intent.putExtra("date",date);
            intent.putExtra("weekDay",weekdays);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
