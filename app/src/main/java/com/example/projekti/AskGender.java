package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kofigyan.stateprogressbar.StateProgressBar;

public class AskGender extends AppCompatActivity {
    String[] descriptionData = {"Age", "Gender", "Weight"};
    private final static String USER = "properties";
    private static final String GENDERKEY = "genderValue";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        Log.d("sukupuoli", "start");
        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

        button = findViewById(R.id.btn);
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);

    }

    public void getGender(View view) {
        EditText genderElement = findViewById(R.id.gender);
        String gender = genderElement.getText().toString();
        Log.d("sukupuoli", gender);

        if(gender.isEmpty()) {
            genderElement.setError("Choose gender first!");
            Log.d("sukupuoli", "ei toimi");
        }
        else {
            SharedPreferences userPreferences = getSharedPreferences(USER,  MODE_PRIVATE);
            SharedPreferences.Editor editor = userPreferences.edit();
            editor.putString(GENDERKEY, gender);
            editor.commit();
            Intent askWeight = new Intent(this, AskWeight.class);
            startActivity(askWeight);
            finish();

        }
    }
}