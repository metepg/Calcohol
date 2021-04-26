package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.TypefaceCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
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
    int startD = 0;
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
        // calling method to get bar entries.
        getBarEntries(2, 8);
        testDate(0);

    }
    private void getBarEntries(int i, int j) {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(0, i));
        barEntriesArrayList.add(new BarEntry(1, j));
        barEntriesArrayList.add(new BarEntry(2, 0));
        barEntriesArrayList.add(new BarEntry(3, 0));
        barEntriesArrayList.add(new BarEntry(4, 0));
        barEntriesArrayList.add(new BarEntry(5, 0));
        barEntriesArrayList.add(new BarEntry(6, 0));

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
    public void testDate(int i){
        dateView = findViewById(R.id.dateView);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.WEEK_OF_YEAR, i);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date startOfWeek = c.getTime();

        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.add(Calendar.DAY_OF_WEEK, 6);
        Date endOfWeek = c.getTime();

        SimpleDateFormat formatt = new SimpleDateFormat("dd.MM.yyyy");
        String firstDate = formatt.format(startOfWeek);
        String lastDate = formatt.format(endOfWeek);
        dateView.setText(firstDate + " - " + lastDate);
    }

    public void nextWeek(View view){
        startD++;
        testDate(startD);
        System.out.println("next");
    }
    public void prevWeek(View view){
        startD--;
        testDate(startD);
        System.out.println("prev");
    }

}