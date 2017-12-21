package com.example.mailo.serviceapplication5778;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    static int count = 1;
    int id = 0, startId = -1;
    boolean isRun = false;
    final String TAG = "testService";

    public MyService() {
        id = count++;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    String serviceInfo() {
        return "service [" + id + "] startId = " + startId;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        id++;
        Log.d(TAG, serviceInfo() + " onCreate ...");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, serviceInfo() + " onDestroy ...");
        isRun = false;
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;
        isRun = true;

        Log.d(TAG, serviceInfo() + " onStartCommand start ...");

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (isRun) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, serviceInfo() + " print ...");
                }
            }
        };

        thread.start();
        Log.d(TAG, serviceInfo() + " onStartCommand end ...");

        return Service.START_STICKY; // super.onStartCommand(intent, flags, startId);
    }
}
