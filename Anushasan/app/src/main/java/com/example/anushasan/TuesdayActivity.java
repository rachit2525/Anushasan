package com.example.anushasan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class TuesdayActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private SQLiteDatabase mDatabase;
    private TuesdayAdapter mAdapter;
    private EditText editTextName;
    private TextView textViewTime;
    private Button buttonTime;

    private String name;

    public static int tuesday_request_code=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuesday);

        TuesdayDBHelper dbHelper = new TuesdayDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.tues_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TuesdayAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((int)viewHolder.itemView.getTag());

            }
        }).attachToRecyclerView(recyclerView);


        editTextName = findViewById(R.id.tues_sub_name);
        textViewTime = findViewById(R.id.tues_sub_time);
        buttonTime = findViewById(R.id.tues_timePick);

        Button buttonAdd = findViewById(R.id.tues_add);

        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String minuteString = "", hourString = "";
        if (minute < 10)
            minuteString = "0" + minute;
        else
            minuteString = String.valueOf(minute);
        //if() we can play with adding AM and PM to classes and same for hours also //////////////////////
        String timeSet = hourOfDay + ":" + minuteString;
        textViewTime.setText(timeSet);

        Calendar mCalender = Calendar.getInstance();
        mCalender.set(Calendar.HOUR_OF_DAY,hourOfDay);
        mCalender.set(Calendar.MINUTE,minute);
        mCalender.set(Calendar.SECOND,0);
        mCalender.set(Calendar.MILLISECOND,0);

        startAlarm(mCalender);
    }

    private void startAlarm(Calendar mCalender) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("time",textViewTime.getText().toString());
        intent.putExtra("Subject",editTextName.getText().toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, tuesday_request_code, intent, 0);
        if (mCalender.before(Calendar.getInstance())) {
            mCalender.add(Calendar.MINUTE, 1);
        }
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, mCalender.getTimeInMillis(),
                AlarmManager.INTERVAL_FIFTEEN_MINUTES/5, pendingIntent);
    }

    private void addItem() {

        if(editTextName.getText().toString().trim().length()==0){
            return;
        }

         name = editTextName.getText().toString();
        String dt = textViewTime.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(TuesdayContract.SubjectEntry.COLUMN_NAME, name);
        cv.put(TuesdayContract.SubjectEntry.COLUMN_TIME, dt);

        mDatabase.insert(TuesdayContract.SubjectEntry.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());

        editTextName.getText().clear();
        String temp ="0:00";
        textViewTime.setText(temp);
        tuesday_request_code++;
    }

    private void removeItem(int id)
    {
        mDatabase.delete(TuesdayContract.SubjectEntry.TABLE_NAME,TuesdayContract.SubjectEntry._ID+"="+id,null);
        mAdapter.swapCursor(getAllItems());
    }


    private Cursor getAllItems() {
        return mDatabase.query(
                TuesdayContract.SubjectEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                TuesdayContract.SubjectEntry.COLUMN_TIMESTAMP  + " DESC"
        );
    }
}
