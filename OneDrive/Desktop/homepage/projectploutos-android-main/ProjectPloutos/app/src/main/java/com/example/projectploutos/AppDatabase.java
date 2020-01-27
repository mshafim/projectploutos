package com.example.projectploutos;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Account.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AccountDao accountDao();
}