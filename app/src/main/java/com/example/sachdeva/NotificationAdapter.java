package com.example.sachdeva.eleavegovernor;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sachdeva on 08-Apr-18.
 */

public class NotificationAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public NotificationAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public void add(RequestsRaisedAdapter object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        NotificationDataHolder notificationDataHolder;
        if (row==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.notification_list,parent,false);
            notificationDataHolder = new NotificationDataHolder();
            notificationDataHolder.tx_date = (TextView)row.findViewById(R.id.notificationDate);
            notificationDataHolder.tx_username = (TextView)row.findViewById(R.id.notificationUsername);
            notificationDataHolder.tx_employeeid = (TextView)row.findViewById(R.id.notificationEmployeeId);
            row.setTag(notificationDataHolder);
        }else {
            notificationDataHolder = (NotificationDataHolder) row.getTag();
        }
        NotificationData notificationData = (NotificationData) this.getItem(position);
        notificationDataHolder.tx_date.setText(notificationData.getDate());
        notificationDataHolder.tx_username.setText(notificationData.getUsername());
        notificationDataHolder.tx_employeeid.setText(notificationData.getEmployeeId());
        if(notificationData.getStatus().equals("PENDING")){
            row.setBackgroundColor(Color.rgb(255,229,204));
        }else if(notificationData.getStatus().equals("APPROVED")){
            row.setBackgroundColor(Color.rgb(0,255,0));
        }else if(notificationData.getStatus().equals("REJECTED")){
            row.setBackgroundColor(Color.RED);
        }
        return row;
    }
    static class NotificationDataHolder{
        TextView tx_date, tx_username, tx_employeeid;
    }
}

