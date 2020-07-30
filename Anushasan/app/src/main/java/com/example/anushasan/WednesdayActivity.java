package com.example.anushasan;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class WednesdayActivity extends AppCompatActivity {

    private static final String TAG = "JSON";
    String handle;
    String rating;
    String friendOfCount;
    String firstName;
    String rank;
    String maxRank;
    String maxRating;
    EditText userHandleEDT;
    TextView textView;
    Button searchInfoBTN;
    public static String encryptThisString(String input) {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                System.out.println("KUCH RESULT TO BANA HAI>..............................<");
                return result;

            } catch (Exception e) {
                Log.i("//////******************************", "doInBackground: printing stacktrace *************************************************");
                e.printStackTrace();
                return "-1";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//			Log.i(TAG, "onPostExecute: " + s);
//			System.out.println(s);
            if (s.equals("-1")) {
                Toast.makeText(WednesdayActivity.this, "Incorrect username! Try again..", Toast.LENGTH_SHORT).show();
            }
            try {
                JSONObject jsonObject = new JSONObject(s);
                String statusInfo = jsonObject.getString("status");

                if (statusInfo.equals("OK")) {
                    System.out.println(statusInfo);
                    String userInfo = jsonObject.getString("result");
                    Log.i(TAG, "onPostExecute: " + userInfo);

                    JSONArray array = new JSONArray(userInfo);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject userDetails = array.getJSONObject(i);
                        Log.i(TAG, "handle: " + userDetails.getString("handle"));

                        handle = userDetails.getString("handle");

                        rating = userDetails.getString("rating");

                        friendOfCount = userDetails.getString("friendOfCount");
                        try {
                            firstName = userDetails.getString("firstName");
                        } catch (JSONException e1)
                        {
                            firstName = "Not Found";
                        }


                        rank = userDetails.getString("rank");

                        maxRank = userDetails.getString("maxRank");

                        maxRating = userDetails.getString("maxRating");

                        Intent intent = new Intent(getApplication(), CodeforcesUserInfo.class);
                        intent.putExtra("handle",handle);
                        intent.putExtra("rating",rating);
                        intent.putExtra("friendOfCount",friendOfCount);
                        intent.putExtra("firstName",firstName);
                        intent.putExtra("rank",rank);
                        intent.putExtra("maxRank",maxRank);
                        intent.putExtra("maxRating",maxRating);
                        startActivity(intent);
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wednesday);
//        final String SearchHandle;
        searchInfoBTN = findViewById(R.id.searchInfoBTN);
        userHandleEDT = findViewById(R.id.userHandleEDT);
        textView = findViewById(R.id.textView);

        searchInfoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadTask task = new DownloadTask();
                String xxx = "97d3791c7c19984cdb8e9b7cc88affb5e4ca0713";
                String yyy = "4032048cba0bc1ac84705576bdb55222a3d46e2e";

                Long tsLong = System.currentTimeMillis() / 1000;
                String ts = tsLong.toString();

                String toHashString = "525252/contest.hacks?apiKey=" + xxx + "&contestId=1382&time=" + ts + "#" + yyy;

//		MessageDigest md = null;
//		try {
//			md = MessageDigest.getInstance("SHA-512");
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		byte[] digest = md.digest(toHashString.getBytes());
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < digest.length; i++) {
//			sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
//		}
//		System.out.println("Pehli hashString /////////////////// /////////// //////////// // "+sb);

                String hashResult = encryptThisString(toHashString);

                System.out.println("dusri hashString /////////////////// /////////// //////////// // " + hashResult);

//		encryptThisString(s1)

                task.execute("https://codeforces.com/api/user.info?handles=" + userHandleEDT.getText().toString());


            }
        });

        Log.i("wednesday", "onCreate: wednesday is clicked");

    }


//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.add_subject_menu, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//		if (item.getItemId() == R.id.add_subject_menu)
//			Toast.makeText(this, "Add a subject", Toast.LENGTH_SHORT).show();
//		return super.onOptionsItemSelected(item);
//	}
}
