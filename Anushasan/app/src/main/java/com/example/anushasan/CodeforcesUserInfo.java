package com.example.anushasan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CodeforcesUserInfo extends AppCompatActivity {

    private static final String TAG = "on receive intent";
    String handle;
    String rating;
    String friendOfCount;
    String firstName;
    String rank;
    String maxRank;
    String maxRating;
    TextView handleTV;
    TextView userRating;
    TextView userRank;
    TextView userFriends;
    TextView userFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codeforces_user_info);
        Intent intent = getIntent();
        handle = intent.getStringExtra("handle");
        rating = intent.getStringExtra("rating");
        friendOfCount = intent.getStringExtra("friendOfCount");
        firstName = intent.getStringExtra("firstName");
        rank = intent.getStringExtra("rank");
        maxRank = intent.getStringExtra("maxRank");
        maxRating = intent.getStringExtra("maxRating");

        Log.i(TAG, "handle "+handle);
        Log.i(TAG, "rating "+rating);
        Log.i(TAG, "friendOfCount "+friendOfCount);
        Log.i(TAG, "firstName "+firstName);
        Log.i(TAG, "rank "+rank);

        handleTV = findViewById(R.id.handleTV);
        userRating = findViewById(R.id.userRating);
        userRank = findViewById(R.id.userRank);
        userFriends = findViewById(R.id.userFriends);
        userFirstName = findViewById(R.id.userFirstName);

        handleTV.setText(handle);
        userRating.setText(rating);
        userFriends.setText(friendOfCount);
        userRank.setText(rank);
        userFirstName.setText(firstName);


    }
}
