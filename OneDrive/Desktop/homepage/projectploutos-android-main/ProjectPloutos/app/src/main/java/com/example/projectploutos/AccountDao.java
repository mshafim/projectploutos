package com.example.projectploutos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountDao {

    @Delete
    void delete(Account account);

    @Insert
    public void addAccount(Account account);

    @Query("select * from accounts")
    public List<Account> getAccounts();
}
