package com.example.anushasan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.anushasan.TuesdayContract.SubjectEntry;

public class TuesdayDBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "tuesdaySubjectList.db";
	public static final int DATABASE_VERSION = 1;

	public TuesdayDBHelper(@Nullable Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		final String SQL_CREATE_TUESDAYLIST_TABLE = "CREATE TABLE " +
				SubjectEntry.TABLE_NAME + " (" +
				SubjectEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				SubjectEntry.COLUMN_NAME + " TEXT NOT NULL, " +
				SubjectEntry.COLUMN_TIME + " TEXT NOT NULL, " +
				SubjectEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
				");";

		db.execSQL(SQL_CREATE_TUESDAYLIST_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + SubjectEntry.TABLE_NAME);
		onCreate(db);
	}
}
