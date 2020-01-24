package com.example.projectploutos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import static android.app.PendingIntent.getActivity;

public class Test extends AppCompatActivity {
    private RequestQueue mQueue;
    private TextView mTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        TextView textView = findViewById(R.id.textView);
        textView.setText(username);
        String password = intent.getStringExtra("password");
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(password);
        String accountType = intent.getStringExtra("account");
        TextView textView3 = findViewById(R.id.textView3);
        textView3.setText(accountType);
        mTextViewResult = findViewById(R.id.textView4);
        mQueue = MySingleton.getInstance(this).getRequestQueue();

        Random rnd = new Random();
        String url = "https://superheroapi.com/api/2577189005834916/" + String.valueOf(rnd.nextInt(731) + 1);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String name = response.getString("name");
                            String real = response.getJSONObject("biography").getString("full-name");
                            String birthplace = response.getJSONObject("biography").getString("place-of-birth");
                            mTextViewResult.setText("\n" + name + ", " + real + ", " + birthplace + "\n");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}