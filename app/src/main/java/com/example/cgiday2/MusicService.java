package com.example.cgiday2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * you can't have a background service -- oreo
 * you can have a foreground service [ service about which user is aware -- foreground service
 */
public class MusicService extends Service {
    static String TAG = MusicService.class.getSimpleName();
    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"service created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"service started");

        return super.onStartCommand(intent, flags, startId);
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
}
