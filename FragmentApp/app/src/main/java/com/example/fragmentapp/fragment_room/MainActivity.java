package com.example.fragmentapp.fragment_room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.fragmentapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    final FragmentManager fragmentManager = getSupportFragmentManager();
    BlankFragment fragment1;
    BlankFragment2 fragment2;
    BlankFragment3 fragment3;
    private Fragment activeFragment;
    private BottomNavigationView navView;



    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navView = findViewById(R.id.bottomNav);
        navView.setOnNavigationItemSelectedListener(this);

        initializeViews();
        LoadFragment();


    }


    public void initializeViews() {
        fragment1 = new BlankFragment();
        fragment2 = new BlankFragment2();
        fragment3 = new BlankFragment3();

        activeFragment = fragment1;
    }

    private void LoadFragment() {
        fragmentManager.beginTransaction().add(R.id.containerFrame, fragment3, "1").hide(fragment3).commit();
        fragmentManager.beginTransaction().add(R.id.containerFrame, fragment2, "2").hide(fragment2).commit();
        fragmentManager.beginTransaction().add(R.id.containerFrame, fragment1, "3").hide(fragment1).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragmentManager.beginTransaction().hide(activeFragment).show(fragment1).commit();
                activeFragment = fragment1;
                return true;

            case R.id.navigation_dashboard:
                fragmentManager.beginTransaction().hide(activeFragment).show(fragment2).commit();
                activeFragment = fragment2;
                return true;

            case R.id.navigation_notifications:
                fragmentManager.beginTransaction().hide(activeFragment).show(fragment3).commit();
                activeFragment = fragment3;
                return true;
            case R.id.navigation_logout:
                SharedPreferences sharedpreferences = getSharedPreferences("SH_lab5",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean("isLogin", false);
                editor.apply();
                Intent moveToLogin = new Intent(MainActivity.this, LoginActivitityLab5.class);
                startActivity(moveToLogin);
                finish();
                return true;
        }
        return false;
    }



}