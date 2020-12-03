package com.example.fragmentapp.Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.fragmentapp.Model.UserModel;

@Database(entities = {UserModel.class},version = 1,exportSchema = false)
public abstract  class UserDatabase extends RoomDatabase {

    public abstract UserModelDAO getUserDao();
}
