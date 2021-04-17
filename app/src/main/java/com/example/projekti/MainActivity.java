package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private String TAG = "jes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        laske();

        Intent askGender = new Intent(this, Gender.class);
        startActivity(askGender);

        Intent askAge = new Intent(this, StartActivity1.class);
        startActivity(askAge);

    }

    public void laske() {
        Calc laskin = new Calc(0, 0, 0, 80, "man", 60 );
        String alcBlood = laskin.getAlcoholInBlood() + "";
        String alcTime = laskin.getBurningTime()+"";
        String cal = laskin.getCalories()+"";
        Log.i(TAG, "Alcohol in blood: " + alcBlood+"%");
        Log.i(TAG, "Time to sober:  " +alcTime+" h");
        Log.i(TAG, "Calories:  " +cal+" kcal");
    }
}