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

<<<<<<< HEAD
        Intent askGender = new Intent(this, Gender.class);
        startActivity(askGender);

=======
>>>>>>> 8f799dd4c39098cf9f133f8b798773fca0f55b73
        Intent askAge = new Intent(this, StartActivity1.class);
        Intent askWeight = new Intent(this, Weight.class);
        Intent askGender = new Intent(this, Gender.class);

        // Kysy ikä
        startActivity(askAge);

<<<<<<< HEAD
=======
        // Kysy sp
        startActivity(askGender);

        // Kysy paino
        startActivity(askWeight);

        Log.d(TAG, "kaikki ok");
>>>>>>> 8f799dd4c39098cf9f133f8b798773fca0f55b73
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