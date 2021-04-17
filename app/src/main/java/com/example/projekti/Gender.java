package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kofigyan.stateprogressbar.StateProgressBar;

public class Gender extends AppCompatActivity {
    String[] descriptionData = {"Age", "Gender", "Weight"};

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

            Intent askWeight = new Intent(this, Weight.class);
            startActivity(askWeight);
        }
    }
}