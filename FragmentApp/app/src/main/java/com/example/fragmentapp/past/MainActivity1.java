package com.example.fragmentapp.past;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fragmentapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {


    private String extraInfo;
    private RecyclerView exampleListRv;
    private ListAdapter listExampleAdapter;
    private List<DataM> exampleModelList;
    private Button ad,ad1,n1,n2;
    private Button st,st1,next;
    private EditText name,user,age,poz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        initializeViews();
        Intent intent = getIntent();
        extraInfo = intent.getStringExtra(AppConstants.NAVIGATION_KEY_1);
        initializeList();
        setRecyclerView();
        ad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poz.setVisibility(View.GONE);
                st.setVisibility(View.GONE);
                name.setVisibility(View.VISIBLE);
                user.setVisibility(View.VISIBLE);
                age.setVisibility(View.VISIBLE);
                ad.setVisibility(View.VISIBLE);
            }
        });
        st1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setVisibility(View.GONE);
                user.setVisibility(View.GONE);
                age.setVisibility(View.GONE);
                ad.setVisibility(View.GONE);
                poz.setVisibility(View.VISIBLE);
                st.setVisibility(View.VISIBLE);
            }
        });

ad.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        addOneRow(v);
    }
});
st.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        removeRow(v);
    }
});
next.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity1.this, Preferen.class);
        startActivity(intent);
    }
});
n1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity1.this, File.class);
        startActivity(intent);
    }
});

    }
public void removeRow(View view)
{
    String value= poz.getText().toString();
    int finalValue=Integer.parseInt(value);
    exampleModelList.remove(finalValue);
    Toast.makeText(MainActivity1.this, "Stergere reusita cu succes", Toast.LENGTH_SHORT).show();
    poz.setText("");
    refreshRV();
}

    public void addOneRow(View view) {

        addRow();

        refreshRV();

    }
    private void addRow() {
        String value= age.getText().toString();
        int finalValue=Integer.parseInt(value);
        DataM rModel = new DataM(name.getText().toString(),user.getText().toString(),finalValue);
        exampleModelList.add(rModel);
        Toast.makeText(MainActivity1.this, "Adaugare reusita cu succes", Toast.LENGTH_SHORT).show();
        name.setText("");
        user.setText("");
        age.setText("");
    }

    private void refreshRV() {

        exampleListRv.getAdapter().notifyItemInserted(getSizeOfList());

        exampleListRv.smoothScrollToPosition(getSizeOfList());

    }
    private int getSizeOfList() {

        return exampleModelList.size();

    }
    private void initializeList()
    {
        exampleModelList = new ArrayList<>();
        exampleModelList.add(new DataM("Popescu", "Ana", 23));
        exampleModelList.add(new DataM("Escu", "Pavel", 19));
        exampleModelList.add(new DataM("Dinescu", "Paul", 33));
        exampleModelList.add(new DataM("Popescu", "Ana", 23));
        exampleModelList.add(new DataM("Escu", "Pavel", 19));
        exampleModelList.add(new DataM("Dinescu", "Paul", 33));
        exampleModelList.add(new DataM("Popescu", "Ana", 23));
        exampleModelList.add(new DataM("Escu", "Pavel", 19));
        exampleModelList.add(new DataM("Dinescu", "Paul", 33));
    }

    private void setRecyclerView()
    {
        listExampleAdapter = new ListAdapter(exampleModelList);
        exampleListRv.setLayoutManager(new LinearLayoutManager( this));
        exampleListRv.setAdapter(listExampleAdapter);
    }

    private void initializeViews()
    {
        n1=findViewById(R.id.next_file);
        n2=findViewById(R.id.next_room);
        next=findViewById(R.id.next);
        st1=findViewById(R.id.st1);
        ad1=findViewById(R.id.ad1);
        st=findViewById(R.id.sterge);
        ad=findViewById(R.id.adauga);
        poz=findViewById(R.id.tv_new_poz);
        name=findViewById(R.id.tv_new_name);
        user=findViewById(R.id.tv_new_username);
        age=findViewById(R.id.tv_new_age);
        exampleListRv = findViewById(R.id.rv_test_list);
    }
}