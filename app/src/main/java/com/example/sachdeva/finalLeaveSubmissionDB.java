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
 * Created by Vikas Sachdeva on 09-Oct-17.
 */

public class finalLeaveSubmissionDB extends AsyncTask<String,Void,String> {
    String leaveHistory_url;
    Context context;
    String leave_Category, leave_Type, date, description;
    String status;
    String payStatus;
    //float remainingCl, remainingAl, remainingMl, remainingEl, withoutPay;
    public finalLeaveSubmissionDB(Context ctx) {
        context = ctx;
    }

    @Override
    protected void onPreExecute() {
        leaveHistory_url = "http://"+IPContainer.ipAddress+"/AndroidProject/leaveSubmissionDb.php";
    }

    @Override
    protected void onPostExecute(String result) {
        status = result;
        //Toast.makeText(context.getApplicationContext(),remainingCl+" "+remainingAl+" "+remainingEl+" "+remainingMl,Toast.LENGTH_LONG).show();
        if(status.isEmpty()){
            //ViewDialogBox.showDialogBox("Leave Confirmation Not Success",context);
            Toast.makeText(context.getApplicationContext(),"Leave Confirmation Not Success",Toast.LENGTH_LONG).show();
        }else {
            ViewDialogBox.showDialogBox(status, context);
            //Toast.makeText(context.getApplicationContext(),status + payStatus, Toast.LENGTH_LONG).show();
            //leaveDb ldb = new leaveDb(context);
            //ldb.execute();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        leave_Category = params[0];
        leave_Type = params[1];
        date = params[2];
        description = params[3];
        payStatus = params[4];
        /*float num = 0;
        if(leave_Type.equals("Full Day")){
            num = 1;
        }else if (leave_Type.equals("Half Day")){
            num = (float)0.5;
        }
        remainingCl = LeavesData.getCl();
        remainingAl = LeavesData.getAl();
        remainingMl = LeavesData.getMl();
        remainingEl = LeavesData.getEl();
        withoutPay = LeavesData.getWpl();
        if(leave_Category.equals("Casual Leave")){
            if((remainingCl<1 && leave_Type.equals("Full Day"))||remainingCl<0.5 && leave_Type.equals("Half Day")){
                payStatus = "without Pay";
                if(leave_Type.equals("Full Day")){
                    withoutPay += 1;
                }else if (leave_Type.equals("Half Day")){
                    withoutPay += 0.5;
                }
            }else {
                remainingCl -= num;
            }
        }else if (leave_Category.equals("Academic Leave")){
            if((remainingAl<1 && leave_Type.equals("Full Day"))||remainingAl<0.5 && leave_Type.equals("Half Day")){
                payStatus = "without Pay";
                if(leave_Type.equals("Full Day")){
                    withoutPay += 1;
                }else if (leave_Type.equals("Half Day")){
                    withoutPay += 0.5;
                }
            }else {
                remainingAl -= num;
            }
        }else if (leave_Category.equals("Medical Leave")){
            if((remainingMl<1 && leave_Type.equals("Full Day"))||remainingMl<0.5 && leave_Type.equals("Half Day")){
                payStatus = "without Pay";
                if(leave_Type.equals("Full Day")){
                    withoutPay += 1;
                }else if (leave_Type.equals("Half Day")){
                    withoutPay += 0.5;
                }
            }else {
                remainingMl -= num;
            }
        }else if(leave_Category.equals("Earned Leave")){
            if((remainingEl<1 && leave_Type.equals("Full Day"))||remainingEl<0.5 && leave_Type.equals("Half Day")){
                payStatus = "without Pay";
                if(leave_Type.equals("Full Day")){
                    withoutPay += 1;
                }else if (leave_Type.equals("Half Day")){
                    withoutPay += 0.5;
                }
            }else {
                remainingEl -= num;
            }
        }*/
        try {
            URL url = new URL(leaveHistory_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            String post_data = URLEncoder.encode("Emp_Id","UTF-8")+"="+URLEncoder.encode(EmployeeData.getEmp_id(),"UTF-8")+"&"
                    +URLEncoder.encode("Leave_Category","UTF-8")+"="+URLEncoder.encode(leave_Category,"UTF-8")+"&"
                    +URLEncoder.encode("Leave_Type","UTF-8")+"="+URLEncoder.encode(leave_Type,"UTF-8")+"&"
                    +URLEncoder.encode("Date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                    +URLEncoder.encode("Status", "UTF-8")+"="+URLEncoder.encode("PENDING","UTF-8")+"&"
                    +URLEncoder.encode("PayStatus","UTF-8")+"="+URLEncoder.encode(payStatus,"UTF-8")+"&"
                    +URLEncoder.encode("Description","UTF-8")+"="+URLEncoder.encode(description,"UTF-8")+"&"
                    +URLEncoder.encode("Department","UTF-8")+"="+URLEncoder.encode(EmployeeData.getDepartment(),"UTF-8")+"&"
                    +URLEncoder.encode("Lec_1st","UTF-8")+"="+URLEncoder.encode(AdjustmentList.getLec1(),"UTF-8")+"&"
                    +URLEncoder.encode("Lec_2nd","UTF-8")+"="+URLEncoder.encode(AdjustmentList.getLec2(),"UTF-8")+"&"
                    +URLEncoder.encode("Lec_3rd","UTF-8")+"="+URLEncoder.encode(AdjustmentList.getLec3(),"UTF-8")+"&"
                    +URLEncoder.encode("Lec_4th","UTF-8")+"="+URLEncoder.encode(AdjustmentList.getLec4(),"UTF-8")+"&"
                    +URLEncoder.encode("Lec_5th","UTF-8")+"="+URLEncoder.encode(AdjustmentList.getLec5(),"UTF-8")+"&"
                    +URLEncoder.encode("Lec_6th","UTF-8")+"="+URLEncoder.encode(AdjustmentList.getLec6(),"UTF-8")+"&"
                    +URLEncoder.encode("Lec_7th","UTF-8")+"="+URLEncoder.encode(AdjustmentList.getLec7(),"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String result="";
            while((status=bufferedReader.readLine())!=null){
                result += status;
            }
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
