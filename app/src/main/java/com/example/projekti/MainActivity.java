package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private String TAG = "jes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        laske();
    }

    public void laske() {
        Calc laskin = new Calc(0, 0, 0, 500, "man", 60 );
        String je = laskin.getAlcoholInBlood() + "";
        Log.i(TAG, je);
    }
}