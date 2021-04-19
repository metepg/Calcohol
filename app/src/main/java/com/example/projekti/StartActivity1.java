package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kofigyan.stateprogressbar.StateProgressBar;

//First activity
public class StartActivity1 extends AppCompatActivity {
    String[] descriptionData = {"Age", "Gender", "Weight"};
    private final static String USER = "properties";
    private final static String AGEKEY = "ageValue";



    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start1);

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

        button = findViewById(R.id.btn);
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);


    }
    //check age
    public void checkAge(View view) {

        EditText ageElement = findViewById(R.id.age);
        Log.d("moi", ageElement.getText().toString());
        String age = ageElement.getText().toString();
        if(age.isEmpty()){
            ageElement.setError("Text required");
        } else if (Integer.parseInt(age) < 18){
            ageElement.setError("Only +18 year old allowed to continue");
        }
        else {
            SharedPreferences userPreferences = getSharedPreferences(USER,  MODE_PRIVATE);
            SharedPreferences.Editor editor = userPreferences.edit();
            editor.putString(AGEKEY, age);
            editor.commit();

            Intent askGender = new Intent(this, Gender.class);
            startActivity(askGender);
        }
    }
}