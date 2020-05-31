package com.example.anushasan;

public class ProjectCard {

    private int mImageResource;
    private String mText1;
    private String mText2;

    public ProjectCard(int ImageResource, String text1, String text2) {
        this.mImageResource = ImageResource;
        this.mText1 = text1;
        this.mText2 = text2;
    }
    public void changeText1(String text){
        mText1=text;
    }

    public void changeText2(String text){
        mText2=text;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }
}
