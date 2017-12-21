package com.example.mailo.serviceapplication5778;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    final String TAG = "testService";
    boolean isRun = false;

    private Button startServiceButton;
    private Button stopServiceButton;
    private Button startIntentServiceButton;
    private Button stopIntentServiceButton;
    private Button startForegroundIntentServiceButton;
    private Button stopForegroundIntentServiceButton;
    private Button startNotificationButton;
    private Button stoptNotificationButton;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-12-21 22:19:45 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        startServiceButton = (Button)findViewById( R.id.startServiceButton );
        stopServiceButton = (Button)findViewById( R.id.stopServiceButton );
        startIntentServiceButton = (Button)findViewById( R.id.startIntentServiceButton );
        stopIntentServiceButton = (Button)findViewById( R.id.stopIntentServiceButton );
        startForegroundIntentServiceButton = (Button)findViewById( R.id.startForegroundIntentServiceButton );
        stopForegroundIntentServiceButton = (Button)findViewById( R.id.stopForegroundIntentServiceButton );
        startNotificationButton = (Button)findViewById( R.id.startNotificationButton );
        stoptNotificationButton = (Button)findViewById( R.id.stoptNotificationButton );

        startServiceButton.setOnClickListener( this );
        stopServiceButton.setOnClickListener( this );
        startIntentServiceButton.setOnClickListener( this );
        stopIntentServiceButton.setOnClickListener( this );
        startForegroundIntentServiceButton.setOnClickListener( this );
        stopForegroundIntentServiceButton.setOnClickListener( this );
        startNotificationButton.setOnClickListener( this );
        stoptNotificationButton.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-12-21 22:19:45 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == startServiceButton ) {
            startService(new Intent(getBaseContext(), MyService.class));
        } else if ( v == stopServiceButton ) {
            stopService(new Intent(getBaseContext(), MyService.class));
        } else if ( v == startIntentServiceButton ) {
            startService(new Intent(getBaseContext(), MyIntentService.class));
        } else if ( v == stopIntentServiceButton ) {
            stopService(new Intent(getBaseContext(), MyIntentService.class));
        } else if ( v == startForegroundIntentServiceButton ) {
            startService(new Intent(getBaseContext(), MyForegroundIntentService.class));
        } else if ( v == stopForegroundIntentServiceButton ) {
            stopService(new Intent(getBaseContext(), MyForegroundIntentService.class));
        } else if ( v == startNotificationButton ) {

            Notification.Builder nBuilder = new Notification.Builder(getBaseContext());

            Bitmap bitmap =  BitmapFactory.decodeResource(getResources(),R.drawable.services);
            nBuilder.setLargeIcon(bitmap);

            nBuilder.setContentTitle("Notification Title");
            nBuilder.setContentText("Notification Content");

            Notification notification = nBuilder.build();

            Object obj = getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationManager notificationManager = (NotificationManager)obj;
            notificationManager.notify(4567, nBuilder.build());

        } else if ( v == stoptNotificationButton ) {
            Object obj = getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationManager notificationManager = (NotificationManager)obj;
            notificationManager.cancel(4567);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "activity onCreate ...");

        setContentView(R.layout.activity_main);
        findViews();
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
