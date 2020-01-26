package com.example.projectploutos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountDao {

    @Delete
    void delete(Credential credential);

    @Insert
    public void addAccount(Credential credential);

    @Query("select * from credentials")
    public List<Credential> getCredentials();
}
