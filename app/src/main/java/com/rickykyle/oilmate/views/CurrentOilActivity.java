package com.rickykyle.oilmate.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rickykyle.oilmate.contracts.CurrentOilContract;
import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.network.responses.Reading;
import com.rickykyle.oilmate.presenters.ReadingPresenter;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.ArrayList;
import java.util.List;

/*
 * This is the view class for the Home screen / current oil reading screen.
 */
public class CurrentOilActivity extends AppCompatActivity implements CurrentOilContract.View {

    // Set up views.
    private TextView remainingOil;

    CurrentOilContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // New instance of presenter.
        presenter = new ReadingPresenter(this);
    }

    /*
     * Overrides transition animation.
     */
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /*
     * Initialise variables.
     */
    @Override
    public void setupUI() {
        remainingOil = findViewById(R.id.remainingOil);
        Globals.readings = new ArrayList<>();
    }

    /*
     * Get the readings from the model and put them in a global list, then show
     * the most recent one.
     */
    @Override
    public void displayReadingData(List<Reading> readingList) {

        if (Globals.readings != null) {
            Globals.readings.clear();
        }

        Globals.readings.addAll(readingList);
        int latestReading = (int) Globals.readings.get(Globals.readings.size() - 1).getReading();
        String result = (latestReading + " litres");
        remainingOil.append(result);
    }

    /*
     * Messages passed to the view are toasts.
     */
    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        Log.d("Error!", "showMessage: " + msg);
    }

    /*
     * When the reading list icon is clicked, go to reading list.
     */
    public void onReadingListClick(View v) {
        Intent goToReadingList = new Intent(getBaseContext(), ReadingListActivity.class);
        startActivity(goToReadingList);
    }

    /*
     * When the graph icon is clicked, go to the graph.
     */
    public void onGraphButtonClick(View v){
        Intent goToGraph =  new Intent(getBaseContext(), ReadingGraphActivity.class);
        startActivity(goToGraph);
    }

    /*
     * When the settings cog is clicked, go to settings.
     */
    public void onSettingsButtonClick(View v){
        Intent goToSettings = new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(goToSettings);
    }
}