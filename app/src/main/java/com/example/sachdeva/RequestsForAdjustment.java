package com.example.sachdeva.eleavegovernor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestsForAdjustment extends AppCompatActivity {

    String requestRaisedData;
    RequestsRaisedAdapter requestsRaisedAdapter;
    ListView listView;
    String date, lectureNo;
    JSONObject jsonObject;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_for_adjustment);
        requestsRaisedAdapter = new RequestsRaisedAdapter(this,R.layout.request_raised_list);
        listView = (ListView)findViewById(R.id.requestApprovalList);
        listView.setAdapter(requestsRaisedAdapter);
        requestRaisedData = getIntent().getExtras().getString("requestsForAdjustment");
        try {
            jsonObject = new JSONObject(requestRaisedData);
            jsonArray = jsonObject.getJSONArray("result");
            int count=0;
            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                lectureNo = JO.getString("LectureNo");
                date = JO.getString("Date");
                String Status = JO.getString("Status");
                RequestsRaisedData requestsRaisedData = new RequestsRaisedData(lectureNo,date,Status);
                requestsRaisedAdapter.add(requestsRaisedData);
                count++;
            }
            listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(getApplicationContext(),"this is a test",Toast.LENGTH_LONG).show();
                        TextView tx_date = (TextView)view.findViewById(R.id.requestRaisedDate);
                        TextView tx_lectureno = (TextView)view.findViewById(R.id.requestRaisedLectureNo);
                        //ViewDialogBox.showDialogBox(tx_date.getText().toString() + "lectureno "+tx_lectureno.getText().toString(),RequestsForAdjustment.this);
                        String type = "extract_request_data";
                        DBTaskHandler dbTaskHandler = new DBTaskHandler(RequestsForAdjustment.this);
                        dbTaskHandler.execute(type, tx_date.getText().toString(), tx_lectureno.getText().toString());
                    }
                }
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
