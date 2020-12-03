package com.example.fragmentapp.firebase;

public class User {
    private String yahoo;
    private String password;

    public User() {
    }

    public User(String yahoo, String password) {
        this.yahoo = yahoo;
        this.password = password;

    }



    public String getYahoo() {
        return yahoo;
    }

    public void setYahoo(String yahoo) {
        this.yahoo = yahoo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
