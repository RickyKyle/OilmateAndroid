package com.rickykyle.oilmate.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.entities.Reading;
import com.rickykyle.oilmate.utilities.Globals;

public class ReadingListActivity extends AppCompatActivity {

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_list);

        result = findViewById(R.id.result);
        setReadingList();
    }

    public void setReadingList(){
        for (Reading reading : Globals.readings) {
            String readingsToOutput = "";
            readingsToOutput += "Date: " + reading.getDate() + "\nTime: " + reading.getTime()
                    + "\nReading: " + reading.getReading() + "litres \n\n";
            result.append(readingsToOutput);
        }
    }

    public void onHomeButtonClick(View v) {
        Intent goToHome = new Intent(getBaseContext(), CurrentOilActivity.class);
        startActivity(goToHome);
    }

    public void onGraphButtonClick(View v){
        Intent goToGraph =  new Intent(getBaseContext(), ReadingGraphActivity.class);
        startActivity(goToGraph);
    }
}