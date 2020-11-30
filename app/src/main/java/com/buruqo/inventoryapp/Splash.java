package com.buruqo.inventoryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//Created by Varol KALA and Atakan GÖÇER
public class Splash extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        Thread timer = new Thread() {

            @Override
            public void run() {

                try {

                    sleep(3000);
                } catch (InterruptedException e) {


                    e.printStackTrace();
                } finally {

                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), MainActivity.class);
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
