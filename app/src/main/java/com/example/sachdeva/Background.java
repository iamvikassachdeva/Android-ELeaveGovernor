package com.example.sachdeva.eleavegovernor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Output;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Spinner;
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
 * Created by Vikas Sachdeva on 02-Sep-17.
 */

public class Background extends AsyncTask<String,Void,String> {
    Context context;
    Background (Context ctx){
        context = ctx;
    }
    String Emp_Id, login_url, registration_url,change_password_url;
    String JSON_STRING;
    String type;
    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        if(type.equals("login")){
            try {
                Emp_Id = params[1];
                String Password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("Emp_Id", "UTF-8")+"="+URLEncoder.encode(Emp_Id,"UTF-8")+"&"
                        +URLEncoder.encode("Password", "UTF-8")+"="+URLEncoder.encode(Password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String result="";
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
        }else if (type.equals("change_password")){
            Emp_Id = params[1];
            String oldpass = params[2], newpass = params[3];
            try {
                URL url = new URL(change_password_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("Emp_Id", "UTF-8")+"="+URLEncoder.encode(Emp_Id,"UTF-8")+"&"
                        +URLEncoder.encode("OldPassword", "UTF-8")+"="+URLEncoder.encode(oldpass,"UTF-8")+"&"
                        +URLEncoder.encode("NewPassword", "UTF-8")+"="+URLEncoder.encode(newpass,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String result = "";
                while((JSON_STRING = bufferedReader.readLine())!=null){
                    result += JSON_STRING +"\n";
                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("register")){
            Emp_Id = params[1];
            String User_Name = params[2];
            String gender = params[3];
            String department = params[4];
            String Email = params[5];
            String Password = params[6];
            String designation = params[7];
            try {
                URL url = new URL(registration_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("Emp_Id","UTF-8")+"="+URLEncoder.encode(Emp_Id,"UTF-8")+"&"
                        +URLEncoder.encode("User_Name","UTF-8")+"="+URLEncoder.encode(User_Name,"UTF-8")+"&"
                        +URLEncoder.encode("Gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+"&"
                        +URLEncoder.encode("Department","UTF-8")+"="+URLEncoder.encode(department,"UTF-8")+"&"
                        +URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(Email,"UTF-8")+"&"
                        +URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(Password,"UTF-8")+"&"
                        +URLEncoder.encode("Designation","UTF-8")+"="+URLEncoder.encode(designation,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
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
        login_url = "http://"+IPContainer.ipAddress+"/AndroidProject/loginx.php";
        registration_url = "http://"+IPContainer.ipAddress+"/AndroidProject/register.php";
        change_password_url = "http://"+IPContainer.ipAddress+"/AndroidProject/changePassword.php";
    }

    @Override
    protected void onPostExecute(String result) {
        if(type.equals("login")){
            JSON_STRING = result;
            if(JSON_STRING.isEmpty()){
                ViewDialogBox.showDialogBox("Invalid username or password",context);
                //Toast.makeText(context.getApplicationContext(), "Invalid username or password",Toast.LENGTH_LONG).show();
            }
            else {
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtra("data", JSON_STRING);
                context.startActivity(intent);
            }
        }else if (type.equals("change_password")){
            JSON_STRING = result;
            if(JSON_STRING.isEmpty()){
                Toast.makeText(context.getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG).show();
            }else{
                //Toast.makeText(context.getApplicationContext(),JSON_STRING,Toast.LENGTH_LONG).show();
                ViewDialogBox.showDialogBox(JSON_STRING,context);
                //context.finish();
            }
        }
        else if (type.equals("register")){
            JSON_STRING = result;
            if(JSON_STRING.isEmpty()){
                ViewDialogBox.showDialogBox("Unable to register at this time",context);
                //Toast.makeText(context.getApplicationContext(),"Unable to register at this time",Toast.LENGTH_LONG).show();
            }else {
                ViewDialogBox.showDialogBox(JSON_STRING,context);
                //Toast.makeText(context.getApplicationContext(),JSON_STRING,Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
