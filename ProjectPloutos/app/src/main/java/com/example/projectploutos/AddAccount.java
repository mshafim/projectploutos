package com.example.projectploutos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class AddAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
    }

    /** Called when the user taps the Sign In button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Test.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String username = editText.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String password = editText2.getText().toString();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String accountType = spinner.getSelectedItem().toString();
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.putExtra("account", accountType);
        startActivity(intent);
    }
}