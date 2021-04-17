package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kofigyan.stateprogressbar.StateProgressBar;

public class Weight extends AppCompatActivity {
    String[] descriptionData = {"Age", "Gender", "Weight"};
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

        button = findViewById(R.id.btn);
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
    }
    public void getWeight (View view) {

        EditText weightElement = findViewById(R.id.weight);
        String weight = (weightElement.getText().toString());


        if(weight.isEmpty()) {
            weightElement.setError("painosi on liian vähän");
            Log.d("paino", "liian vähän painoa");
        }
        else {
            Log.d("paino", "tarpeeksi painoa");
    finish();
        }
    }
}