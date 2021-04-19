package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainView extends AppCompatActivity {

    private Counter soft = new Counter();
    private Counter strong = new Counter();
    private Counter wine = new Counter();
    private Counter liquor = new Counter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.softdrink, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public void onPlus(View view){
        if(view == findViewById(R.id.plus)){
           TextView txt = findViewById(R.id.textViewSoft);
           soft.add();
           txt.setText(soft.getCounter());
        }
    }
    public void onMinus(View view){
        if(view == findViewById(R.id.minus)){
            TextView txt = findViewById(R.id.textViewSoft);
            soft.minus();
            txt.setText(soft.getCounter());
        }
    }
    public void onSpin(View view){
        
    }
}