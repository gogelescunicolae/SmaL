package com.example.fragmentapp.firebase;

import androidx.annotation.NonNull;
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

import com.example.fragmentapp.firebase.UserDatabase;
import com.example.fragmentapp.firebase.UserModelDAO;
import com.example.fragmentapp.Model.UserModel;
import com.example.fragmentapp.R;
import com.example.fragmentapp.fragment_room.LoginActivitityLab5;
import com.example.fragmentapp.fragment_room.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_act extends AppCompatActivity {
    Button log,reg;
    EditText yah,pas;
    UserModelDAO db;
    UserDatabase database1;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    CheckBox saveDataStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_act);
        init();

        SharedPreferences sharedPreferences = getSharedPreferences("SH_lab6",MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLog", false);

        database=FirebaseDatabase.getInstance();
        ref=database.getReference().child("User");

        if (isLogin){
            startActivity(new Intent(login_act.this, home.class));
            finish();
        }
        database1= Room.databaseBuilder(this, UserDatabase.class, "UserModel1")
                .allowMainThreadQueries()
                .build();
        db = database1.getUserDao();
        clickButton();
    }
    public void clickButton(){
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login_act.this,RegA.class);
                startActivity(i);
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            String pass_txt;
            @Override
            public void onClick(View v) {
                String yahoo_txt=yah.getText().toString();
                pass_txt=pas.getText().toString();
                UserMod us  = db.getUser(yahoo_txt,pass_txt);

                if(ref.child(yahoo_txt)!=null){
                    if(saveDataStatus.isChecked()) {
                        SharedPreferences sharedpreferences = getSharedPreferences("SH_lab6", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean("isLog", true);
                        editor.putString("YAHOO", yahoo_txt);
                        editor.putString("PASSWORD", pass_txt);
                        editor.apply();
                        Intent i=new Intent(login_act.this,home.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                ref.child(yahoo_txt).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user=snapshot.getValue(User.class);
                            if(pass_txt.equals(user.getPassword())){

                                Toast.makeText(login_act.this,"Login Succesfull",Toast.LENGTH_LONG).show();

                                Intent i=new Intent(login_act.this,home.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(login_act.this,"parola gresita",Toast.LENGTH_LONG).show();
                            }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
                }
                else
                {
                    Toast.makeText(login_act.this,"yahoo gresit",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void init(){
        yah=findViewById(R.id.yahoo);
        pas=findViewById(R.id.parola);
        log= findViewById(R.id.log_btn);
        reg=findViewById(R.id.reg_btn);
        saveDataStatus=findViewById(R.id.checkBoxSave);
    }
}