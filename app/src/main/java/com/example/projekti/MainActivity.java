package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private String TAG = "jes";
    private final static String USER = "properties";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tää on se storage mihin tietoja tallentuu tietoja
        SharedPreferences sharedPrefs = getSharedPreferences(USER, MODE_PRIVATE);
        Log.d(TAG, String.valueOf(sharedPrefs.contains("valuesSet")));

        // Tällä lauseella kattoo onko tiedot jo asetettu eli onko alkujutut tehty jo
        if(sharedPrefs.getBoolean("valuesSet", false)) {
            // Printtaa kaikki tiedot
            Log.d(TAG, sharedPrefs.getAll().toString());
            laske();
            Intent mainView = new Intent(this, MainView.class);
            startActivity(mainView);
        }
        // Jos ei ole niin..
        else {
            Intent askAge = new Intent(this, StartActivity1.class);
            startActivity(askAge);
        }
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