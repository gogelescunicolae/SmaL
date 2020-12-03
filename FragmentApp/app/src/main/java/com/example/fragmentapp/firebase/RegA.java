package com.example.fragmentapp.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fragmentapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegA extends AppCompatActivity {
Button btn1_log,btn1_reg;
EditText e1,e2;
private User user;
FirebaseDatabase database;
DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initialize();
        database=FirebaseDatabase.getInstance();
        ref=database.getReference().child("User");
        btn1_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yah=e1.getText().toString();
                String pas=e2.getText().toString();
                user=new User(yah,pas);
                ref.child(user.getYahoo()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                            Toast.makeText(RegA.this, "User creat cu succes", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(RegA.this, "error", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
           }
        });
        btn1_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegA.this,login_act.class);
                startActivity(i);
            }
        });

    }
    public void initialize()
    {
        e2=findViewById(R.id.parola_reg);
        e1=findViewById(R.id.yahoo_reg);
        btn1_log=findViewById(R.id.log_btn_reg);
        btn1_reg=findViewById(R.id.reg_btn_reg);
    }
}