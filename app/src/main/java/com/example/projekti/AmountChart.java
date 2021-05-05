package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import android.view.View;
import android.widget.TextView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Käyttäjä tarkastelee lisättyä dataa</h1>
 * <p>määritellään toiminnot barchart aktiviteettiin</p>
 * <p>
 * Käytetty kirjastoa <a href="https://github.com/PhilJay/MPAndroidChart">mpAndroidChart</a>
 *
 * @author TeemuT
 * @version 1.0
 */
public class AmountChart extends AppCompatActivity {
    Singleton savedData;

    // Muuttujat
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    TextView dateView;
    TextView year;

    // Tätä lukua kasvatetaan 1 jos halutaan nähdä seuraava viikko
    // 0 näyttää nykyisen viikon
    int startD = 0;

    // Arraylista johon tallentaan pylväiden aloitukset
    ArrayList<BarEntry> barEntriesArrayList;
    TextView soft;
    TextView strong;
    TextView wine;
    TextView liquor;
    TextView dateInfo;
    TextView calories;
    TextView dayOfWeek;
    TextView weekPortions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        barChart = findViewById(R.id.idBarChart);
        LocalDate startDay = getStartDay(0);
        getBarEntries(startDay);
        setDays(startDay);
    }

    /**
     * <p>Hakee aloitus päivän</p>
     *
     * @param i muuttuja
     * @return Palauttaa alkavan viikon ensimmäisen päivän
     */
    // First date of week as LocalDate object
    public LocalDate getStartDay(int i) {
        LocalDate date = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        date = date.plusWeeks(i);
        return date;
    }

    /**
     * <p>Laittaa oikeat pvm näkyville</p>
     *
     * @param c muuttuva muuttuja käyttäjän valitseman viikon mukaan
     */
    public void setDays(LocalDate c) {

        int currentYear = c.getYear();
        //formatoidaan päivät esim 30.02
        String firstDayOfWeek = c.format(DateTimeFormatter.ofPattern("dd.MM"));
        String lastDayOfWeek = c.plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM"));

        dateView = findViewById(R.id.dateView);
        year = findViewById(R.id.year);
        year.setText(String.valueOf(currentYear));
        String weekDates = firstDayOfWeek + " - " + lastDayOfWeek;
        //asetettaa dateview tekstielementin arvoksi ensimmäisen ja viimeisen päivän
        dateView.setText(weekDates);
    }

    /**
     * <p>Alustaa pylvästaulukon ja päivittää sitä</p>
     *
     * @param date Päivämuuttuja
     */
    private void getBarEntries(LocalDate date) {
        barEntriesArrayList = new ArrayList<>();

        soft = findViewById(R.id.soft);
        strong = findViewById(R.id.strong);
        wine = findViewById(R.id.wine);
        liquor = findViewById(R.id.liquor);
        dateInfo = findViewById(R.id.date);
        calories = findViewById(R.id.calories);
        dayOfWeek = findViewById(R.id.weekDay);
        weekPortions = findViewById(R.id.weekPortions);

        // Päivien tiedot arrayna
        List<DayInfo> days = getDays(date);

        // Viikonpäivät
        ArrayList<String> weekDays = new ArrayList<>();
        weekDays.add("Monday");
        weekDays.add("Tuesday");
        weekDays.add("Wednesday");
        weekDays.add("Thursday");
        weekDays.add("Friday");
        weekDays.add("Saturday");
        weekDays.add("Sunday");

        //Loopataan saatujen arvojen läpi ja lisätään juomat total muuttujalle
        int total = 0;
        for (int i = 0; i < days.size(); i++) {
            total += checkValue(days.get(i));
        }
        weekPortions.setText(String.valueOf(total));

        // Aseta juotujen annosten määrä viikonpäiville
        // Jos tietoja ei ole lisätty arvoksi tulee 0
        int monday = checkValue(days.get(0));
        int tuesday = checkValue(days.get(1));
        int wednesday = checkValue(days.get(2));
        int thursday = checkValue(days.get(3));
        int friday = checkValue(days.get(4));
        int saturday = checkValue(days.get(5));
        int sunday = checkValue(days.get(6));

        // Lisätään uudet tolpat jokaiselle viikon päivälle
        // X arvo määrittelee ensimmäisen tolpan
        barEntriesArrayList.add(new BarEntry(0, monday));
        barEntriesArrayList.add(new BarEntry(1, tuesday));
        barEntriesArrayList.add(new BarEntry(2, wednesday));
        barEntriesArrayList.add(new BarEntry(3, thursday));
        barEntriesArrayList.add(new BarEntry(4, friday));
        barEntriesArrayList.add(new BarEntry(5, saturday));
        barEntriesArrayList.add(new BarEntry(6, sunday));
        //Teksti pylväskaavion alla
        barDataSet = new BarDataSet(barEntriesArrayList, "Servings of alcohol per day");

        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            /**
             * <p>Tarkistaa onko pylvästä painettu, jos painettu pylväs korostetaan eri värillä</p>
             * @param e Haetaan määrät päiviltä ja annetaan arvot tekstikentille
             * @param h Painalluksella korostetaan painettua pylvästä
             */
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int i = Math.round(e.getX());
                if (days.get(i) == null) {
                    dateInfo.setText(formatDate(date.plusDays(i).toString()));
                    dayOfWeek.setText(weekDays.get(i));
                    resetFields();
                } else {
                    DayInfo data = days.get(i);

                    String weekDay = weekDays.get(i);
                    String date = formatDate(data.getDate());
                    String caloriesAmount = String.valueOf(data.getCalories());


                    soft.setText(data.getSoftPortions());
                    strong.setText(data.getStrongPortions());
                    wine.setText(data.getWinePortions());
                    liquor.setText(data.getLiquorPortions());
                    dateInfo.setText(date);
                    calories.setText(caloriesAmount);
                    dayOfWeek.setText(weekDay);
                }
            }

            /**
             * <p>Jos ei valittuna, ei tapahdu mitään</p>
             */
            @Override
            public void onNothingSelected() {

            }
        });
        //Luodaan uusi pylväs ja määritellään sen ulkoasu ja muuttujat
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.animateY(1500);
        barChart.setDrawBarShadow(true);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.setDrawValueAboveBar(true);
        barChart.getAxisLeft().setDrawZeroLine(true);
        barChart.setScaleEnabled(false);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
        barChart.getXAxis().setGridLineWidth(1);
        barChart.getAxisRight().setAxisMaximum(27);
        barChart.getAxisLeft().setAxisMaximum(27);
        barChart.getAxisRight().setAxisMinimum(0);
        barChart.getAxisLeft().setAxisMinimum(0);

        XAxis axelx = barChart.getXAxis();
        String[] dates = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
        axelx.setValueFormatter(new IndexAxisValueFormatter(dates));
        barData.setValueFormatter(new ValueFormatter() {
            /**
             * <p>Muuttaa pylväskaavio numerot tekstiksi, ilman desimaalia.</p>
             * @param value Muuttuja
             * @return palauttaa pylväiden arvot tekstiksi, ilman desimaalia. Jos arvoa ei ole saatu niin palauttaa tyhjän tekstin
             */
            @Override
            public String getFormattedValue(float value) {
                if (value > 0) {
                    return String.valueOf(Math.round(value));
                } else {
                    return "";
                }
            }
        });
    }

    /**
     * <p>Palauttaa päivän ja kuukauden</p>
     *
     * @param date pvm esim. 2021-05-20
     * @return Palauttaa päivän ja kuukauden esim. 20.05
     */
    public String formatDate(String date) {
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        return day + "." + month;
    }

    /**
     * <p>Katsoo onko annoksia enemmän kuin nolla</p>
     *
     * @param value Muuttuja
     * @return Palauttaa arvon nolla jos päivälle ei ole lisättty annoksia
     */
    public int checkValue(DayInfo value) {
        return value == null ? 0 : value.getPortions();
    }

    /**
     * <p>Asettaaa teksti kenttien alkuarvoksi "0" tekstinä</p>
     */
    public void resetFields() {
        soft.setText("0");
        strong.setText("0");
        wine.setText("0");
        liquor.setText("0");
        calories.setText("0");
    }

    /**
     * <p>Viikon eteenpäin painalluksella</p>
     *
     * @param view nappi
     */
    // Next button actions
    public void nextWeek(View view) {
        startD++;
        LocalDate startDay = getStartDay(startD);
        getBarEntries(startDay);
        setDays(startDay);
        resetFields();
        dateInfo.setText("");
        dayOfWeek.setText("");
    }

    /**
     * <p>Palaa viikon taaksepäin painalluksella</p>
     *
     * @param view nappi
     */
    public void prevWeek(View view) {
        startD--;
        LocalDate startDay = getStartDay(startD);
        getBarEntries(startDay);
        setDays(startDay);
        resetFields();
        dateInfo.setText("");
        dayOfWeek.setText("");
    }

    /**
     * <p>Palaa takaisin päänäkymään</p>
     *
     * @param view nappi pääaktiviteettiin
     */
    public void onBackP(View view) {
        super.onBackPressed();
        finish();
    }

    /**
     * <p>Haetaan listalta pvm mukaan viikolle dataa</p>
     *
     * @param date Verrataan tämän hetkistä viikkoa nykyiseen viikkoon
     * @return palautetaan muuttuja pvt saadut arvot
     */
    public List<DayInfo> getDays(LocalDate date) {
        //loopataan kokonainen viikko
        savedData = Singleton.getInstance();
        List<DayInfo> days = savedData.getAllDays();
        List<DayInfo> pvt = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {

            // Pvm millä haetaan oliota
            // Kasvatetaan joka loopin jälkeen yhdellä
            String matchDate = date.plusDays(i).toString();

            // Käy tallennetut tiedot läpi ja etsi pvm mukaan oliota
            for (int j = 0; j < days.size(); j++) {
                String pv = days.get(j).getDate();
                if (pv.equals(matchDate)) {
                    pvt.add(days.get(j));
                }
            }
            // Jos listalta ei löydy tietoja pvm mukaan
            // Lisää indeksin kohdalle null
            if (i >= pvt.size()) {
                pvt.add(i, null);
            }
        }
        return pvt;
    }
}