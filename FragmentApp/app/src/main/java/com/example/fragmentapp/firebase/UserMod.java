package com.example.fragmentapp.firebase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class UserMod implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;
    private String email;
    private String password;
    static int countUser=0;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserMod(String email, String password) {
        this.email = email;
        this.password = password;
        countUser++;
    }

    public static int getCountUser() {
        return countUser;
    }

    public static void setCountUser(int countUser) {
        UserMod.countUser = countUser;
    }

    @Override
    public String toString() {
        return "UserModel1{" +
                "ID=" + ID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
