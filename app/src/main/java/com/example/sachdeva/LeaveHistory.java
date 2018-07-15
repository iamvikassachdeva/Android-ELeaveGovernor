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

public class LeaveHistory extends AppCompatActivity {
    JSONObject jsonObject;
    JSONArray jsonArray;
    String leaveHistory_data;
    String leaveCategory, date, status;
    LeaveHistoryAdapter leaveHistoryAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_history);
        leaveHistoryAdapter = new LeaveHistoryAdapter(this,R.layout.leave_histroy_list_layout);
        listView = (ListView)findViewById(R.id.historyViewList);
        listView.setAdapter(leaveHistoryAdapter);
        leaveHistory_data = getIntent().getExtras().getString("leaveData");
        try {
            jsonObject = new JSONObject(leaveHistory_data);
            jsonArray = jsonObject.getJSONArray("result");
            int count=0;
            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                leaveCategory = JO.getString("Leave_Category");
                date = JO.getString("Date");
                status = JO.getString("Status");
                LeaveHistoryData leaveHistoryData = new LeaveHistoryData(date,leaveCategory,status);
                leaveHistoryAdapter.add(leaveHistoryData);
                count++;
            }
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            TextView tx_date = (TextView)view.findViewById(R.id.leaveHistorydate);
                            TextView tx_leaveCategory = (TextView)view.findViewById(R.id.leaveHistoryLeaveCategory);
                            String date = tx_date.getText().toString();
                            String leaveCategory = tx_leaveCategory.getText().toString();
                            ExtractLeaveHistoryDetails extractLeaveHistoryDetails = new ExtractLeaveHistoryDetails(LeaveHistory.this);
                            extractLeaveHistoryDetails.execute(leaveCategory,date);
                        }
                    }
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
