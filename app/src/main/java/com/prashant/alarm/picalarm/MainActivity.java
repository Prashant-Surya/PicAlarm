package com.prashant.alarm.picalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {
    PendingIntent alarmIntent,bdayIntent;
    long alarmTime,bdayTime,currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar current = Calendar.getInstance();
        Calendar alarm = Calendar.getInstance();
        Calendar bday = Calendar.getInstance();
        // Alarm for changing wallpaper daily
        alarm.set(Calendar.HOUR_OF_DAY,14);
        alarm.set(Calendar.MINUTE,30);
        alarm.set(Calendar.SECOND,0);
        // Bday for changing wallpaper on her bday
        bday.set(Calendar.DAY_OF_MONTH,04);
        bday.set(Calendar.HOUR_OF_DAY,15);
        bday.set(Calendar.MINUTE,00);
        bday.set(Calendar.SECOND,58);
        currentTime = current.getTimeInMillis();
        alarmTime = alarm.getTimeInMillis();
        bdayTime = bday.getTimeInMillis();

        Intent tempAlarm = new Intent(MainActivity.this,AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(MainActivity.this,1234,tempAlarm,0);

        Intent tempAlarm2 = new Intent(MainActivity.this,BdayReceiver.class);
        bdayIntent = PendingIntent.getBroadcast(MainActivity.this,1234,tempAlarm2,0);

        AlarmManager daily = (AlarmManager)getSystemService(ALARM_SERVICE);
        AlarmManager onbday = (AlarmManager)getSystemService(ALARM_SERVICE);
        if(alarmTime >= currentTime) // you can add buffer time too here to ignore some small differences in milliseconds
        {
            //set from today
            daily.setRepeating(AlarmManager.RTC,
                    alarmTime , AlarmManager.INTERVAL_DAY,
                    alarmIntent);

        }
        else{
            //set from next day
            // you might consider using calendar.add() for adding one day to the current day
            alarm.add(Calendar.DAY_OF_MONTH, 1);
            alarmTime = alarm.getTimeInMillis();

            daily.setRepeating(AlarmManager.RTC,
                    alarmTime , AlarmManager.INTERVAL_DAY,
                    alarmIntent);

        }
        if(bdayTime>=currentTime){
            onbday.set(AlarmManager.RTC_WAKEUP,alarmTime,bdayIntent);
        }else {
            bday.add(Calendar.DAY_OF_MONTH,1);
            bdayTime = bday.getTimeInMillis();
            onbday.set(AlarmManager.RTC_WAKEUP,bdayTime,bdayIntent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
