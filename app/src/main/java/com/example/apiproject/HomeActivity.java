package com.example.apiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apiproject.Fragments.CountryFragment;
import com.example.apiproject.Fragments.HomeFragment;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;

import me.anwarshahriar.calligrapher.Calligrapher;

public class HomeActivity extends AppCompatActivity   {
    BottomNavigationView bottomNavigationView;
    TextView Tv_Title;
   // LinearLayout parent;
    //SpinKitView spinKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       // parent = findViewById(R.id.parent_country);
       // spinKitView = findViewById(R.id.spin_kit_country);


        bottomNavigationView = findViewById(R.id.bottom_navigation_home);
        Tv_Title = findViewById(R.id.tv_home_title);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;


                switch (item.getItemId()){
                    case R.id.menuItem_country:
                        //spinKitView.setVisibility(View.VISIBLE);
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



      setFont();
    }

    private void setFont() {
        String iransans_light = "iransans_light.ttf" ;
        AssetManager assetManager = this.getAssets();
        Typeface typeface = Typeface.createFromAsset(assetManager,iransans_light);
        try {
            Field field =Typeface.class.getDeclaredField("monospace");
            field.setAccessible(true);
            try {
                field.set(null, typeface);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "iransans_bold.ttf", true);
    }
}