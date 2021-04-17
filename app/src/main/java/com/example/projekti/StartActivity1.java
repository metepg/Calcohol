package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class StartActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start1);
    }
    public void checkAge(View view) {
        EditText ageElement = findViewById(R.id.age);
        Log.d("moi", ageElement.getText().toString());
        int age = Integer.parseInt(ageElement.getText().toString());
        TextView allowed = findViewById(R.id.txtv);
        if (age < 18){
            allowed.setText("Only +18 year old allowed to continue");
        } else {
            allowed.setText("juujuu");
        }

    }
}