package com.example.projectploutos;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder>{

    private List<Account> accountList;

    public AccountAdapter(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_list_row, parent, false);
        return new AccountViewHolder(itemView);
    }

   @Override
    public void onBindViewHolder(AccountViewHolder holder, int position){
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        holder.accountName.setText(accountList.get(position).getAccountName());
        holder.balance.setText(fmt.format(accountList.get(position).getBalance()));
    }

   @Override
    public int getItemCount() {
        return accountList.size();
    }

    public double totalBalance() {
        double total = 0;
        for(int i=0; i<accountList.size(); i++) {
            total += accountList.get(i).getBalance();
        }
        return total;
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {
        public TextView accountName;
        public TextView balance;

        public AccountViewHolder(View view) {
            super(view);
            accountName = (TextView) view.findViewById(R.id.accountName);
            balance = (TextView) view.findViewById(R.id.balance);
        }
    }
}
