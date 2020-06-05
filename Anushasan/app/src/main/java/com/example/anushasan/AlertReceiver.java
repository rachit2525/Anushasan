package com.example.anushasan;

import android.content.BroadcastReceiver;


import android.content.Context;
import android.content.Intent;
//import android.support.v4.app.NotificationCompat;

import androidx.core.app.NotificationCompat;

import com.example.anushasan.utility.NotificationHelper;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String time= intent.getStringExtra("time");
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(time);
        notificationHelper.getManager().notify(1, nb.build());
    }
}