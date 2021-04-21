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
    private Calc total;

    TextView softText;
    TextView strongText;
    TextView wineText;
    TextView liquorText;
    TextView totalText;

    Spinner softPortion;
    Spinner strongPortion;
    Spinner winePortion;
    Spinner liquorPortion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        Spinner softVal = findViewById(R.id.softSpinner);
        ArrayAdapter<CharSequence> soft = ArrayAdapter.createFromResource(this,
                R.array.softdrink, android.R.layout.simple_spinner_item);
        soft.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        softVal.setAdapter(soft);

        Spinner strongVal = findViewById(R.id.strongSpinner);
        ArrayAdapter<CharSequence> strong = ArrayAdapter.createFromResource(this,
                R.array.strongdrink, android.R.layout.simple_spinner_item);
        strong.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        strongVal.setAdapter(strong);

        Spinner wineVal = findViewById(R.id.wineSpinner);
        ArrayAdapter<CharSequence> wine = ArrayAdapter.createFromResource(this,
                R.array.winedrink, android.R.layout.simple_spinner_item);
        wine.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wineVal.setAdapter(wine);

        Spinner liquorVal = findViewById(R.id.liquorSpinner);
        ArrayAdapter<CharSequence> liquor = ArrayAdapter.createFromResource(this,
                R.array.liquordrink, android.R.layout.simple_spinner_item);
        liquor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liquorVal.setAdapter(liquor);


        softText = findViewById(R.id.softText);
        strongText = findViewById(R.id.strongText);
        wineText = findViewById(R.id.wineText);
        liquorText = findViewById(R.id.liquorText);
        totalText = findViewById(R.id.totalText);

        softPortion = findViewById(R.id.softSpinner);
        strongPortion = findViewById(R.id.strongSpinner);
        winePortion = findViewById(R.id.wineSpinner);
        liquorPortion = findViewById(R.id.liquorSpinner);

        total = new Calc("man",50);
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
                total.addSoft(getPortion(softPortion.getSelectedItem().toString()));
                updateField(findViewById(R.id.softText), soft);
                break;
            case R.id.strongPlus:
                strong.add();
                total.addStrong(getPortion(strongPortion.getSelectedItem().toString()));
                updateField(findViewById(R.id.strongText), strong);
                break;
            case R.id.winePlus:
                wine.add();
                total.addWine(getPortion(winePortion.getSelectedItem().toString()));
                updateField(findViewById(R.id.wineText), wine);
                break;
            case R.id.liquorPlus:
                liquor.add();
                total.addLiquor(getPortion(liquorPortion.getSelectedItem().toString()));
                updateField(findViewById(R.id.liquorText), liquor);
                break;
        }

        totalText.setText(total.getAlcoholInBlood());
    }

    // Katso minus buttoneista mitä painettu
    public void onMinus(View view) {
        switch (view.getId()) {
            case R.id.softMinus:
                soft.minus();
                total.addSoft(-getPortion(softPortion.getSelectedItem().toString()));
                updateField(findViewById(R.id.softText), soft);
                break;
            case R.id.strongMinus:
                strong.minus();
                total.addStrong(-getPortion(strongPortion.getSelectedItem().toString()));
                updateField(findViewById(R.id.strongText), strong);
                break;
            case R.id.wineMinus:
                wine.minus();
                total.addWine(-getPortion(winePortion.getSelectedItem().toString()));
                updateField(findViewById(R.id.wineText), wine);
                break;
            case R.id.liquorMinus:
                liquor.minus();
                total.addLiquor(-getPortion(liquorPortion.getSelectedItem().toString()));
                updateField(findViewById(R.id.liquorText), liquor);
                break;
        }
        totalText.setText(total.getAlcoholInBlood());
    }

    // Palauttaa numeroarvon spinnereiden valintakentästä
    public int getPortion(String portion) {
        switch (portion){
            case "0.33 l":
                return 330;
            case "0.5 l":
                return 500;
            case "12 cl":
                return 120;
            case "4 cl":
                return 40;
        }
        return 0;
    }
}