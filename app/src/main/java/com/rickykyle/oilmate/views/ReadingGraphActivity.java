package com.rickykyle.oilmate.views;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
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
import java.util.List;

public class ReadingGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_graph);

        setChart();
    }

    public void setChart() {

        LineChart chart = findViewById(R.id.lineChart);
        List<Entry> entries = new ArrayList<Entry>();

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
}

