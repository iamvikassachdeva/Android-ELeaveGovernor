package com.example.sachdeva.eleavegovernor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vikas Sachdeva on 29-Oct-17.
 */

public class LeaveHistoryAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public LeaveHistoryAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(LeaveHistoryData object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        DataHolder dataHolder;
        if (row==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.leave_histroy_list_layout,parent,false);
            dataHolder = new DataHolder();
            dataHolder.tx_date = (TextView)row.findViewById(R.id.leaveHistorydate);
            dataHolder.tx_leaveCategory = (TextView)row.findViewById(R.id.leaveHistoryLeaveCategory);
            row.setTag(dataHolder);
        }else {
            dataHolder = (DataHolder)row.getTag();
        }
        LeaveHistoryData leaveHistoryData = (LeaveHistoryData) this.getItem(position);
        dataHolder.tx_date.setText(leaveHistoryData.getDate());
        dataHolder.tx_leaveCategory.setText(leaveHistoryData.getLeaveCtegory());
        if(leaveHistoryData.getStatus().equals("PENDING")){
            row.setBackgroundColor(Color.rgb(255,229,204));
        }else if(leaveHistoryData.getStatus().equals("APPROVED")){
            row.setBackgroundColor(Color.rgb(0,255,0));
        }else if(leaveHistoryData.getStatus().equals("REJECTED")){
            row.setBackgroundColor(Color.RED);
        }
        return row;
    }
    static class DataHolder{
        TextView tx_date, tx_leaveCategory;
    }
}
