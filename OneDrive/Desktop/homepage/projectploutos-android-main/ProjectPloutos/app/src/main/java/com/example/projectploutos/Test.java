package com.example.projectploutos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Test extends AppCompatActivity {

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
    }
}
