package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Singleton days;
    Gson gson = new Gson();

    private String TAG = "jes";
    private final static String USER = "properties";
    private final Counter soft = new Counter();
    private final Counter strong = new Counter();
    private final Counter wine = new Counter();
    private final Counter liquor = new Counter();

    private DayInfo total;
    User testUser;

    LocalDate myObj;
    DateTimeFormatter getDate;
    String formattedDate;

    EditText timeText;

    TextView portions;
    TextView smallSoft;
    TextView bigSoft;
    TextView smallStrong;
    TextView bigStrong;
    TextView smallWine;
    TextView bigWine;
    TextView smallLiquor;
    TextView bigLiquor;
    TextView totalText;
    TextView burnaus;
    TextView energy;

    Spinner softPortion;
    Spinner strongPortion;
    Spinner winePortion;
    Spinner liquorPortion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tää on se storage mihin tallentuu tietoja
        SharedPreferences sharedPrefs = getSharedPreferences(USER, MODE_PRIVATE);

        // Tällä lauseella kattoo onko tiedot jo asetettu eli onko alkujutut tehty jo
        if (sharedPrefs.getBoolean("valuesSet", false)) {
            setContentView(R.layout.activity_main);
            loadData();

            // Alustetaan käyttäjätiedot user olioon
            int weight = Integer.parseInt(sharedPrefs.getString("weightValue", "0"));
            String gender = sharedPrefs.getString("genderValue", "man");
            int age = Integer.parseInt(sharedPrefs.getString("ageValue", "18"));
            testUser = new User(age, gender, weight);

            // Dropdownien alustus
            softPortion = findViewById(R.id.softSpinner);
            strongPortion = findViewById(R.id.strongSpinner);
            winePortion = findViewById(R.id.wineSpinner);
            liquorPortion = findViewById(R.id.liquorSpinner);

            // 0.33l & 0.5l
            ArrayAdapter<CharSequence> soft = ArrayAdapter.createFromResource(this,
                    R.array.softdrink, android.R.layout.simple_spinner_item);
            soft.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            softPortion.setAdapter(soft);

            // 0.33l & 0.5l
            ArrayAdapter<CharSequence> strong = ArrayAdapter.createFromResource(this,
                    R.array.strongdrink, android.R.layout.simple_spinner_item);
            strong.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            strongPortion.setAdapter(strong);

            // 12cl & 0.375l
            ArrayAdapter<CharSequence> wine = ArrayAdapter.createFromResource(this,
                    R.array.winedrink, android.R.layout.simple_spinner_item);
            wine.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            winePortion.setAdapter(wine);

            // 4cl % 0.5l
            ArrayAdapter<CharSequence> liquor = ArrayAdapter.createFromResource(this,
                    R.array.liquordrink, android.R.layout.simple_spinner_item);
            liquor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            liquorPortion.setAdapter(liquor);

            burnaus = findViewById(R.id.burningText);
            energy = findViewById(R.id.energyText);
            timeText = findViewById(R.id.timeText);

            smallSoft = findViewById(R.id.smallSoft);
            bigSoft = findViewById(R.id.bigSoft);
            smallStrong = findViewById(R.id.smallStrong);
            bigStrong = findViewById(R.id.bigStrong);
            smallWine = findViewById(R.id.smallWine);
            bigWine = findViewById(R.id.bigWine);
            smallLiquor = findViewById(R.id.smallLiquor);
            bigLiquor = findViewById(R.id.bigLiquor);

            totalText = findViewById(R.id.totalText);
            portions = findViewById(R.id.portions);

            timeText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    startTime();
                }
            });

            myObj = LocalDate.now();
            getDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formattedDate = myObj.format(getDate);
            total = new DayInfo(formattedDate);

            List<DayInfo> data = loadData();
            days = Singleton.getInstance();
            if (data != null && days.getAllDays().size() < 1) {
                // Lisää tallennetut tiedot Singletoniin
                for (int i = 0; i < data.size(); i++) {
                    days.getAllDays().add(data.get(i));
                }
            }
        }
        // Jos ei ole, niin käynnistä login aktiviteetti
        else {
            Intent askAge = new Intent(this, AskAge.class);
            startActivity(askAge);
        }
    }

    /**
     * Hae tiedot "database" tiedostosta
     *
     * @return päiväkohtaiset tiedot listana
     */
    public List<DayInfo> loadData() {
        SharedPreferences worker = getSharedPreferences("database", MODE_PRIVATE);
        if (worker.getAll().toString().equals("{}")) {
            return null;
        } else {
            String arr = worker.getString("KAIKKIDATA", "");
            TypeToken<List<DayInfo>> token = new TypeToken<List<DayInfo>>() {
            };
            return gson.fromJson(arr, token.getType());
        }
    }

    public void onChart(View view) {
        Intent chart = new Intent(this, AmountChart.class);
        startActivity(chart);
    }

    public void onUser(View view) {
        Intent user = new Intent(this, UserSettings.class);
        startActivity(user);
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    // Päivitä tekstikenttä annetun idn ja counterin mukaan
    public void updateField(TextView id, String text) {
        id.setText(text);
    }

    // Katso plus buttoneista mitä painettu
    public void onPlus(View view) {
        switch (view.getId()) {
            case R.id.softPlus:
                total.updateSoft(getPortion(softPortion.getSelectedItem().toString()));
                if (softPortion.getSelectedItem().equals("0.33 l")) {
                    soft.setSmall("+");
                    updateField(findViewById(R.id.smallSoft), soft.getSmall());
                } else {
                    soft.setBig("+");
                    updateField(findViewById(R.id.bigSoft), soft.getBig());
                }
                break;
            case R.id.strongPlus:
                total.updateStrong(getPortion(strongPortion.getSelectedItem().toString()));
                if (strongPortion.getSelectedItem().equals("0.33 l")) {
                    strong.setSmall("+");
                    updateField(findViewById(R.id.smallStrong), strong.getSmall());
                } else {
                    strong.setBig("+");
                    updateField(findViewById(R.id.bigStrong), strong.getBig());
                }
                break;
            case R.id.winePlus:
                total.updateWine(getPortion(winePortion.getSelectedItem().toString()));
                if (winePortion.getSelectedItem().equals("12 cl")) {
                    wine.setSmall("+");
                    updateField(findViewById(R.id.smallWine), wine.getSmall());
                } else {
                    wine.setBig("+");
                    updateField(findViewById(R.id.bigWine), wine.getBig());
                }
                break;
            case R.id.liquorPlus:
                total.updateLiquor(getPortion(liquorPortion.getSelectedItem().toString()));
                if (liquorPortion.getSelectedItem().equals("4 cl")) {
                    liquor.setSmall("+");
                    updateField(findViewById(R.id.smallLiquor), liquor.getSmall());
                } else {
                    liquor.setBig("+");
                    updateField(findViewById(R.id.bigLiquor), liquor.getBig());
                }
                break;
        }
        totalText.setText(total.getAlcoholInBlood(testUser));
        portions.setText(String.valueOf(total.getPortions()));
        startTime();
        energy.setText(total.getCalories() + " kcal");
    }


    // Katso minus buttoneista mitä painettu
    public void onMinus(View view) {
        switch (view.getId()) {
            case R.id.softMinus:
                if (softPortion.getSelectedItem().equals("0.33 l")) {
                    if (Integer.parseInt(soft.getSmall()) > 0) {
                        total.updateSoft(-330);
                    }
                    soft.setSmall("-");
                    updateField(findViewById(R.id.smallSoft), soft.getSmall());
                } else {
                    if (Integer.parseInt(soft.getBig()) > 0) {
                        total.updateSoft(-500);
                    }
                    soft.setBig("-");
                    updateField(findViewById(R.id.bigSoft), soft.getBig());
                }
                break;
            case R.id.strongMinus:
                if (strongPortion.getSelectedItem().equals("0.33 l")) {
                    if (Integer.parseInt(strong.getSmall()) > 0) {
                        total.updateStrong(-330);
                    }
                    strong.setSmall("-");
                    updateField(findViewById(R.id.smallStrong), strong.getSmall());
                } else {
                    if (Integer.parseInt(strong.getBig()) > 0) {
                        total.updateStrong(-500);
                    }
                    strong.setBig("-");
                    updateField(findViewById(R.id.bigStrong), strong.getBig());
                }
                break;
            case R.id.wineMinus:
                if (winePortion.getSelectedItem().equals("12 cl")) {
                    if (Integer.parseInt(wine.getSmall()) > 0) {
                        total.updateWine(-120);
                    }
                    wine.setSmall("-");
                    updateField(findViewById(R.id.smallWine), wine.getSmall());
                } else {
                    if (Integer.parseInt(wine.getBig()) > 0) {
                        total.updateWine(-375);
                    }
                    wine.setBig("-");
                    updateField(findViewById(R.id.bigWine), wine.getBig());
                }
                break;
            case R.id.liquorMinus:
                if (liquorPortion.getSelectedItem().equals("4 cl")) {
                    if (Integer.parseInt(liquor.getSmall()) > 0) {
                        total.updateLiquor(-40);
                    }
                    liquor.setSmall("-");
                    updateField(findViewById(R.id.smallLiquor), liquor.getSmall());
                } else {
                    if (Integer.parseInt(liquor.getBig()) > 0) {
                        total.updateLiquor(-500);
                    }
                    liquor.setBig("-");
                    updateField(findViewById(R.id.bigLiquor), liquor.getBig());
                }
                break;
        }
        totalText.setText(total.getAlcoholInBlood(testUser));
        portions.setText(String.valueOf(total.getPortions()));
        startTime();
        energy.setText(total.getCalories() + " kcal");
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

    /**
     * @param view "add calendar" napin onclick
     */
    public void addToCalendar(View view) {
        if (total.getPortions() == 0) {
            return;
        }

        int i = getDateIndex(total.getDate());

        // Jos listalta ei löydy samalla pvm olevaa oliota
        // Lisää tiedot listalle
        if (i < 0) {
            days.getAllDays().add(total);
        }
        // Jos löytyy
        // Lisää juomat vanhoihin tietoihin
        else {
            DayInfo d = days.getOneDay(i);

            d.setSoftAmount(total.getSoftAmount() + d.getSoftAmount());
            d.setStrongAmount(total.getStrongAmount() + d.getStrongAmount());
            d.setWineAmount(total.getWineAmount() + d.getWineAmount());
            d.setLiquorAmount(total.getLiquorAmount() + d.getLiquorAmount());
            days.getAllDays().set(i, d);
        }

        List<DayInfo> lista = Singleton.getInstance().getAllDays();
        String json = gson.toJson(lista);

        SharedPreferences userPreferences = getSharedPreferences("database", MODE_PRIVATE);
        SharedPreferences.Editor editor = userPreferences.edit();
        editor.putString("KAIKKIDATA", json);
        editor.apply();
        resetFields();
        total = new DayInfo(formattedDate);
    }

    // Tarkastaa onko päivämäärällä jo tietoja
    // Jos on, niin lisää tiedot edellisen lisäksi
    public int getDateIndex(String date) {
        List<DayInfo> data = days.getAllDays();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getDate().equals(date)) {
                return i;
            }
        }
        return -1;
    }

    // Tyhjennä kentät
    public void resetFields() {
        soft.reset();
        strong.reset();
        wine.reset();
        liquor.reset();

        updateField(smallSoft, "0");
        updateField(bigSoft, "0");

        updateField(smallStrong, "0");
        updateField(bigStrong, "0");

        updateField(bigWine, "0");
        updateField(smallWine, "0");

        updateField(smallLiquor, "0");
        updateField(bigLiquor, "0");

        updateField(totalText, "0.0%");
        updateField(portions, "0");
        updateField(burnaus, "0");
        updateField(energy, "0");
    }

    public void startTime() {
        String test = timeText.getText().toString();
        if (test.isEmpty()) {
            test = "0";
        }
        int newTest = Integer.parseInt(test);
        int set = total.getBurningTime(testUser) - newTest;
        burnaus.setText(Integer.toString(set));
    }
}
