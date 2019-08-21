package com.example.cgiday2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

/**
 * you can't have a background service -- oreo
 * you can have a foreground service [ service about which user is aware -- foreground service
 */
public class MusicService extends Service {
    static String TAG = MusicService.class.getSimpleName();
    public static final String ACTION_START_FOREGROUND_SERVICE = "ACTION_START_FOREGROUND_SERVICE";

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"service created");
        MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.music);
        mediaPlayer.start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"service started");

         super.onStartCommand(intent, flags, startId);
        startInForeground();
        return  START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"service destroyed");
       // stopSelf();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    private void startInForeground() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"123")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("TEST")
                .setContentText("HELLO")
                .setTicker("TICKER")
                .setContentIntent(pendingIntent);
        Notification notification=builder.build();
        if(Build.VERSION.SDK_INT>=26) {
            NotificationChannel channel = new NotificationChannel("123", "my channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("channel description");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
        startForeground(1, notification);
    }
}
