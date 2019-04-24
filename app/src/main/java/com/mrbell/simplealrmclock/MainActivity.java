package com.mrbell.simplealrmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    TimePicker timePicker;
    TextView update;
    Context context;
    Button btnAlrmon,btnAlrmoff;
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context=this;
        final Calendar calendar = Calendar.getInstance();

        final Intent intent =new Intent(this.context,Alarm_reciver.class);

        alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);

        timePicker=findViewById(R.id.timepicker);

        update=findViewById(R.id.update);

        btnAlrmon=findViewById(R.id.start_alarm);
        btnAlrmoff=findViewById(R.id.end_alarm);



        btnAlrmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    calendar.set(calendar.HOUR_OF_DAY,timePicker.getHour());
                    calendar.set(calendar.MINUTE,timePicker.getMinute());

                    int hour=timePicker.getHour();
                    int minute=timePicker.getMinute();

                    String hour_string=String.valueOf(hour);
                    String minute_string =String.valueOf(minute);

                    if(hour >12){
                        hour_string=String.valueOf(hour-12);

                    }
                    if(minute<10){
                        minute_string="0"+String.valueOf(minute);
                    }
                    setalrm_text("Alrm set to!"+hour_string + ":"+ minute_string);

                    pendingIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManager.set(alarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);



                }
                else{
                    calendar.set(calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                    calendar.set(calendar.MINUTE,timePicker.getCurrentMinute());
                    int hour=timePicker.getCurrentHour();
                    int minute=timePicker.getCurrentMinute();

                    String hour_string=String.valueOf(hour);
                    String minute_string =String.valueOf(minute);

                    setalrm_text("Alrm on!"+hour_string + ":"+ minute_string);

                }





            }
        });

        btnAlrmoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setalrm_text("Alrm off!");
            }
        });

    }

    private void setalrm_text(String s) {

        update.setText(s);
    }
}
