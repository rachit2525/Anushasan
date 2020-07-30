package com.example.anushasan.nsp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.anushasan.AlertReceiver;
import com.example.anushasan.R;


public class CancelIntent extends AppCompatActivity {   // please manage the destruction I caused

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cancel_intent);
		String reqcode = getIntent().getStringExtra("reqcode"); // this is always 1, IDK why
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(this, AlertReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, Integer.parseInt(reqcode), intent, PendingIntent.FLAG_UPDATE_CURRENT);
		alarmManager.cancel(pendingIntent);
		Log.e("NSP", "Reached post deletion, code: " + reqcode);
		Toast.makeText(this, "Deletion reqcode: " + reqcode, Toast.LENGTH_SHORT).show();
		// TODO remove from SQLite DB, or else add a boolean value to your DB and set it to false so that if needed it can be recreated just by a reschedule btn;
		finish();
	}
}
