package com.example.sachdeva.eleavegovernor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestsRaised extends AppCompatActivity {
    String requestRaisedData;
    RequestsRaisedAdapter requestsRaisedAdapter;
    ListView listView;
    String date, lectureNo;
    JSONObject jsonObject;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_raised);
        requestsRaisedAdapter = new RequestsRaisedAdapter(this,R.layout.request_raised_list);
        listView = (ListView)findViewById(R.id.requestViewList);
        listView.setAdapter(requestsRaisedAdapter);
        requestRaisedData = getIntent().getExtras().getString("requestsRaised");
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
//                            TextView tx_date = (TextView)view.findViewById(R.id.leaveHistorydate);
//                            TextView tx_leaveCategory = (TextView)view.findViewById(R.id.leaveHistoryLeaveCategory);
//                            String date = tx_date.getText().toString();
//                            String leaveCategory = tx_leaveCategory.getText().toString();
//                            ExtractLeaveHistoryDetails extractLeaveHistoryDetails = new ExtractLeaveHistoryDetails(LeaveHistory.this);
//                            extractLeaveHistoryDetails.execute(leaveCategory,date);
                        }
                    }
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
