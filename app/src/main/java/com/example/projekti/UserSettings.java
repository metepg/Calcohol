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
 * @version 1.0 5/2021
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

    /**
     * Haetaan aplikaatioon aikaisemmin syötetyt tiedot
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        sharedPrefs = getSharedPreferences(USER, MODE_PRIVATE);

        //Haetaan aikaisemmin syötetyt tiedot
        getGender();
        getWeight();
        getAge();
    }

    /**
     * Haetaan aikaisemmin valittu sukupuoli muistista
     */
    public void getGender() {
        String gender = sharedPrefs.getString(GENDERKEY, "male");
        radiosexi = findViewById(R.id.radioSex);
        radiofemale = findViewById(R.id.radioFemale);
        radiomale = findViewById(R.id.radioMale);

        if (gender.equals("male")) {
            radiomale.setChecked(true);
        } else {
            radiofemale.setChecked(true);
        }
    }

    /**
     * Haetaan aikaisemmin syötetty ikä muistista
     */
    public void getAge() {
        String age = sharedPrefs.getString(AGEKEY, "25");
        ageText = findViewById(R.id.newAge);
        ageText.setText(age);
    }

    /**
     * Haetaaan aikaisemmin syötetty paino muistista
     */
    public void getWeight() {
        String weight = sharedPrefs.getString(WEIGHTKEY, "70");
        weightText = findViewById(R.id.weightStart);
        weightText.setText(weight);
    }

    /**
     * @return Palautetaan ikä Stringinä
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
     * @return Palautetaan paino Stringinä
     */
    public String setWeight() {
        weightText = findViewById(R.id.weightStart);
        weighted = weightText.getText().toString();
        if (weighted.isEmpty()) {
            weightText.setError("Weight required");
        } else if (Integer.parseInt(weighted) < 50) {
            weightText.setError("weight below the required limit");
            return "";
        }
        return weighted;
    }

    /**
     * @return palautetaan sukupuoli Stringinä
     */
    public String setGender() {
        radiosexi = findViewById(R.id.radioSex);
        radiofemale = findViewById(R.id.radioFemale);
        radiomale = findViewById(R.id.radioMale);

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
     * @param view tallentaa uudet arvot ja syöttää ne jaettuuntiedostoon
     */
    public void saveNewValues(View view) {
        if (setAge().isEmpty() || setWeight().isEmpty()) {
            return;
        }
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(GENDERKEY, setGender());
        editor.putString(WEIGHTKEY, setWeight());
        editor.putString(AGEKEY, setAge());
        editor.commit();
        finish();
    }

    /**
     * @param view Palaa pää aktiviteettiin
     */
    public void backToMain(View view) {
        super.onBackPressed();
        finish();
    }
}