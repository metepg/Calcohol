package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Weight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
    }
    public void getWeight (View view) {
        EditText weightElement = findViewById(R.id.weight);
        String weight = (weightElement.getText().toString());
        TextView allowed = findViewById(R.id.txtv);

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