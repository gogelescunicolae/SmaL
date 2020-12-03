package com.example.fragmentapp.fragment_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentapp.Data.UserDatabase;
import com.example.fragmentapp.Data.UserModelDAO;
import com.example.fragmentapp.Model.UserModel;
import com.example.fragmentapp.R;

import java.util.List;

public class EditData extends AppCompatActivity {
    TextView idEdit,idEd;
    Button btnChange;
    EditText dataEdit,nEd;
    UserModelDAO db;
    UserDatabase database;
    String idEmail,idName;
    private List<UserModel> userModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        Intent intent = getIntent();

        idEmail=intent.getStringExtra("IDedit");
        idName=intent.getStringExtra("IDedit1");
        idEdit= findViewById(R.id.IDEmailAccount);

        btnChange =findViewById(R.id.changeEmailAccount);
        dataEdit= findViewById(R.id.newDataEmail);

        nEd=findViewById(R.id.newDataName);
        idEdit.setText(idEmail);
        database = Room.databaseBuilder(EditData.this, UserDatabase.class, "UserModel")
                .allowMainThreadQueries()
                .build();
        db = database.getUserDao();

        updateData();

    }

    public void updateData(){
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(dataEdit.getText().toString() != null) {
                   db.updateUsername(dataEdit.getText().toString(), idEmail);
                   Toast.makeText(EditData.this, "Date!", Toast.LENGTH_SHORT).show();
               }
               if(nEd.getText().toString() != null) {
                   db.updatePassword(nEd.getText().toString(), idEmail);
                   Toast.makeText(EditData.this, "Date schimbate cu succes!", Toast.LENGTH_SHORT).show();
               }
                startActivity(new Intent(EditData.this, MainActivity.class));
                finish();
            }
        });
    }
}