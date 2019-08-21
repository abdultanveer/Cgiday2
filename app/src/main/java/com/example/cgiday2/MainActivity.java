package com.example.cgiday2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
ProgressBar mProgressBar;
    DownloadTask downloadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progresbar);
    }

    public void download(View view) {
        Intent serviceIntent = new Intent(MainActivity.this,MusicService.class);

        switch (view.getId()){
            case R.id.buttonDownload:
                downloadTask = new DownloadTask(mProgressBar);
                downloadTask.execute("http://yahoo.com");//1
                break;
            case R.id.button_sms_schedule:
                scheduleSms();
                break;
            case R.id.button_start_service:
                startService(serviceIntent);
                break;

            case R.id.button_stop_service:
                stopService(serviceIntent);
                break;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        //downloadTask.cancel(true);
    }

    void scheduleSms(){
        Intent dialIntent = new Intent(MainActivity.this,SmsActivity.class);
        PendingIntent smsPendingIntent =
                PendingIntent.getActivity(this,
                        007,dialIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        long triggerTime = System.currentTimeMillis()+15*1000;
        int alarmType = AlarmManager.RTC_WAKEUP;
        AlarmManager alarmManager =
                (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setExact(alarmType,triggerTime,smsPendingIntent);
    }
}
