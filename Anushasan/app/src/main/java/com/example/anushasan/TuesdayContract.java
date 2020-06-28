package com.example.anushasan;

import android.provider.BaseColumns;

public class TuesdayContract {

    private TuesdayContract() {}

    public static final class  SubjectEntry implements BaseColumns{
        public static final String TABLE_NAME = "tuesdaySubjectList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_TIMESTAMP= "timestamp";


    }
}
