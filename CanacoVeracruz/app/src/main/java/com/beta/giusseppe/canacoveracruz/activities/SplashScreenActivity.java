package com.beta.giusseppe.canacoveracruz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.beta.giusseppe.canacoveracruz.R;


public class SplashScreenActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3900);   // set the duration of splash screen
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
