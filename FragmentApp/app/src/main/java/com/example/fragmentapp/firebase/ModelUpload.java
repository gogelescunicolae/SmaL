package com.example.fragmentapp.firebase;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


import com.example.fragmentapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModelUpload extends AppCompatActivity {

    EditText textt,numarr;
    Switch valBool;
    Button butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_upload);

        textt=findViewById(R.id.textModel);
        numarr=findViewById(R.id.numarModel);
        valBool=findViewById(R.id.switch_model);
        butt=findViewById(R.id.button_upload_model);

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");
                Toast.makeText(ModelUpload.this, "succes", Toast.LENGTH_SHORT).show();
                AnotherModelFirebase md=new AnotherModelFirebase(textt.getText().toString(),Integer.parseInt(numarr.getText().toString()),false);
                myRef.child(String.valueOf(System.currentTimeMillis())).setValue(md);
            }
        });

    }
}