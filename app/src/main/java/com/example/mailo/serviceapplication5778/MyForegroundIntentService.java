package com.example.mailo.serviceapplication5778;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyForegroundIntentService extends IntentService {
   final String TAG = "testService";

    Notification.Builder nBuilder;

    public MyForegroundIntentService() {
        super("MyForegroundIntentService");
    }



    @Override
    public void onCreate() {
        super.onCreate();


       nBuilder = new Notification.Builder(getBaseContext());

        nBuilder.setSmallIcon(R.drawable.services);
        nBuilder.setContentTitle("Notification Title");
        nBuilder.setContentText("Notification Content");

        nBuilder.setProgress(100,50,false);

        Notification notification = nBuilder.build();

       // NotificationManager notificationManager =    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
      //  notificationManager.notify(1234, nBuilder.build());

        startForeground(1234, notification);

        //  R.drawable.services,"service run ...", System.currentTimeMillis());


//        Intent notificationIntent = new Intent(getBaseContext(), MainActivity.class);
//        PendingIntent pendingIntent =
//                PendingIntent.getActivity(getBaseContext(), 0, notificationIntent, 0);
//
//        Notification notification =
//                new Notification.Builder(getBaseContext())
//                        .setContentTitle("title")
//                        .setContentText("notification_message")
//                        .setSmallIcon(R.drawable.services)
//                        .setContentIntent(pendingIntent)
//                        .setTicker("ticker_text")
//                        .build();
//        startForeground(1337,notification);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int num = 0;
        while (num <100) {
            try {
                Thread.sleep(500);
                nBuilder.setProgress(100,num++,false);

//Object obj = getSystemService(Context.NOTIFICATION_SERVICE);
//NotificationManager notificationManager = (NotificationManager)obj;
//notificationManager.notify(1234, nBuilder.build());
              startForeground(1234, nBuilder.build());
                //notificationManager.cancel(1234);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "Foreground Service print ..."+num);

        }
    }
}
