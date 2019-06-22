package com.sipemandu.sipemandu.UI.SplashScreen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.LoginActivity.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private static final String TAG = "SplashScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        int SPLASH_DISPLAY_LENGTH = 1000;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
        hideActionbar();
        
    }

    public void hideActionbar(){
        ActionBar ab = getSupportActionBar();
        ab.hide();
//        ab.setTitle("Zomato");
//        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setElevation(0);
    }
}
