package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Gender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        Log.d("sukupuoli", "start");
    }

    public void getGender(View view) {
        EditText genderElement = findViewById(R.id.gender);
        String gender = genderElement.getText().toString();
        Log.d("sukupuoli", gender);

        if(gender.isEmpty()) {
            genderElement.setError("Kandee valita sukupuoli hei");
            Log.d("sukupuoli", "ei toimi");
        }
        else {
            finish();
        }
    }
}