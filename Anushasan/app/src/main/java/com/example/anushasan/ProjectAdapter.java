package com.example.anushasan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ExViewHolder> {

	private ArrayList<ProjectCard> mProjectCard;
	private OnItemClickListener mListener;

	public ProjectAdapter(ArrayList<ProjectCard> exampleList) {
		mProjectCard = exampleList;
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		mListener = listener;
	}

	@Override
	public ExViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_project, parent, false);
		ExViewHolder evh = new ExViewHolder(v, mListener);
		return evh;
	}

	@Override
	public void onBindViewHolder(@NonNull ExViewHolder holder, int position) {

		ProjectCard currentItem = mProjectCard.get(position);
		holder.mImageView.setImageResource(currentItem.getImageResource());
		holder.mTextView1.setText(currentItem.getText1());
		holder.mTextView2.setText(currentItem.getText2());
	}

	@Override
	public int getItemCount() {
		return mProjectCard.size();
	}

	public interface OnItemClickListener {
		void onItemClick(int position);

		void onDeleteClick(int position);

		void onDateClick(int position);
	}

	public static class ExViewHolder extends RecyclerView.ViewHolder {

		public ImageView mImageView;
		public TextView mTextView1;
		public TextView mTextView2;
		public ImageView mDeleteImage;
		public ImageView mDateImage;

		public ExViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
			super(itemView);
			mImageView = itemView.findViewById(R.id.imageView);
			mTextView1 = itemView.findViewById(R.id.textView1);
			mTextView2 = itemView.findViewById(R.id.textView2);
			mDeleteImage = itemView.findViewById(R.id.image_delete);
			mDateImage = itemView.findViewById(R.id.image_date);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (listener != null) {
						int position = getAdapterPosition();
						if (position != RecyclerView.NO_POSITION) {
							listener.onItemClick(position);
						}
					}
				}
			});

			mDeleteImage.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (listener != null) {
						int position = getAdapterPosition();
						if (position != RecyclerView.NO_POSITION) {
							listener.onDeleteClick(position);
						}
					}
				}
			});

			mDateImage.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (listener != null) {
						int position = getAdapterPosition();
						if (position != RecyclerView.NO_POSITION) {
							listener.onDateClick(position);
						}
					}
				}
			});

		}
	}
}
