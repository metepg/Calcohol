package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Käyttäjän asetusten aktiviteetin luokka
 *
 * @author teme
 * @version 1.1 5/2021
 */

public class UserSettings extends AppCompatActivity {
    /**
     * Applikaation <strong>avaimet</strong> tiedoille
     */
    SharedPreferences sharedPrefs;
    private final static String USER = "properties";
    private static final String WEIGHTKEY = "weightValue";
    private static final String GENDERKEY = "genderValue";
    private final static String AGEKEY = "ageValue";
    /**
     * Luokan yleiset <strong>muuttujat</strong>
     */
    RadioGroup radiosexi;
    RadioButton radiofemale;
    RadioButton radiomale;
    EditText ageText;
    EditText weightText;
    String gender;
    String weighted;
    String aged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        sharedPrefs = getSharedPreferences(USER, MODE_PRIVATE);

        Intent intent = getIntent();
        String age = intent.getStringExtra(MainActivity.AGEKEY);
        String weight = intent.getStringExtra(MainActivity.WEIGHTKEY);
        String gender = intent.getStringExtra(MainActivity.GENDERKEY);

        radiosexi = findViewById(R.id.radioSex);
        radiofemale = findViewById(R.id.radioFemale);
        radiomale = findViewById(R.id.radioMale);
        weightText = findViewById(R.id.weightStart);
        ageText = findViewById(R.id.newAge);

        // Haetaan aikaisemmin syötetyt tiedot
        setDefaults(age, weight, gender);
    }

    /**
     * Aseta default arvot tallennettujen tietojen mukaisesti
     *
     * @param age    ikä
     * @param weight paino
     * @param gender sukupuoli
     */
    public void setDefaults(String age, String weight, String gender) {
        ageText.setText(age);
        weightText.setText(weight);

        if (gender.equals("male")) {
            radiomale.setChecked(true);
        } else {
            radiofemale.setChecked(true);
        }
    }

    /**
     * Palauttaa iän tekstinä
     *
     * @return ikä esim. 33
     */
    public String setAge() {
        ageText = findViewById(R.id.newAge);
        aged = ageText.getText().toString();
        if (aged.isEmpty()) {
            ageText.setError("Age required");
        } else if (Integer.parseInt(aged) < 18) {
            ageText.setError("Only 18+ year old allowed to continue");
            return "";
        }
        return aged;
    }

    /**
     * Hakee painon tekstikentästä ja palauttaa sen tekstinä
     *
     * @return paino esim. 55
     */
    public String setWeight() {
        weightText = findViewById(R.id.weightStart);
        weighted = weightText.getText().toString();
        if (weighted.isEmpty()) {
            weightText.setError("Weight required");
        } else if (Integer.parseInt(weighted) < 50) {
            weightText.setError("Weight below the required limit");
            return "";
        }
        return weighted;
    }

    /**
     * Palauttaa sukupuolen Stringinä
     *
     * @return sukupuoli (male / female)
     */
    public String setGender() {
        if (radiomale.isChecked()) {
            gender = "male";
            radiomale.setChecked(true);
        } else {
            gender = "female";
            radiofemale.setChecked(true);
        }
        return gender;
    }

    /**
     * Tallentaa uudet arvot
     *
     * @param view nappi elementti
     */
    public void saveNewValues(View view) {
        if (setAge().isEmpty() || setWeight().isEmpty()) {
            return;
        }
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(GENDERKEY, setGender());
        editor.putString(WEIGHTKEY, setWeight());
        editor.putString(AGEKEY, setAge());
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        editor.commit();
        finish();
    }

    /**
     * Takaisin mainactivityyn
     *
     * @param view nappi elementti
     */
    public void backToMain(View view) {
        super.onBackPressed();
        finish();
    }
}