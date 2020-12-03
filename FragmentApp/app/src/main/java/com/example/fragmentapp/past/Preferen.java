package com.example.fragmentapp.past;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentapp.R;

public class Preferen extends AppCompatActivity {
EditText t1,t2;
Button b1, b2,b3;
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferen);
        initialize();
        setOnClick();
    }
    private void setOnClick(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(AppConstants.MY_PREFS_NAME,MODE_PRIVATE).edit();
                String texn=t1.getText().toString();
                String texu=t2.getText().toString();

                editor.putString(AppConstants.KEY_name, texn);
                editor.putString(AppConstants.KEY_user, texu);
                editor.apply();
                Toast.makeText(Preferen.this, "Adaugare reusita cu succes", Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences(AppConstants.MY_PREFS_NAME, MODE_PRIVATE);
                String name = prefs.getString(AppConstants.KEY_name, "No name defined");
                String user = prefs.getString(AppConstants.KEY_user, "No name defined");
                String all=name+" "+user;
                tv.setText(all);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent intent=new Intent(Preferen.this, File.class);
                startActivity(intent);
            }
        });
    }
private void initialize(){
tv=findViewById(R.id.tv_res);
        t1=findViewById(R.id.tv_new_n);
        t2=findViewById(R.id.tv_new_u);
        b1=findViewById(R.id.ad_pref);
    b2=findViewById(R.id.af_pref);
    b3=findViewById(R.id.af_next);
}
}