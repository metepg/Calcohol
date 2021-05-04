package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <h1>Ohjelman pääaktiviteetti</h1>
 * <p>
 * <h2>Täältä käyttäjä pystyy:</h2>
 * <ul>
 *     <li>lisäämään juotuja juomia sovellukseen</li>
 *     <li>tarkastamaan alkoholin määrän veressä</li>
 *     <li>näkemään juotujen juomien kalorimäärän</li>
 * </ul>
 * <h2>Tästä aktiviteestia pääsee myös:</h2>
 * <ul>
 *     <li>muuttamaan tietoja (userSettings)</li>
 *     <li>tarkastelemaan lisättyjä tietoja graafisessa muodossa (AmountChart)</li>
 * </ul>
 */

public class MainActivity extends AppCompatActivity {
    Singleton days;
    Gson gson = new Gson();

    private final static String USER = "properties";
    private final Counter soft = new Counter();
    private final Counter strong = new Counter();
    private final Counter wine = new Counter();
    private final Counter liquor = new Counter();

    public final static String AGEKEY = "age";
    public final static String GENDERKEY = "gender";
    public final static String WEIGHTKEY = "weight";

    private DayInfo total;
    User user;

    // Pvm muuttujia
    LocalDate myObj;
    DateTimeFormatter getDate;
    String formattedDate;

    // Tekstikenttämuuttujat
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

    // Alasvetovalikkomuuttujat
    Spinner softPortion;
    Spinner strongPortion;
    Spinner winePortion;
    Spinner liquorPortion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tiedosto mistä löytyy käyttäjän tiedot (paino, sukupuoli, ikä)
        SharedPreferences sharedPrefs = getSharedPreferences(USER, MODE_PRIVATE);

        // Tällä lauseella tarkistetaan onko käyttäjätiedot syötetty
        // Jos on niin käynnistä aktiviteetin tapahtumat
        if (sharedPrefs.getBoolean("valuesSet", false)) {
            setContentView(R.layout.activity_main);
            loadData();

            // Alustetaan käyttäjätiedot user olioon
            int weight = Integer.parseInt(sharedPrefs.getString("weightValue", "0"));
            String gender = sharedPrefs.getString("genderValue", "man");
            int age = Integer.parseInt(sharedPrefs.getString("ageValue", "18"));
            user = new User(age, gender, weight);

            // Dropdownien hakeminen muuttujiksi
            softPortion = findViewById(R.id.softSpinner);
            strongPortion = findViewById(R.id.strongSpinner);
            winePortion = findViewById(R.id.wineSpinner);
            liquorPortion = findViewById(R.id.liquorSpinner);

            // Dropdownien tekstit

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

            // Tekstikentät muuttujiksi
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
            burnaus = findViewById(R.id.burningText);
            energy = findViewById(R.id.energyText);

            // Haetaan tämän päivän tiedot
            myObj = LocalDate.now();
            getDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formattedDate = myObj.format(getDate);
            total = new DayInfo(formattedDate);

            // Käyttäjän syöttämät juomat
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

    /**
     * Siirtyy datanäkymään
     *
     * @param view nappi elementti
     */
    public void onChart(View view) {
        Intent chart = new Intent(this, AmountChart.class);
        startActivity(chart);
    }

    /**
     * Siirtyy käyttäjätietojen päivitys aktiviteettiin
     *
     * @param view nappi elementti
     */
    public void onUser(View view) {

        String age = user.getAge();
        String gender = user.getGender();
        String weight = String.valueOf(user.getWeight());
        Intent user = new Intent(this, UserSettings.class);
        user.putExtra(AGEKEY, age);
        user.putExtra(GENDERKEY, gender);
        user.putExtra(WEIGHTKEY, weight);
        startActivity(user);
    }

    /**
     * Päivitä tekstikenttä annetun idn ja counterin mukaan
     *
     * @param id   päivitettävän tekstikentän id
     * @param text sinne syötettävä teksti
     */
    public void updateField(TextView id, String text) {
        id.setText(text);
    }

    /**
     * <p>Katsoo mitä näkymän plusnapeista on painettu</p>
     * <p>Päivittää kenttiä saadun napin idn mukaan</p>
     *
     * @param view näkymän elementti
     */
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
        totalText.setText(total.getAlcoholInBlood(user));
        portions.setText(String.valueOf(total.getPortions()));
        startTime();
        energy.setText(total.getCalories() + " kcal");
    }


    /**
     * <p>Katsoo mitä näkymän minusnapeista on painettu</p>
     * <p>Päivittää kenttiä saadun napin idn mukaan</p>
     *
     * @param view näkymän elementti
     */
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
        totalText.setText(total.getAlcoholInBlood(user));
        portions.setText(String.valueOf(total.getPortions()));
        startTime();
        energy.setText(total.getCalories() + " kcal");
    }

    /**
     * Palauttaa numeroarvon spinnereiden valintakentästä liukulukuna
     *
     * @param portion tekstiarvo esim "4 cl"
     * @return muuntaa syötetyn tekstiarvon millilitroiksi esim. "4 cl" -> 40.00
     */
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
     * <p>Tallentaa syötetyt tiedot listaan objektina</p>
     * <p>Jos saman päivän tietoja on olemassa jo, tallentaa niiden tietojen lisäksi</p>
     * <p>Lopuksi kääntää listan JSON-muotoon ja tallentaa sen tiedostoon</p>
     *
     * @param view "add calendar" napin onclick
     */
    public void addToCalendar(View view) {
        // Katso onko annoksia lisätty yhtään kun nappia painetaan
        if (total.getPortions() == 0) {
            return;
        }

        // Hae pvm avulla olion indeksi listalta
        int i = getDateIndex(total.getDate());

        // Jos listalta ei löydy samalla pvm olevaa oliota
        // Lisää tiedot listalle
        if (i == -1) {
            days.getAllDays().add(total);
        }

        // Jos löytyy niin lisää juomat vanhoihin tietoihin
        else {
            DayInfo d = days.getOneDay(i);
            d.setSoftAmount(total.getSoftAmount() + d.getSoftAmount());
            d.setStrongAmount(total.getStrongAmount() + d.getStrongAmount());
            d.setWineAmount(total.getWineAmount() + d.getWineAmount());
            d.setLiquorAmount(total.getLiquorAmount() + d.getLiquorAmount());
            days.getAllDays().set(i, d);
        }

        // Muuttaa datan JSON-muotoon
        List<DayInfo> lista = Singleton.getInstance().getAllDays();
        String json = gson.toJson(lista);

        // Tallenna tiedot tiedostoon "database"
        // Avain = "KAIKKIDATA"
        SharedPreferences userPreferences = getSharedPreferences("database", MODE_PRIVATE);
        SharedPreferences.Editor editor = userPreferences.edit();
        editor.putString("KAIKKIDATA", json);
        editor.apply();
        resetFields();
        total = new DayInfo(formattedDate); // Alusta uusi instanssi kun vanhan tiedot ovat tallennettu
    }

    /**
     * Tarkastaa löytyykö tallennetuista tiedoista jo saman päivän tietoja.
     *
     * @param date, päivämäärä millä oliota haetaan listasta. Formaatti ("yyyy,MM,dd") esim. 2021-05-28
     * @return, palauttaa etsittävän olion indeksin. Jos oliota ei löydy palauttaa (-1)
     */
    private int getDateIndex(String date) {
        List<DayInfo> data = days.getAllDays();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getDate().equals(date)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * <p>Laskee kuinka kauan kestää että alkoholi poistuu elimistöstä</p>
     * <p>Näyttää tuloksen tekstikentässä</p>
     */
    public void startTime() {
        String set = String.valueOf(total.getBurningTime(user));
        burnaus.setText(String.format("%s h", set));
    }

    /**
     * <p>Nollaa kaikki kentät</p>
     * <p>Tätä metodia kutsutaan tallennuksen jälkeen</p>
     */
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

}
