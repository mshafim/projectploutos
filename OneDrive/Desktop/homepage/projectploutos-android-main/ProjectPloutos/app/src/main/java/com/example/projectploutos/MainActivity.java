package com.example.projectploutos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Account> accountList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AccountAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new AccountAdapter(accountList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        initAccountData();

        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        TextView total = (TextView) findViewById(R.id.totalBalance_text);
        total.setText("Total Balance: " + fmt.format(mAdapter.totalBalance()));
    }
    private void initAccountData() {
        Account account = new Account("Venmo", 20);
        accountList.add(account);

        account = new Account("Checkings", 150);
        accountList.add(account);

        account = new Account("Zelle", 45);
        accountList.add(account);

        account = new Account("CashApp", 30);
        accountList.add(account);
//
//        account = new Account("Checking Account", 150);
//        accountList.add(account);
//
//        account = new Account("Zelle", 45);
//        accountList.add(account);
//
//        account = new Account("Cashapp", 30);
//        accountList.add(account);
//
//        account = new Account("Checking Account", 150);
//        accountList.add(account);
//
//        account = new Account("Zelle", 45);
//        accountList.add(account);
//
//        account = new Account("Cashapp", 30);
//        accountList.add(account);

        mAdapter.notifyDataSetChanged();

    }

    /** Called when the user taps the Send button */
    public void addAccount(View view) {
        Intent intent = new Intent(this, AddAccount.class);
        startActivity(intent);
    }


}
