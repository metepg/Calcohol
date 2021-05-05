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
 * <h1>Käyttäjän painon kysely luokka</h1>
 * <p>weight aktiviteetin luokka</p>
 * <p>
 * Käytetty kirjastoa <a href="https://github.com/kofigyan/StateProgressBar">progressbarin</a> luontiin
 *
 * @author TeemuT
 * @version 1.0 5/2021
 */

public class AskWeight extends AppCompatActivity {
    String[] descriptionData = {"Age", "Gender", "Weight"};
    Button button;
    private final static String USER = "properties";
    private static final String WEIGHTKEY = "weightValue";
    private static final String INITKEY = "valuesSet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        //Ulkoisesta kirjastosta haettu käyttäjän etenemisen seuraamis palkki
        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);
        button = findViewById(R.id.angry_btn);
        //Tässä määritellään paino kolmanneksi seuraamis palkissa
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
    }

    /**
     * <p>getWeight hakee käyttäjän syöttämän painon</p>
     * <p>Määritellään painolle pienin ja korkein arvo</p>
     *
     * @param view Nappi tallennukseen ja seuraavaan aktiviteettiin
     */
    public void getWeight(View view) {
        EditText weightElement = findViewById(R.id.editWeight);
        String weight = (weightElement.getText().toString());
        if (weight.isEmpty()) {
            weightElement.setError("Please, enter weight");
        } else if (Integer.parseInt(weight) < 50 || Integer.parseInt(weight) > 150) {
            weightElement.setError("Insert weight between 50 - 150 kg");
        } else {
            SharedPreferences userPreferences = getSharedPreferences(USER, MODE_PRIVATE);
            SharedPreferences.Editor editor = userPreferences.edit();
            editor.putString(WEIGHTKEY, weight);

            // Jos kaikki ok niin lisää boolean arvo
            // Mainactivityssä verrataan siihen onko tiedot lisätty jo
            // Laita false jos haluat pyörittää login valikkoa aina uusiks
            editor.putBoolean(INITKEY, true);
            editor.apply();
            Intent returnToMain = new Intent(this, MainActivity.class);
            startActivity(returnToMain);
            finish();
        }
    }
}