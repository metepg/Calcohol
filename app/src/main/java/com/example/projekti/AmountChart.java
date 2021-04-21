package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import android.graphics.Color;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class AmountChart extends AppCompatActivity {
    // variable for our bar chart
    BarChart barChart;
    TextView dateView;
    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList<BarEntry> barEntriesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);


// initializing variable for bar chart.
        barChart = findViewById(R.id.idBarChart);
        dateView = findViewById(R.id.dateView);


        int calendar = new GregorianCalendar().get(Calendar.WEEK_OF_YEAR);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        Date startOfWeek = c.getTime();
        c.add(Calendar.DAY_OF_WEEK, 6);
        Date endOfWeek = c.getTime();

        SimpleDateFormat formatt = new SimpleDateFormat("dd.MM.yyyy");
        String firstDate = formatt.format(startOfWeek);
        String lastDate = formatt.format(endOfWeek);





        dateView.setText(firstDate+ " - " + lastDate + "\n"+"Week: " + calendar);
        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Servings of alcohol per day");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);

        barChart.getAxisRight().setAxisMaximum(50);
        barChart.getAxisLeft().setAxisMaximum(50);
        barChart.getAxisRight().setAxisMinimum(0);
        barChart.getAxisLeft().setAxisMinimum(0);
        XAxis axel = barChart.getXAxis();


        String[] dates = {"", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
        axel.setValueFormatter(new IndexAxisValueFormatter(dates));
    }

    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, 0));
        barEntriesArrayList.add(new BarEntry(2f, 0));
        barEntriesArrayList.add(new BarEntry(3f, 4));
        barEntriesArrayList.add(new BarEntry(4f, 0));
        barEntriesArrayList.add(new BarEntry(5f, 0));
        barEntriesArrayList.add(new BarEntry(6f, 0));
        barEntriesArrayList.add(new BarEntry(7f, 0));
    }
}