package com.example.anushasan;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TuesdayAdapter extends RecyclerView.Adapter<TuesdayAdapter.TuesdayViewHolder> {

	private Context mContext;
	private Cursor mCursor;

	public TuesdayAdapter(Context context, Cursor cursor) {
		mContext = context;
		mCursor = cursor;
	}

	@NonNull
	@Override
	public TuesdayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.tues_card, parent, false);
		return new TuesdayViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull TuesdayViewHolder holder, int position) {

		if (!mCursor.moveToPosition(position)) {
			return;
		}

		String name = mCursor.getString(mCursor.getColumnIndex(TuesdayContract.SubjectEntry.COLUMN_NAME));
		String time = mCursor.getString(mCursor.getColumnIndex(TuesdayContract.SubjectEntry.COLUMN_TIME));
		int id = mCursor.getInt(mCursor.getColumnIndex(TuesdayContract.SubjectEntry._ID));

		holder.nameText.setText(name);
		holder.timeText.setText(time);
		holder.itemView.setTag(id);
	}

	@Override
	public int getItemCount() {
		return mCursor.getCount();
	}

	public void swapCursor(Cursor newCursor) {
		if (mCursor != null) {
			mCursor.close();
		}

		mCursor = newCursor;

		if (newCursor != null) {
			notifyDataSetChanged();
		}
	}

	public class TuesdayViewHolder extends RecyclerView.ViewHolder {

		public TextView nameText;
		public TextView timeText;

		public TuesdayViewHolder(@NonNull View itemView) {
			super(itemView);

			nameText = itemView.findViewById(R.id.tues_name_item);
			timeText = itemView.findViewById(R.id.tues_time_item);
		}
	}
}
