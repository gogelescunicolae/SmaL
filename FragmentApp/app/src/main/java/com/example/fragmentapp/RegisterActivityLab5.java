package com.example.fragmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentapp.Data.UserDatabase;
import com.example.fragmentapp.Data.UserModelDAO;
import com.example.fragmentapp.Model.UserModel;

public class RegisterActivityLab5 extends AppCompatActivity {

    EditText editTextUsername, editTextEmail, editTextPassword, editTextCnfPassword,editTextAge;
    Button buttonRegister,textViewLogin;

    private UserModelDAO userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_lab5);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAge = findViewById(R.id.editTextAge);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextCnfPassword = findViewById(R.id.editTextCnfPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        textViewLogin = findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivityLab5.this, LoginActivitityLab5.class));
            }
        });
        userDao = Room.databaseBuilder(this, UserDatabase.class, "UserModel").allowMainThreadQueries()
                .build().getUserDao();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUsername.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String passwordConf = editTextCnfPassword.getText().toString().trim();
                int age = Integer.parseInt(editTextAge.getText().toString().trim());
                if (password.equals(passwordConf)) {
                    UserModel user = new UserModel(email,password,userName,age);
                    userDao.insert(user);
                    SharedPreferences sharedpreferences = getSharedPreferences("SH_lab5",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                 //   editor.putBoolean("isLogin", true);
                //    editor.putString("USERNAME", userName);
                //    editor.putString("EMAIL", email);
                 //   editor.putString("PASSWORD", password);
                 //   editor.putInt("AGE", age);
                    editor.apply();
                    Toast.makeText(RegisterActivityLab5.this, "Cont creat cu succes", Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new Intent(RegisterActivityLab5.this, LoginActivitityLab5.class);
                    startActivity(moveToLogin);

                } else {
                    Toast.makeText(RegisterActivityLab5.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}