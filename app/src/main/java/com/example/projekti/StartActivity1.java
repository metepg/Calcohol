package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//First activity
public class StartActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start1);
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
            finish();
        }
    }
}