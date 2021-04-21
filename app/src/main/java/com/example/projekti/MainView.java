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

    // Päivitä tekstikenttä annetun idn ja counterin mukaan
    public void updateField(TextView id, Counter counter) {
        id.setText(counter.getCounter());
    }

    // Katso plus buttoneista mitä painettu
    public void onPlus(View view) {
        switch (view.getId()) {
            case R.id.softPlus:
                soft.add();
                updateField(findViewById(R.id.softText), soft);
                break;
            case R.id.strongPlus:
                strong.add();
                updateField(findViewById(R.id.strongText), strong);
                break;
            case R.id.winePlus:
                wine.add();
                updateField(findViewById(R.id.wineText), wine);
                break;
            case R.id.liquorPlus:
                liquor.add();
                updateField(findViewById(R.id.liquorText), liquor);
                break;
        }
    }

    // Katso minus buttoneista mitä painettu
    public void onMinus(View view) {
        switch (view.getId()) {
            case R.id.softMinus:
                soft.minus();
                updateField(findViewById(R.id.softText), soft);
                break;
            case R.id.strongMinus:
                strong.minus();
                updateField(findViewById(R.id.strongText), strong);
                break;
            case R.id.wineMinus:
                wine.minus();
                updateField(findViewById(R.id.wineText), wine);
                break;
            case R.id.liquorMinus:
                liquor.minus();
                updateField(findViewById(R.id.liquorText), liquor);
                break;
        }
    }

    public void onSpin(View view) {

    }
}