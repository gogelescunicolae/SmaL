package com.example.fragmentapp.firebase;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.fragmentapp.firebase.UserModelDAO;
import com.example.fragmentapp.firebase.UserMod;

@Database(entities = {UserMod.class},version = 1,exportSchema = false)
public abstract  class UserDatabase extends RoomDatabase {

    public abstract UserModelDAO getUserDao();
}