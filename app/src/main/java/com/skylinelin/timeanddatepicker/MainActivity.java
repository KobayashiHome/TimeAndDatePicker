package com.skylinelin.timeanddatepicker;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author skylinelin
 * @date 2018/2/3
 * */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity" ;
    private TimePicker mTimePicker;
    private DatePicker mDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimePicker = findViewById(R.id.time_picker);
        mTimePicker.setIs24HourView(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTimePicker.setHour(17);
            mTimePicker.setMinute(52);
        }else{
            mTimePicker.setCurrentHour(17);
            mTimePicker.setCurrentMinute(52);
        }

        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Log.d(TAG,hourOfDay+"："+minute);
            }
        });


        //DatePicker
        mDatePicker = findViewById(R.id.date_picker);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;

        try {
            date = simpleDateFormat.parse("2018-2-3");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date!=null){
            calendar.setTime(date);
        }

        mDatePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Log.d(TAG, year+"-"+monthOfYear+"-"+dayOfMonth);

                        Toast.makeText(MainActivity.this,year+"-"+monthOfYear+"-"+dayOfMonth,Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
