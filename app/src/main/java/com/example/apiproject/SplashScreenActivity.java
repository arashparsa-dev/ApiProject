    package com.example.apiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Field;

import me.anwarshahriar.calligrapher.Calligrapher;

    public class SplashScreenActivity extends AppCompatActivity {
        RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        relativeLayout = findViewById(R.id.relative_splash);

        //takhir dar vorod b app

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                    ConnectivityManager cm = (ConnectivityManager) SplashScreenActivity.this
                            .getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = cm.getActiveNetworkInfo();


                    if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
                        Intent intent = new Intent(SplashScreenActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Snackbar.make(relativeLayout,"No Internet Connection!",Snackbar.LENGTH_INDEFINITE).show();
                    }



            }
        },3000);

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