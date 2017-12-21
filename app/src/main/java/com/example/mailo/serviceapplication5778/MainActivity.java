package com.example.mailo.serviceapplication5778;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    final String TAG = "testService";
    boolean isRun = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "activity onCreate ...");

        setContentView(R.layout.activity_main);

        Button startServiceButton = (Button) findViewById(R.id.startServiceButton);
        Button stopServiceButton = (Button) findViewById(R.id.stopServiceButton);


        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getBaseContext(), MyForegroundIntentService.class));
               // MyService m = new MyService();


            }
        });


        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getBaseContext(), MyForegroundIntentService.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "activity onResume ...");
        isRun = true;
        final Thread thread = new Thread() {
            @Override
            public void run() {
                while (isRun) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "activity print ...");
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "activity onDestroy ...");
       isRun = false;
        super.onDestroy();
    }
}
