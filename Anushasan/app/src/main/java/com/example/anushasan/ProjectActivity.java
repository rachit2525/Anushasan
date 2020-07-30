package com.example.anushasan;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ProjectActivity extends AppCompatActivity {

	ArrayList<ProjectCard> mProjectCard;
	DatePicker picker;
	Button btnGet;
	String date;
	private RecyclerView recycler_view_project;
	private ProjectAdapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	private Button buttonInsert;
	private Button buttonRemove;
	private EditText editTextInsert;
	private EditText editTextRemove;
	private String pro_name;
	private Calendar myCalendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_project);

		createProjectCard();
		buildRecyclerView();

		setButtons();
        /*picker=(DatePicker)findViewById(R.id.datePicker1);
        btnGet=(Button)findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date= picker.getDayOfMonth()+"/"+ (picker.getMonth() + 1)+"/"+picker.getYear();
            }
        });*/

	}

	public void insertItem(int position) {

		mProjectCard.add(position, new ProjectCard(R.drawable.ic_project_card, "Tap to Set Name", "Click to set Date->"));
		mAdapter.notifyItemInserted(position);

	}

	public void removeItem(int position) {

		mProjectCard.remove(position);
		mAdapter.notifyItemRemoved(position);

	}

	public void changeItem(int position, String text) {
		mProjectCard.get(position).changeText1(pro_name);
		mAdapter.notifyItemChanged(position);
	}

	public void changeItem2(int position, String text) {
		mProjectCard.get(position).changeText2(date);
		mAdapter.notifyItemChanged(position);
	}

	public void createProjectCard() {

		mProjectCard = new ArrayList<>();


	}

	public void buildRecyclerView() {

		recycler_view_project = findViewById(R.id.recycler_view_project);
		recycler_view_project.setHasFixedSize(true);
		mLayoutManager = new LinearLayoutManager(this);
		mAdapter = new ProjectAdapter(mProjectCard);

		recycler_view_project.setLayoutManager(mLayoutManager);
		recycler_view_project.setAdapter(mAdapter);

		mAdapter.setOnItemClickListener(new ProjectAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(int position) {


				openDialog();
				changeItem(position, pro_name);
				pro_name = "";
			}

			@Override
			public void onDeleteClick(int position) {
				removeItem(position);
			}

			@Override
			public void onDateClick(int position) {

				init();
				changeItem2(position, date);
			}
		});

	}

	public void setButtons() {
		buttonInsert = findViewById(R.id.button_insert);
		buttonRemove = findViewById(R.id.button_remove);
		editTextInsert = findViewById(R.id.editText_insert);
		editTextRemove = findViewById(R.id.editText_remove);

		buttonInsert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (editTextInsert.getText().toString().trim().length() == 0)
					Toast.makeText(ProjectActivity.this, "Enter the project Number", Toast.LENGTH_LONG).show();

				else {
					int position = Integer.parseInt(editTextInsert.getText().toString());
					if (position > mProjectCard.size() || position < 0)
						Toast.makeText(ProjectActivity.this, "Give position within Range", Toast.LENGTH_LONG).show();
					else
						insertItem(position);
				}

			}
		});

		buttonRemove.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (editTextRemove.getText().toString().trim().length() == 0)
					Toast.makeText(ProjectActivity.this, "Enter the project Number", Toast.LENGTH_LONG).show();

				else {
					int position = Integer.parseInt(editTextRemove.getText().toString());
					if (position >= mProjectCard.size() || position < 0)
						Toast.makeText(ProjectActivity.this, "Give position within Range", Toast.LENGTH_LONG).show();
					else
						removeItem(position);
				}
			}
		});
	}

	private void openDialog() {

		final androidx.appcompat.app.AlertDialog.Builder builderi = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();
		View view = inflater.inflate(R.layout.projectname_dialog, null);
		final EditText proj_name = view.findViewById(R.id.edittext_project_name);

		builderi.setView(view)
				.setTitle("PROJECT NAME")
				.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//System.exit(0);
					}
				})
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (proj_name.getText().toString().trim().length() != 0) {
							pro_name = proj_name.getText().toString();


						} else {
							Toast.makeText(ProjectActivity.this, "Enter Name", Toast.LENGTH_LONG).show();
							openDialog();
						}
					}
				}).create().show();
	}

	public void init() {
		myCalendar = Calendar.getInstance();
		DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {


			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
			                      int dayOfMonth) {
				// TODO Auto-generated method stub
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabel();
			}

		};

		new DatePickerDialog(ProjectActivity.this, date, myCalendar
				.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
				myCalendar.get(Calendar.DAY_OF_MONTH)).show();

	}

	private void updateLabel() {
		String myFormat = "MM/dd/yy"; //In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		date = sdf.format(myCalendar.getTime());
		//edittext.setText(sdf.format(myCalendar.getTime()));
	}
}
