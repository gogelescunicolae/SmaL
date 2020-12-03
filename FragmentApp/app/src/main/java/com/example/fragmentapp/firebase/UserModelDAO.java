package com.example.fragmentapp.firebase;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface UserModelDAO {

    @Query("SELECT * FROM UserMod WHERE email=:email AND password=:password")
    UserMod getUser(String email, String password);

}
