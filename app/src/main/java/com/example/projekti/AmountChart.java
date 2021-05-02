package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class AmountChart extends AppCompatActivity {
    // variable for our bar chart
    BarChart barChart;
    TextView dateView;
    // variable for our bar data.
    BarData barData;
    int startD = 0;
    // variable for our bar data set.
    BarDataSet barDataSet;
    Singleton savedData;

    // array list for storing entries.
    ArrayList<BarEntry> barEntriesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        // initializing variable for bar chart.
        barChart = findViewById(R.id.idBarChart);

        // calling method to get bar entries.
        LocalDate startDay = getStartDay(0);
        getBarEntries(startDay);
        setDays(startDay);

    }

    // First date of week as LocalDate object
    public LocalDate getStartDay(int i) {
        LocalDate date = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        date = date.plusWeeks(i);
        return date;
    }

    // Laittaa oikeat pvm näkyville
    public void setDays(LocalDate c) {

        String firstDayOfWeek = c.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String lastDayOfWeek = c.plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        dateView = findViewById(R.id.dateView);

        dateView.setText(firstDayOfWeek + " - " + lastDayOfWeek);
    }

    private void getBarEntries(LocalDate date) {
        // Barchart array
        barEntriesArrayList = new ArrayList<>();

        // Päivien tiedot arrayna
        List<DayInfo> days = getDays(date);

        // Aseta juotujen annosten määrä viikonpäiville
        // Jos tietoja ei ole lisätty arvoksi tulee 0
        int monday = checkValue(days.get(0));
        int tuesday = checkValue(days.get(1));
        int wednesday =  checkValue(days.get(2));
        int thursday = checkValue(days.get(3));
        int friday = checkValue(days.get(4));
        int saturday = checkValue(days.get(5));
        int sunday = checkValue(days.get(6));


        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(0, monday));
        barEntriesArrayList.add(new BarEntry(1, tuesday));
        barEntriesArrayList.add(new BarEntry(2, wednesday));
        barEntriesArrayList.add(new BarEntry(3, thursday));
        barEntriesArrayList.add(new BarEntry(4, friday));
        barEntriesArrayList.add(new BarEntry(5, saturday));
        barEntriesArrayList.add(new BarEntry(6, sunday));

        barDataSet = new BarDataSet(barEntriesArrayList, "Servings of alcohol per day");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        barChart.animateY(1000);

        Legend legend = barChart.getLegend();
        legend.setEnabled(false);
        barChart.setDrawBarShadow(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.setDrawValueAboveBar(false);


        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
        barChart.getXAxis().setGridLineWidth(1);

        barChart.getAxisRight().setAxisMaximum(50);
        barChart.getAxisLeft().setAxisMaximum(50);
        barChart.getAxisRight().setAxisMinimum(0);
        barChart.getAxisLeft().setAxisMinimum(0);
        XAxis axelx = barChart.getXAxis();
        String[] dates = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
        axelx.setValueFormatter(new IndexAxisValueFormatter(dates));
    }

    public int checkValue(DayInfo value){
        return value == null ? 0 : value.getPortions();
    }

    // Next button actions
    public void nextWeek(View view) {
        startD++;
        LocalDate startDay = getStartDay(startD);
        getBarEntries(startDay);
        setDays(startDay);
        System.out.println("next");
    }

    // Previous button actions
    public void prevWeek(View view) {
        startD--;
        LocalDate startDay = getStartDay(startD);
        getBarEntries(startDay);
        setDays(startDay);
        System.out.println("prev");
    }

    // Back button action
    public void onBackP(View view) {
        super.onBackPressed();
        finish();
    }

    // Hae oikeat päivät listasta
    public List<DayInfo> getDays(LocalDate date) {

        savedData = Singleton.getInstance();
        List<DayInfo> days = savedData.getAllDays();
        List<DayInfo> pvt = new ArrayList();
        for (int i = 0; i <= 6; i++) {
            String matchDate = date.plusDays(i).toString(); // Current date to match

            // Loop through saved info
            for(int j = 0; j < days.size(); j++) {
                String pv = days.get(j).getDate();
                if(pv.equals(matchDate)) {
                    pvt.add(days.get(j));
                }
            }
            // If no match add null instead of DayInfo to List
            try {
                pvt.get( i );
            } catch ( IndexOutOfBoundsException e ) {
                pvt.add( i, null );
            }
        }
        return pvt;
    }

}