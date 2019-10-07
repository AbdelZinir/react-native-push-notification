package com.dieam.reactnativepushnotificationcustom.modules;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import static com.dieam.reactnativepushnotificationcustom.modules.RNPushNotification.LOG_TAG;

public class RNPushNotificationPublisher extends BroadcastReceiver {
    final static String NOTIFICATION_ID = "notificationId";

    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        long currentTime = System.currentTimeMillis();

        Log.i(LOG_TAG, "NotificationPublisher: Prepare To Publish: " + id + ", Now Time: " + currentTime);

        Application applicationContext = (Application) context.getApplicationContext();

        PackageManager pm = context.getPackageManager();
        Intent launchIntent = pm.getLaunchIntentForPackage(applicationContext.getPackageName());
        context.startActivity(launchIntent);

        new RNPushNotificationHelper(applicationContext)
                .sendToNotificationCentre(intent.getExtras());
    }
}
