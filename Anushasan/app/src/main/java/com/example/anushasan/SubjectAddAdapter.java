package com.example.anushasan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anushasan.utility.LetterImageView;

import java.util.ArrayList;

public class SubjectAddAdapter extends RecyclerView.Adapter<SubjectAddAdapter.SubjectViewHolder> {

    OnItemLongClickListener mListener;

    public interface OnItemLongClickListener {
        void onItemLongClicked(int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mListener) {
        this.mListener = mListener;
    }

    private ArrayList<SubjectCardItem> mSubjectList;

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {

        public LetterImageView subjectLetterImageView;
        public TextView subjectNameTextView;
        public TextView subjectTimeTextView;

        public SubjectViewHolder(@NonNull View itemView, final OnItemLongClickListener listener) {
            super(itemView);
            subjectNameTextView = itemView.findViewById(R.id.subjectNameTextView);
            subjectTimeTextView = itemView.findViewById(R.id.subjectTimeTextView);
            subjectLetterImageView = itemView.findViewById(R.id.subjectLetterImageView);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemLongClicked(position);
                        }
                    }
                    return false;
                }
            });
        }
    }

    public SubjectAddAdapter(ArrayList<SubjectCardItem> subjectList) {
        mSubjectList = subjectList;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_subject_card, parent, false);
        SubjectViewHolder subVH = new SubjectViewHolder(v,mListener);
        return subVH;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        SubjectCardItem currentSubjectItem = mSubjectList.get(position);

        holder.subjectNameTextView.setText(currentSubjectItem.getSubjectName());
        if(currentSubjectItem.getSubjectName().length()>=1) {
            holder.subjectLetterImageView.setLetter(currentSubjectItem.getSubjectName().charAt(0));
            holder.subjectLetterImageView.setOval(true);
        }
        holder.subjectTimeTextView.setText(currentSubjectItem.getSubjectTime());


    }

    @Override
    public int getItemCount() {
        return mSubjectList.size();
    }
}
