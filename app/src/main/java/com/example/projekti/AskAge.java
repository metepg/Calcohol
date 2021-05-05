package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kofigyan.stateprogressbar.StateProgressBar;

/**
 * <h1>Aktiviteetti jossa kysytään ikä</h1>
 * <p>
 * Käytetty kirjastoa <a href="https://github.com/kofigyan/StateProgressBar">progressbarin</a> luontiin
 *
 * @author Henri Iisvirta
 * @version 1.0 5/2021
 */
public class AskAge extends AppCompatActivity {
    String[] descriptionData = {"Age", "Gender", "Weight"};
    private final static String USER = "properties";
    private final static String AGEKEY = "ageValue";

    //Tässä määritellään luokan muuttuja
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
        //Ulkoisesta kirjastosta haettu käyttäjän etenemisen seuraamis palkki
        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);
        button = findViewById(R.id.angry_btn);
        //Tässä määritellään ikä ensimmäisessä vaiheessa
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
    }

    /**
     * Tarkistaa ja tallentaa käyttäjän syötteen
     *
     * @param view painettu nappi
     */
    public void checkAge(View view) {
        EditText ageElement = findViewById(R.id.age);
        String age = ageElement.getText().toString();
        //Tarkistetaan onko käyttäjä syöttänyt tietoja
        if (age.isEmpty()) {
            ageElement.setError("Age required");
        } else if (Integer.parseInt(age) < 18 || Integer.parseInt(age) > 99) {
            ageElement.setError("Insert age between 18 - 99");
        } else {
            //Tallennetaan arvot muistiin
            SharedPreferences userPreferences = getSharedPreferences(USER, MODE_PRIVATE);
            SharedPreferences.Editor editor = userPreferences.edit();
            editor.putString(AGEKEY, age);
            editor.apply();
            Intent askGender = new Intent(this, AskGender.class);
            startActivity(askGender);
            finish();
        }
    }
}