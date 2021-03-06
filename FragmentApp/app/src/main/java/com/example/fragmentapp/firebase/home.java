package com.example.fragmentapp.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragmentapp.R;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

public class home extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1543;
    private UploadPicturesAdapter mPictureAdapter;
    private Button mUpload,fini,log,nxt,nxt2;
    private RecyclerView mUploadList;
    private ArrayList<Uri> mImagesURIs;
    private StorageReference mStorageRef;
    private Uri mImageUri;

    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeViews();

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        setOnClickListeners();
        mImagesURIs = new ArrayList<>();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null && data.getClipData() != null)
        {
            mImagesURIs.clear();
           ClipData clipData = data.getClipData();
            for(int i = 0; i < clipData.getItemCount(); i++){
                Uri mUri =  clipData.getItemAt(i).getUri();
                mImagesURIs.add(mUri);
            }
            setRecyclerView();
        }
    }

    private void setRecyclerView()
    {
        if (mImagesURIs != null){
            mPictureAdapter = new UploadPicturesAdapter(mImagesURIs);
        }

        mUploadList.setLayoutManager(new LinearLayoutManager(this));
        mUploadList.setHasFixedSize(true);
        mUploadList.setAdapter(mPictureAdapter);
    }

    private void setOnClickListeners() {
        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.setType("image/*");
                mIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                mIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(mIntent,"Select photos"), RESULT_LOAD_IMAGE);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("SH_lab6",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean("isLog", false);
                editor.apply();
                Intent i=new Intent(home.this,login_act.class);
                startActivity(i);
                finish();

            }
        });
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(home.this, ImagesActivity.class);
                startActivity(i);
            }
        });
        nxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(home.this,ModelUpload.class);
                startActivity(i);
            }
        });


    }

    private void initializeViews() {
        mUpload = findViewById(R.id.btn_upload_photos);
        mUploadList = findViewById(R.id.rv_upload_list);
        fini=findViewById(R.id.btn_accommodation_select);
        log=findViewById(R.id.logout_btn);
        nxt=findViewById(R.id.nxt);
        nxt2=findViewById(R.id.ad11);
    }

}