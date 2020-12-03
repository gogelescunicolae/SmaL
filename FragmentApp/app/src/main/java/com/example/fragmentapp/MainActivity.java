 package com.example.fragmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fragmentapp.firebase.login_act;
import com.example.fragmentapp.fragment_room.LoginActivitityLab5;
import com.example.fragmentapp.past.MainActivity1;

public class MainActivity extends AppCompatActivity {
Button bt1,bt2,bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bt1=findViewById(R.id.fire);
        bt2=findViewById(R.id.fire2);
        bt3=findViewById(R.id.fire3);
        setOnClick();
    }
    public void setOnClick(){
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, login_act.class);
                startActivity(i);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, LoginActivitityLab5.class);
                startActivity(i);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, MainActivity1.class);
                startActivity(i);
            }
        });
    }
}