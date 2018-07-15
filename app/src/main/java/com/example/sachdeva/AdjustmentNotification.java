package com.example.sachdeva.eleavegovernor;

/**
 * Created by Vikas Sachdeva on 09-Oct-17.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AdjustmentNotification extends AppCompatActivity {

    NotificationAdapter notificationAdapter;
    String JSON_DATA;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ListView listView;
    String date, username, status, employeeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjustment_notification);
//        NotificationList.setLec1("");
//        NotificationList.setLec2("");
//        NotificationList.setLec3("");
//        NotificationList.setLec4("");
//        NotificationList.setLec5("");
//        NotificationList.setLec6("");
//        NotificationList.setLec7("");
        notificationAdapter = new NotificationAdapter(this,R.layout.notification_list);
        listView = (ListView)findViewById(R.id.adjustmentNotificationListview);
        listView.setAdapter(notificationAdapter);
        JSON_DATA = getIntent().getStringExtra("notificationData");

        try {
            jsonObject = new JSONObject(JSON_DATA);
            jsonArray = jsonObject.getJSONArray("result");
            int count=0;
            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                username = JO.getString("UserName");
                date = JO.getString("Date");
                status = JO.getString("Status");
                employeeId = JO.getString("EmployeeId");
                NotificationData notificationData = new NotificationData(date,username,status, employeeId);
                notificationAdapter.add(notificationData);
                count++;
            }

//            jsonObject = new JSONObject(JSON_DATA);
//            jsonArray = jsonObject.getJSONArray("result");
//            //JSONObject JO = jsonArray.getJSONObject(0);
//            //JSONObject lecture1Object = jsonObject.getJSONObject("Lecture_Ist");
//            jsonArray = jsonObject.getJSONArray("Lecture_Ist");
//            String User_Name;
//            lecturesList.add("testing");
//            for(int i=0; i<jsonArray.length(); i++){
//                JSONObject JO = jsonArray.getJSONObject(i);
//                User_Name = JO.getString("User_Name");
//                lecturesList.add("Lecture_Ist: " + User_Name);
//                length++;
//            }
//            jsonArray = jsonObject.getJSONArray("Lecture_2nd");
//
//            for(int i=0; i<jsonArray.length(); i++){
//                JSONObject JO = jsonArray.getJSONObject(i);
//                User_Name = JO.getString("User_Name");
//                lecturesList.add("Lecture_2nd: " + User_Name);
//                length++;
//            }
//            jsonArray = jsonObject.getJSONArray("Lecture_3rd");
//
//            for(int i=0; i<jsonArray.length(); i++){
//                JSONObject JO = jsonArray.getJSONObject(i);
//                User_Name = JO.getString("User_Name");
//                lecturesList.add("Lecture_3rd: " + User_Name);
//                length++;
//            }
//
//            jsonArray = jsonObject.getJSONArray("Lecture_4th");
//            for(int i=0; i<jsonArray.length(); i++){
//                JSONObject JO = jsonArray.getJSONObject(i);
//                User_Name = JO.getString("User_Name");
//                lecturesList.add("Lecture_4th: " + User_Name);
//                length++;
//            }
//
//            jsonArray = jsonObject.getJSONArray("Lecture_5th");
//            for(int i=0; i<jsonArray.length(); i++){
//                JSONObject JO = jsonArray.getJSONObject(i);
//                User_Name = JO.getString("User_Name");
//                lecturesList.add("Lecture_5th: " + User_Name);
//                length++;
//            }
//
//            jsonArray = jsonObject.getJSONArray("Lecture_6th");
//            for(int i=0; i<jsonArray.length(); i++){
//                JSONObject JO = jsonArray.getJSONObject(i);
//                User_Name = JO.getString("User_Name");
//                lecturesList.add("Lecture_6th: " + User_Name);
//                length++;
//            }
//
//            jsonArray = jsonObject.getJSONArray("Lecture_7th");
//            for(int i=0; i<jsonArray.length(); i++){
//                JSONObject JO = jsonArray.getJSONObject(i);
//                User_Name = JO.getString("User_Name");
//                lecturesList.add("Lecture_7th: " + User_Name);
//                length++;
//            }
            //jsonArray = jsonObject.getJSONArray("result");
            //JSONObject JO = jsonArray.getJSONObject(0);


//            final String lec1, lec2, lec3, lec4, lec5, lec6, lec7;
//            lec1 = JO.getString("Lecture_Ist");
//            lec2 = JO.getString("Lecture_2nd");
//            lec3 = JO.getString("Lecture_3rd");
//            lec4 = JO.getString("Lecture_4th");
//            lec5 = JO.getString("Lecture_5th");
//            lec6 = JO.getString("Lecture_6th");
//            lec7 = JO.getString("Lecture_7th");
//            //Toast.makeText(getApplicationContext(),lec1+" "+lec2,Toast.LENGTH_LONG).show();
//            if(!lec1.equals("null")) {
//                lecturesList.add("Lecture_Ist: " + lec1);
//                length++;
//            }if (!lec2.equals("null")){
//                lecturesList.add("Lecture_2nd: " + lec2);
//                length++;
//            }if(!lec3.equals("null")){
//                lecturesList.add("Lecture_3rd: " + lec3);
//                length++;
//            }if (!lec4.equals("null")){
//                lecturesList.add("Lecture_4th: " + lec4);
//                length++;
//            }if (!lec5.equals("null")){
//                lecturesList.add("Lecture_5th: " + lec5);
//                length++;
//            }if(!lec6.equals("null")){
//                lecturesList.add("Lecture_6th: " + lec6);
//                length++;
//            }if(!lec7.equals("null")){
//                lecturesList.add("Lecture_7th: " + lec7);
//                length++;
//            }
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            TextView tx_date = (TextView)view.findViewById(R.id.notificationDate);
                            TextView tx_employeeId = (TextView)view.findViewById(R.id.notificationEmployeeId);
                            TextView tx_username = (TextView)view.findViewById(R.id.notificationUsername);
                            //ViewDialogBox.showDialogBox(tx_date.getText().toString() + "lectureno "+tx_lectureno.getText().toString(),RequestsForAdjustment.this);
                            String type = "NotificationView";
                            GetDbData getDbData = new GetDbData(AdjustmentNotification.this);
                            getDbData.execute(tx_date.getText().toString(), type, tx_username.getText().toString(),tx_employeeId.getText().toString());
                            /*String value = (String)listView.getItemAtPosition(position);
                            Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();
                            AdjustmentDbHandler adjustmentDbHandler = new AdjustmentDbHandler(MarkAdjustment.this);
                            adjustmentDbHandler.execute(value,EmployeeData.getDepartment(),weekDays);*/

                            // On Click listener for notification item
                        }
                    }
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
