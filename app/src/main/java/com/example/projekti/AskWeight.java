package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kofigyan.stateprogressbar.StateProgressBar;

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
        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

        button = findViewById(R.id.angry_btn);
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
    }
    public void getWeight (View view) {

        EditText weightElement = findViewById(R.id.editWeight);
        String weight = (weightElement.getText().toString());


        if(weight.isEmpty()) {
            weightElement.setError("Please, enter weight");
            Log.d("paino", "liian vähän painoa");
        }
        else {
            Log.d("paino", "tarpeeksi painoa");
            SharedPreferences userPreferences = getSharedPreferences(USER,  MODE_PRIVATE);
            SharedPreferences.Editor editor = userPreferences.edit();
            editor.putString(WEIGHTKEY, weight);

            // Jos kaikki ok niin lisää boolean arvo
            // Mainactivityssä verrataan siihen onko tiedot lisätty jo
            // Laita false jos haluat pyörittää login valikkoa aina uusiks
            editor.putBoolean(INITKEY, true);
            editor.commit();
            Intent returnToMain = new Intent(this, MainActivity.class);
            startActivity(returnToMain);
            finish();
        }
    }
}