package com.example.fragmentapp.past;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fragmentapp.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class File extends AppCompatActivity {
    EditText et;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        initializare();
        setOnClick();
    }

        private void initializare(){
        et=findViewById(R.id.et_file);
        b1=findViewById(R.id.bt_ad);
        b2=findViewById(R.id.bt_c);
        }
private void setOnClick(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data=et.getText().toString();
                scriere("file",data);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=citire("file");
                Toast.makeText(File.this,data,Toast.LENGTH_SHORT).show();
            }
        });
}
    private String citire(String ftxt)
    {
        String stringFromFile="";
        try{
            InputStream inputStream=this.openFileInput(ftxt);
            if(inputStream!=null) {
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString="";
                StringBuilder stringBuilder=new StringBuilder();
                while((receiveString=bufferedReader.readLine())!=null){
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                stringFromFile=stringBuilder.toString();
            }

        } catch (FileNotFoundException e) {
            Log.e("declaration avtivity",e.toString());
        }catch (IOException e)
        {
            Log.e("declaration activity",e.toString());
        }
        //Gson gson=new Gson();
        //String data=gson.toJson(stringFromFile);
        return stringFromFile;
    }
    public void scriere(String ftxt, String data){
        try {
           // Gson gson=new Gson();
           // String data=gson.toJson(stringFromFile);
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(this.openFileOutput(ftxt, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast.makeText(this,"declaratie salvata",Toast.LENGTH_SHORT).show();
            }catch (IOException e){
            Toast.makeText(this,"eroare",Toast.LENGTH_SHORT).show();
            Log.e("Exception",e.toString());

        }
    }
}