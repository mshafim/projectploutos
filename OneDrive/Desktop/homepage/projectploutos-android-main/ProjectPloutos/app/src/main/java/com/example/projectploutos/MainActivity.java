package com.example.projectploutos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Account> accountList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AccountAdapter mAdapter;
    private RequestQueue mQueue;
    public static AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "accountdb").fallbackToDestructiveMigration().allowMainThreadQueries().build();
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
        mQueue = MySingleton.getInstance(this).getRequestQueue();
        List<Account> accounts = appDatabase.accountDao().getAccounts();
        for(Account c:accounts){
            accountList.add(c);
            mAdapter.notifyDataSetChanged();
        }
    }

    /** Called when the user taps the Send button */
    public void addAccount(View view) {
        Intent intent = new Intent(this, AddAccount.class);
        startActivity(intent);
    }
}
