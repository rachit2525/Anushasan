package com.example.anushasan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.anushasan.utility.LetterImageView;


public class ScheduleFragment extends Fragment {

	View view;
	private ListView listView;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_schedule, container, false);

		setupUIViews();
		setupListView();

		return view;
	}

	private void setupListView() {
		String[] week = getResources().getStringArray(R.array.Week);

		ScheduleAdapter adapter = new ScheduleAdapter(this, R.layout.each_day_cardview, week);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent;
				switch (position) {
					case 0:
						Toast.makeText(getContext(), "Monday", Toast.LENGTH_SHORT).show();
						intent = new Intent(getActivity(), MondayActivity.class);
						startActivity(intent);
						break;
					case 1:
						Toast.makeText(getContext(), "Tuesday", Toast.LENGTH_SHORT).show();
						intent = new Intent(getActivity(), TuesdayActivity.class);
						startActivity(intent);
						break;
					case 2:
						Toast.makeText(getContext(), "Wednesday", Toast.LENGTH_SHORT).show();
						intent = new Intent(getActivity(), WednesdayActivity.class);
						startActivity(intent);
						break;
					case 3:
						Toast.makeText(getContext(), "Thursday", Toast.LENGTH_SHORT).show();
						intent = new Intent(getActivity(), ThursdayActivity.class);
						startActivity(intent);
						break;
					case 4:
						Toast.makeText(getContext(), "Friday", Toast.LENGTH_SHORT).show();
						intent = new Intent(getActivity(), FridayActivity.class);
						startActivity(intent);
						break;
					case 5:
						Toast.makeText(getContext(), "Saturday", Toast.LENGTH_SHORT).show();
						intent = new Intent(getActivity(), SaturdayActivity.class);
						startActivity(intent);
						break;
					default:
						break;

				}
			}
		});

	}

	private void setupUIViews() {
		listView = (ListView) view.findViewById(R.id.schedule_listView);
	}

	private class ScheduleAdapter extends ArrayAdapter {

		private int resource;
		private LayoutInflater layoutInflater;
		private String[] schedule;

		public ScheduleAdapter(ScheduleFragment scheduleFragment, int resource, String[] objects) {
			super(scheduleFragment.getContext(), resource, objects);
			this.resource = resource;
			this.schedule = objects;
			layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		}

		@NonNull
		@Override
		public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = layoutInflater.inflate(resource, null);
				holder.days_LetterImageView = (LetterImageView) convertView.findViewById(R.id.days_LetterImageView);
				holder.days_textView = (TextView) convertView.findViewById(R.id.days_textView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.days_LetterImageView.setOval(true);
			holder.days_LetterImageView.setLetter(schedule[position].charAt(0));
			holder.days_textView.setText(schedule[position]);
			return convertView;

		}

		class ViewHolder {
			private LetterImageView days_LetterImageView;
			private TextView days_textView;

		}
	}

}
