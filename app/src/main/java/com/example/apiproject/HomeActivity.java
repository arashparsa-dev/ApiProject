package com.example.apiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.apiproject.Fragments.CountryFragment;
import com.example.apiproject.Fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity   {
    BottomNavigationView bottomNavigationView;
    TextView Tv_Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigationView = findViewById(R.id.bottom_navigation_home);
        Tv_Title = findViewById(R.id.tv_home_title);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;


                switch (item.getItemId()){
                    case R.id.menuItem_country:
                        selectedFragment = CountryFragment.getInstance();
                        Tv_Title.setText("کشورها");

                        break;
                    case R.id.menuItem_home:
                        selectedFragment = HomeFragment.getInstance();
                        Tv_Title.setText("صفحه اصلی");

                        break;

                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_main_fragment_container,selectedFragment);
                transaction.commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.menuItem_home);
    }
}