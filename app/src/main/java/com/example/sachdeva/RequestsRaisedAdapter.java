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
 * Created by Sachdeva on 02-Apr-18.
 */

public class RequestsRaisedAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public RequestsRaisedAdapter(@NonNull Context context, @LayoutRes int resource) {
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
        RequestDataHolder requestDataHolder;
        if (row==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.request_raised_list,parent,false);
            requestDataHolder = new RequestDataHolder();
            requestDataHolder.tx_date = (TextView)row.findViewById(R.id.requestRaisedDate);
            requestDataHolder.tx_lectureNo = (TextView)row.findViewById(R.id.requestRaisedLectureNo);
            row.setTag(requestDataHolder);
        }else {
            requestDataHolder = (RequestDataHolder) row.getTag();
        }
        RequestsRaisedData requestsRaisedData = (RequestsRaisedData) this.getItem(position);
        requestDataHolder.tx_date.setText(requestsRaisedData.getDate());
        requestDataHolder.tx_lectureNo.setText(requestsRaisedData.getLectureNo());
        if(requestsRaisedData.getStatus().equals("PENDING")){
            row.setBackgroundColor(Color.rgb(255,229,204));
        }else if(requestsRaisedData.getStatus().equals("APPROVED")){
            row.setBackgroundColor(Color.rgb(0,255,0));
        }else if(requestsRaisedData.getStatus().equals("REJECTED")){
            row.setBackgroundColor(Color.RED);
        }
        return row;
    }
    static class RequestDataHolder{
        TextView tx_date, tx_lectureNo;
    }
}
