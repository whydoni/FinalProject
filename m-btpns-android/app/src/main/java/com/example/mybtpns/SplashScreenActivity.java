package com.example.mybtpns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView bgSplash, logo_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        findViewById();
        myAnimation();
        thread.start();
    }

    void findViewById() {
        bgSplash = findViewById(R.id.bgsplash);
        logo_splash = findViewById(R.id.logo_splash);
    }

    void myAnimation() {
        bgSplash.animate().translationY(-2600).setDuration(1000).setStartDelay(4000);
        logo_splash.animate().translationY(2600).setDuration(1000).setStartDelay(4000);
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                sleep(4000);
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                SplashScreenActivity.this.startActivity(intent);
                finish();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    };

}