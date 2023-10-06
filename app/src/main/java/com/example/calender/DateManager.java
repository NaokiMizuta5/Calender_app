package com.example.calender;

import java.text.SimpleDateFormat;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class DateManager {
    Calendar mCalendar;

    public DateManager() {
        mCalendar = Calendar.getInstance();
    }

    public List<Date> getDays() {
        Date startDate = mCalendar.getTime();

        int count = getWeeks() * 7;

        mCalendar.set(Calendar.DATE, 1);
        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        mCalendar.add(Calendar.DATE, -dayOfWeek);

        List<Date> days = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            days.add(mCalendar.getTime());
            mCalendar.add(Calendar.DATE, 1);
        }
        mCalendar.setTime(startDate);

        return days;
    }

    public boolean isCurrentMonth(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM", Locale.US);
        String currentMonth = format.format(mCalendar.getTime());
        if (currentMonth.equals(format.format(date))) {
            return true;
        } else {
            return false;
        }
    }
    public int getWeeks() {
        return mCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    public int getDaysOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public void nextMonth(){
        mCalendar.add(Calendar.MONTH,1);
    }

    public void prevMonth(){
        mCalendar.add(Calendar.MONTH,-1);
    }
}
