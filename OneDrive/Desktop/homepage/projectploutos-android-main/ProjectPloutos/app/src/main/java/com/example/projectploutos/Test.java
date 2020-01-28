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


public class Test extends AppCompatActivity {
    private RequestQueue mQueue;
    private TextView mTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        TextView textView = findViewById(R.id.textView);
        textView.setText("ADDING ACCOUNT");
        final String password = intent.getStringExtra("password");
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText("PLEASE WAIT...");
        final String accountType = intent.getStringExtra("account");
        TextView textView3 = findViewById(R.id.textView3);
        textView3.setText(accountType);
        mTextViewResult = findViewById(R.id.textView4);
        mTextViewResult.setText("SEARCHING");
        mQueue = MySingleton.getInstance(this).getRequestQueue();

        final String url = "http://10.0.2.2:5000/api/" + username + "/" + password;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                       try {
                           Account account = new Account();
                           account.setAccountName(accountType);
                           account.setId(Integer.parseInt(response.getJSONObject("accounts").getString("id")));
                           String balance = response.getJSONObject("accounts").getString("balance");
                           double bal = Double.parseDouble(balance.substring(1));
                           account.setBalance(bal);
                           MainActivity.appDatabase.accountDao().addAccount(account);
                           mTextViewResult.setText(response.getJSONObject("accounts").getString("balance"));
                           Toast.makeText(getApplicationContext(), "Account Added Successfully", Toast.LENGTH_SHORT).show();
                       }
                       catch(JSONException e){e.printStackTrace();}
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Account not found", Toast.LENGTH_SHORT).show();
                error.printStackTrace(); }
        });

        mQueue.add(request);
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}