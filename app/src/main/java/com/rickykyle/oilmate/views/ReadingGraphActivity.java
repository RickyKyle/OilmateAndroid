package com.rickykyle.oilmate.views;

import android.content.Intent;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.entities.Reading;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.ArrayList;

public class ReadingGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_graph);
        setChart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void setChart() {

        LineChart chart = findViewById(R.id.lineChart);
        ArrayList<Entry> entries = new ArrayList<Entry>();
        ArrayList<String> dateTime = new ArrayList<>();


        for(Reading reading : Globals.readings){
            entries.add(new Entry (reading.getReadingID(), reading.getReading()));
        }

        chart.setDrawBorders(false);
        chart.setNoDataText("No data currently available.");
        LineDataSet dataSet = new LineDataSet(entries, "Oil Usage");
        dataSet.setColor(Color.BLACK);
        dataSet.setLineWidth(3f);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();
    }


    public void onHomeButtonClick(View v) {
        Intent goToHome = new Intent(getBaseContext(), CurrentOilActivity.class);
        startActivity(goToHome);
    }

    public void onReadingListClick(View v) {
        Intent goToReadingList = new Intent(getBaseContext(), ReadingListActivity.class);
        startActivity(goToReadingList);
    }

    public void onSettingsButtonClick(View v){
        Intent goToSettings = new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(goToSettings);
    }
}

