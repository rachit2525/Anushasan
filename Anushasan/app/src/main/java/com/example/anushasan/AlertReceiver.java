package com.example.anushasan;

import android.content.BroadcastReceiver;


import android.content.Context;
import android.content.Intent;
//import android.support.v4.app.NotificationCompat;

import androidx.core.app.NotificationCompat;

import com.example.anushasan.utility.NotificationHelper;

public class AlertReceiver extends BroadcastReceiver {
    public static int id=1;
    @Override
    public void onReceive(Context context, Intent intent) {
        String time= intent.getStringExtra("time");
        String name= intent.getStringExtra("Subject");
        String ans=time+" Subject = " + name;
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(ans);
        notificationHelper.getManager().notify(id, nb.build());
        id++;
    }
}