package com.example.anushasan;

public class SubjectCardItem {
    private String SubjectName;
    private String SubjectTime;

    public SubjectCardItem(String subjectName, String subjectTime) {
        SubjectName = subjectName;
        SubjectTime = subjectTime;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public String getSubjectTime() {
        return SubjectTime;
    }
}
