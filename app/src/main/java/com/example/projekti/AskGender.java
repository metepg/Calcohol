package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kofigyan.stateprogressbar.StateProgressBar;

public class AskGender extends AppCompatActivity {
    String[] descriptionData = {"Age", "Gender", "Weight"};
    private final static String USER = "properties";
    private static final String GENDERKEY = "genderValue";

    Button button;
    RadioGroup groupradio;
    RadioButton groupfemale;
    RadioButton groupmale;
    SharedPreferences sharedPrefs;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);
        sharedPrefs = getSharedPreferences(USER, MODE_PRIVATE);
        button = findViewById(R.id.angry_btn);
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);

    }

    public void setGender(View view) {
        groupradio = findViewById(R.id.groupradio);
        groupfemale = findViewById(R.id.groupfemale);
        groupmale = findViewById(R.id.groupmale);
        if (groupradio.getCheckedRadioButtonId() == -1) {
            groupmale.setError("Choose one of these");
            return;
        }
        if (groupmale.isChecked()) {
            gender = "male";
            groupmale.setChecked(true);
        } else if (groupfemale.isChecked()) {
            gender = "female";
            groupfemale.setChecked(true);
        }
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(GENDERKEY, gender);
        editor.commit();
        Intent askGender = new Intent(this, AskWeight.class);
        startActivity(askGender);
        finish();
    }
}


    /*public void saveValues(View view) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(GENDERKEY, setGender());
        editor.commit();
        Intent askGender = new Intent(this, AskWeight.class);
        startActivity(askGender);
        finish();
    }*/
