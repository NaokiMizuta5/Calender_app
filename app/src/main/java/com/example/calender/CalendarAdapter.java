package com.example.calender;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends BaseAdapter {
    private List<Date> dateArray = new ArrayList();
    private Context mContext;
    private DateManager mDateManager;
    private LayoutInflater mLayoutInflater;


    private static class ViewHolder {
        public TextView dateText;
    }

    public CalendarAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mDateManager = new DateManager();
        dateArray = mDateManager.getDays();
    }

    @Override
    public int getCount() {
        return dateArray.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.calendar_cell, null);
            holder = new ViewHolder();
            holder.dateText = convertView.findViewById(R.id.dateText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        float dp = mContext.getResources().getDisplayMetrics().density;
        ViewGroup.LayoutParams params = new AbsListView.LayoutParams(parent.getWidth()/7 - (int)dp,(parent.getHeight() - (int)dp * mDateManager.getWeeks()) / mDateManager.getWeeks());
        convertView.setLayoutParams(params);

        SimpleDateFormat dateFormat = new SimpleDateFormat("d", Locale.US);
        holder.dateText.setText(dateFormat.format(dateArray.get(position)));

        if (mDateManager.isCurrentMonth(dateArray.get(position))) {
            convertView.setBackgroundColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.LTGRAY);
        }

        int colorId;
        switch (mDateManager.getDaysOfWeek(dateArray.get(position))) {
            case 1:
                colorId = Color.RED;
                break;
            case 7:
                colorId = Color.BLUE;
                break;
            default:
                colorId = Color.BLACK;
                break;
        }
        holder.dateText.setTextColor(colorId);

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public String getTitle() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM",Locale.US);
        return format.format(mDateManager.mCalendar.getTime());
    }
    public void nextMonth() {
        mDateManager.nextMonth();
        dateArray = mDateManager.getDays();
        this.notifyDataSetChanged();
    }
    public void prevMonth() {
        mDateManager.prevMonth();
        dateArray = mDateManager.getDays();
        this.notifyDataSetChanged();
    }
}
