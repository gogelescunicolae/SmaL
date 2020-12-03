package com.example.fragmentapp.fragment_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fragmentapp.Data.UserDatabase;
import com.example.fragmentapp.Data.UserModelDAO;
import com.example.fragmentapp.Model.UserModel;
import com.example.fragmentapp.R;
import com.example.fragmentapp.past.Preferen;

public class LoginActivitityLab5 extends AppCompatActivity {

    UserModelDAO db;
    UserDatabase database1;
    EditText username,password;
    Button login_btn, textViewRegister,nx;
    CheckBox saveDataStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activitity_lab5);

        SharedPreferences sharedPreferences = getSharedPreferences("SH_lab5",MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);

        initializeUI();
        if (isLogin){
            startActivity(new Intent(LoginActivitityLab5.this, MainActivity.class));
            finish();
        }
       database1= Room.databaseBuilder(this, UserDatabase.class, "UserModel1")
                .allowMainThreadQueries()
                .build();
        db = database1.getUserDao();
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivitityLab5.this, RegisterActivityLab5.class));
            }
        });
        nx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivitityLab5.this, Preferen.class));
            }
        });
        callLogin();
    }

    private void initializeUI(){
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login_btn=findViewById(R.id.login_btn);
        saveDataStatus=findViewById(R.id.checkBoxSave);
        textViewRegister=findViewById(R.id.textViewRegister);
        nx=findViewById(R.id.past);
    }

    public void callLogin(){
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  userName = username.getText().toString().trim();
                String  passWord = password.getText().toString().trim();

                UserModel user  = db.getUser(userName,passWord);
                if(user!=null){
                    if(saveDataStatus.isChecked()) {
                        SharedPreferences sharedpreferences = getSharedPreferences("SH_lab5", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean("isLogin", true);
                        editor.putString("USERNAME", userName);
                        editor.putString("PASSWORD", passWord);
                        editor.apply();
                    }
                    Intent i = new Intent(LoginActivitityLab5.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(LoginActivitityLab5.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}