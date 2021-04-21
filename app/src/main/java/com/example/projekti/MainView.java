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

    TextView smallSoft;
    TextView bigSoft;
    TextView smallStrong;
    TextView bigStrong;
    TextView smallWine;
    TextView bigWine;
    TextView smallLiquor;
    TextView bigLiquor;
    TextView totalText;

    Spinner softPortion;
    Spinner strongPortion;
    Spinner winePortion;
    Spinner liquorPortion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        // Dropdownien alustus
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


        smallSoft = findViewById(R.id.smallSoft);
        bigSoft = findViewById(R.id.bigSoft);

        smallStrong = findViewById(R.id.smallStrong);
        bigStrong = findViewById(R.id.bigStrong);

        smallWine = findViewById(R.id.smallWine);
        bigWine = findViewById(R.id.bigWine);

        smallLiquor = findViewById(R.id.smallLiquor);
        bigLiquor = findViewById(R.id.bigLiquor);
        totalText = findViewById(R.id.totalText);

        softPortion = findViewById(R.id.softSpinner);
        strongPortion = findViewById(R.id.strongSpinner);
        winePortion = findViewById(R.id.wineSpinner);
        liquorPortion = findViewById(R.id.liquorSpinner);

        total = new Calc("man", 50);
    }

    // Päivitä tekstikenttä annetun idn ja counterin mukaan
    public void updateField(TextView id, String text) {
        id.setText(text);
    }

    // Katso plus buttoneista mitä painettu
    public void onPlus(View view) {
        switch (view.getId()) {
            case R.id.softPlus:
                total.addSoft(getPortion(softPortion.getSelectedItem().toString()));
                if (softPortion.getSelectedItem().equals("0.33 l")) {
                    soft.setSmall("+");
                    updateField(findViewById(R.id.smallSoft), soft.getSmall());
                } else {
                    soft.setBig("+");
                    updateField(findViewById(R.id.bigSoft), soft.getBig());
                }
                break;
            case R.id.strongPlus:
                total.addStrong(getPortion(strongPortion.getSelectedItem().toString()));
                if (strongPortion.getSelectedItem().equals("0.33 l")) {
                    strong.setSmall("+");
                    updateField(findViewById(R.id.smallStrong), strong.getSmall());
                } else {
                    strong.setBig("+");
                    updateField(findViewById(R.id.bigStrong), strong.getBig());
                }
                break;
            case R.id.winePlus:
                total.addWine(getPortion(winePortion.getSelectedItem().toString()));
                if (winePortion.getSelectedItem().equals("12 cl")) {
                    wine.setSmall("+");
                    updateField(findViewById(R.id.smallWine), wine.getSmall());
                } else {
                    wine.setBig("+");
                    updateField(findViewById(R.id.bigWine), wine.getBig());
                }
                break;
            case R.id.liquorPlus:
                total.addLiquor(getPortion(liquorPortion.getSelectedItem().toString()));
                if (liquorPortion.getSelectedItem().equals("4 cl")) {
                    liquor.setSmall("+");
                    updateField(findViewById(R.id.smallLiquor), liquor.getSmall());
                } else {
                    liquor.setBig("+");
                    updateField(findViewById(R.id.bigLiquor), liquor.getBig());
                }
                break;
        }
        totalText.setText(total.getAlcoholInBlood());
    }


    // Katso minus buttoneista mitä painettu
    public void onMinus(View view) {
        switch (view.getId()) {
            case R.id.softMinus:
                if (softPortion.getSelectedItem().equals("0.33 l")) {
                    if (Integer.parseInt(soft.getSmall()) > 0) {
                        total.addSoft(-330);
                    }
                    soft.setSmall("-");
                    updateField(findViewById(R.id.smallSoft), soft.getSmall());
                } else {
                    if (Integer.parseInt(soft.getBig()) > 0) {
                        total.addSoft(-500);
                    }
                    soft.setBig("-");
                    updateField(findViewById(R.id.bigSoft), soft.getBig());
                }
                break;
            case R.id.strongMinus:
                if (strongPortion.getSelectedItem().equals("0.33 l")) {
                    if (Integer.parseInt(strong.getSmall()) > 0) {
                        total.addStrong(-330);
                    }
                    strong.setSmall("-");
                    updateField(findViewById(R.id.smallStrong), strong.getSmall());
                } else {
                    if (Integer.parseInt(strong.getBig()) > 0) {
                        total.addStrong(-500);
                    }
                    strong.setBig("-");
                    updateField(findViewById(R.id.bigStrong), strong.getBig());
                }
                break;
            case R.id.wineMinus:
                if (winePortion.getSelectedItem().equals("12 cl")) {
                    if (Integer.parseInt(wine.getSmall()) > 0) {
                        total.addWine(-120);
                    }
                    wine.setSmall("-");
                    updateField(findViewById(R.id.smallWine), wine.getSmall());
                } else {
                    if (Integer.parseInt(wine.getBig()) > 0) {
                        total.addWine(-375);
                    }
                    wine.setBig("-");
                    updateField(findViewById(R.id.bigWine), wine.getBig());
                }
                break;
            case R.id.liquorMinus:
                if (liquorPortion.getSelectedItem().equals("4 cl")) {
                    if (Integer.parseInt(liquor.getSmall()) > 0) {
                        total.addLiquor(-40);
                    }
                    liquor.setSmall("-");
                    updateField(findViewById(R.id.smallLiquor), liquor.getSmall());
                } else {
                    if (Integer.parseInt(liquor.getBig()) > 0) {
                        total.addLiquor(-500);
                    }
                    liquor.setBig("-");
                    updateField(findViewById(R.id.bigLiquor), liquor.getBig());
                }
                break;
        }
        totalText.setText(total.getAlcoholInBlood());
    }

    // Palauttaa numeroarvon spinnereiden valintakentästä
    public double getPortion(String portion) {
        switch (portion) {
            case "4 cl":
                return 40.0;
            case "12 cl":
                return 120.0;
            case "0.33 l":
                return 330.0;
            case "0.5 l":
                return 500.0;
            case "0.375 l":
                return 375.0;

        }
        return 0;
    }
}