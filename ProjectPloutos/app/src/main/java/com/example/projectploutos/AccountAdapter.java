package com.example.projectploutos;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder>{

    private Context context;
    private List<Account> accountList;

    public AccountAdapter(List<Account> accountList, Context context) {
        this.accountList = accountList;
        this.context = context;
    }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_list_row, parent, false);
        return new AccountViewHolder(itemView);
    }

   @Override
    public void onBindViewHolder(AccountViewHolder holder, final int position){
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        holder.accountName.setText(accountList.get(position).getAccountName());
        holder.balance.setText(fmt.format(accountList.get(position).getBalance()));
        switch(accountList.get(position).getAccountName()) {
            case "Checkings":
                holder.image.setImageResource(R.drawable.checkings);
                break;
            case "Savings":
                holder.image.setImageResource(R.drawable.savings);
                break;
            case "Venmo":
                holder.image.setImageResource(R.drawable.venmo);
                break;
            case "CashApp":
                holder.image.setImageResource(R.drawable.cashapp);
                break;
            case "Zelle":
                holder.image.setImageResource(R.drawable.zelle);
                break;
        }
       holder.image.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               switch(accountList.get(position).getAccountName()) {
                   case "Checkings":
                       try{
                            Toast.makeText(context, "Opening Capital One Mobile", Toast.LENGTH_SHORT).show();
                            Intent i = context.getPackageManager().getLaunchIntentForPackage("com.konylabs.capitalone");
                            context.startActivity(i);}
                       catch(Exception e) {
                            Toast.makeText(context, "Capital One Mobile not installed", Toast.LENGTH_SHORT).show();
                        }
                       break;

                   case "Savings":
                       try {
                           Toast.makeText(context, "Opening Capital One Mobile!", Toast.LENGTH_SHORT).show();
                           Intent k = context.getPackageManager().getLaunchIntentForPackage("com.konylabs.capitalone");
                           context.startActivity(k);
                       }
                       catch(Exception e){
                           Toast.makeText(context, "Capital One Mobile not installed", Toast.LENGTH_SHORT).show();
                       }
                       break;
                   case "Venmo":
                       try {
                           Toast.makeText(context, "Opening Venmo", Toast.LENGTH_SHORT).show();
                           Intent j = context.getPackageManager().getLaunchIntentForPackage("com.venmo");
                           context.startActivity(j);
                       }
                       catch(Exception e){
                           Toast.makeText(context, "Venmo not installed", Toast.LENGTH_SHORT).show();
                       }
                       break;
                   case "CashApp":
                       try {
                           Toast.makeText(context, "Opening Cash App", Toast.LENGTH_SHORT).show();
                           Intent l = context.getPackageManager().getLaunchIntentForPackage("com.squareup.cash");
                           context.startActivity(l);
                       }
                       catch(Exception e){
                           Toast.makeText(context, "Cash App not installed", Toast.LENGTH_SHORT).show();
                       }
                       break;
                   case "Zelle":
                       try {
                           Toast.makeText(context, "Opening Zelle", Toast.LENGTH_SHORT).show();
                           Intent m = context.getPackageManager().getLaunchIntentForPackage("com.zellepay.zelle");
                           context.startActivity(m);
                       }
                       catch(Exception e) {
                           Toast.makeText(context, "Zelle not installed", Toast.LENGTH_SHORT).show();
                       }
                       break;
               }
           }
       });
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
        public ImageView image;

        public AccountViewHolder(View view) {
            super(view);
            accountName = (TextView) view.findViewById(R.id.accountName);
            balance = (TextView) view.findViewById(R.id.balance);
            image = (ImageView) view.findViewById(R.id.image);
            image.setClickable(true);
        }
    }
}
