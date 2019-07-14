package com.rickykyle.oilmate.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rickykyle.oilmate.contracts.CurrentOilContract;
import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.entities.Reading;
import com.rickykyle.oilmate.presenters.ReadingPresenter;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.ArrayList;
import java.util.List;


public class CurrentOilActivity extends AppCompatActivity implements CurrentOilContract.View {

    private TextView remainingOil;
    CurrentOilContract.Presenter presenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new ReadingPresenter(this);
    }

    @Override
    public void setupUI() {

        progressDialog = new ProgressDialog(this);
        remainingOil = findViewById(R.id.remainingOil);

        Globals.readings = new ArrayList<>();
    }

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

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        Log.d("Error!", "showMessage: " + msg);
    }

    @Override
    public void showProgressDialog() {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setMessage("Loading...");
        } else {
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);

            try {
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void hideProgressDialog() {

        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog.hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onReadingListClick(View v) {
        Intent goToReadingList = new Intent(getBaseContext(), ReadingListActivity.class);
        startActivity(goToReadingList);
    }

    public void onGraphButtonClick(View v){
        Intent goToGraph =  new Intent(getBaseContext(), ReadingGraphActivity.class);
        startActivity(goToGraph);
    }
}