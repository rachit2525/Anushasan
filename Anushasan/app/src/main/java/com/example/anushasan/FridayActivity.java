package com.example.anushasan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class FridayActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private Button timePickerButton;
    private Button addSubjectButton;
    private TextView timeTextView;
    private EditText subjectEditText;

    ArrayList<SubjectCardItem> subjectCardItemArrayList;

    private RecyclerView mRecyclerView;
    private SubjectAddAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int REQUEST_CODE_FOR_EACH_SUBJECT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friday);

        timePickerButton = (Button) findViewById(R.id.timePickerButton);
        addSubjectButton = (Button) findViewById(R.id.addSubjectButton);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        subjectEditText = (EditText) findViewById(R.id.subjectEditText);

        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });

        createSubjectCardItemArrayList();
        buildRecyclerView();

        addSubjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //REQUEST_CODE_FOR_EACH_SUBJECT++;
                String subNAME = subjectEditText.getText().toString();
                String subTIME = timeTextView.getText().toString();
                if(subNAME.length()>=1) {
                    insertSubjectItem(subNAME, subTIME);
                }
                else
                    Toast.makeText(FridayActivity.this, "Please enter a valid Subject Name", Toast.LENGTH_SHORT).show();
                subjectEditText.setText("");
                timeTextView.setText("00:00");
            }
        });

    }

    public void insertSubjectItem(String subNAME,String subTIME) {
        subjectCardItemArrayList.add(new SubjectCardItem(subNAME,subTIME));
        mAdapter.notifyDataSetChanged();
    }

    private void createSubjectCardItemArrayList() {
        subjectCardItemArrayList = new ArrayList<>();
        //subjectCardItemArrayList.add(new SubjectCardItem("Computer Organization","9:00"));
        //subjectCardItemArrayList.add(new SubjectCardItem("Automata", "10:00"));
//        subjectCardItemArrayList.add(new SubjectCardItem("graph","10:00"));
//        subjectCardItemArrayList.add(new SubjectCardItem("automata lab","10:00"));
//        subjectCardItemArrayList.add(new SubjectCardItem("shell lab","10:00"));
//        subjectCardItemArrayList.add(new SubjectCardItem("scientific computing","10:00"));
//        subjectCardItemArrayList.add(new SubjectCardItem("algorithms","10:00"));
//        subjectCardItemArrayList.add(new SubjectCardItem("communications Foundations","10:00"));
//        subjectCardItemArrayList.add(new SubjectCardItem("Comm. lab","10:00"));
//        subjectCardItemArrayList.add(new SubjectCardItem("Algorithms lab","10:00"));
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.friday_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SubjectAddAdapter(subjectCardItemArrayList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemLongClickListener(new SubjectAddAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClicked(int position) {
                Toast.makeText(FridayActivity.this, subjectCardItemArrayList.get(position).getSubjectName() +" removed Succefully", Toast.LENGTH_SHORT).show();
                subjectCardItemArrayList.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String minuteString = "", hourString = "";
        if(minute<10)
            minuteString = "0"+ minute;
        else
            minuteString = String.valueOf(minute);
        //if() we can play with adding AM and PM to classes and same for hours also //////////////////////
        String timeSet = hourOfDay+":"+minuteString;
        timeTextView.setText(timeSet);

        // yahan notification de rha
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
        intent.putExtra("time",timeTextView.getText().toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE_FOR_EACH_SUBJECT, intent, 0);
        if (mCalender.before(Calendar.getInstance())) {
            mCalender.add(Calendar.MINUTE, 1);
        }
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, mCalender.getTimeInMillis(),
                AlarmManager.INTERVAL_FIFTEEN_MINUTES/15*2, pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_subject_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_subject_menu)
            Toast.makeText(this, "Tap and hold on a class to remove it from the Time-Table", Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }
}
