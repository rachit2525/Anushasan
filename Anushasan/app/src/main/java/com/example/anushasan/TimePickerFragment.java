package com.example.anushasan;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class TimePickerFragment extends DialogFragment {
	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		Date date = c.getTime();

//        int mDay = (Calendar.DAY_OF_WEEK_IN_MONTH);
		String dDay = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
		Log.d(TAG, "Current Day: " + dDay + " /////////////////////////////////////////////////////////////////////////////////////////////////////////////");

		return new TimePickerDialog(getActivity(),
				(TimePickerDialog.OnTimeSetListener) getActivity(),
				hour, minute, DateFormat.is24HourFormat(getActivity()));
	}
}
