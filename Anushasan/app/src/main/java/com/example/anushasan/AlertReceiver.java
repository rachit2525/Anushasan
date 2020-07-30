package com.example.anushasan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;



//import android.support.v4.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
	public static int id = 1;

	@Override
	public void onReceive(Context context, Intent intent) {
		String time = intent.getStringExtra("time");
		String reqcode = intent.getStringExtra("reqcode");
		Log.e("NSP", "Alert Receiver code: " + reqcode);
		NotificationHelper notificationHelper = new NotificationHelper(context);
		notificationHelper.sendHighPriorityNotification("Alarm!","Your Alarm Manager is working for time = "
				+ time ,TuesdayActivity.class);
		id++;
	}
}