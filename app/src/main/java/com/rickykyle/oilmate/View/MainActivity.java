package com.rickykyle.oilmate.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.rickykyle.oilmate.API.OilmateApi;
import com.rickykyle.oilmate.API.ServiceCreator;
import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.Reading;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private TextView remainingOil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        remainingOil = findViewById(R.id.remainingOil);
        networkCallAndResponse();
    }

    public void networkCallAndResponse(){
        OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);
        Call<List<Reading>> call = oilmateApi.loadReadings();

        call.enqueue(new Callback<List<Reading>>() {

            @Override
            public void onResponse(Call<List<Reading>> call, Response<List<Reading>> response) {
                if (!response.isSuccessful()) {
                    remainingOil.setText("Code: " + response.code());
                    return;
                }

                List<Reading> readings = response.body();
                int latestReading = (int) readings.get(readings.size() - 1).getReading();
                String result = (latestReading + " litres");
                remainingOil.append(result);
            }

            @Override
            public void onFailure(Call<List<Reading>> call, Throwable t) {
                remainingOil.setText((t.getMessage()));
            }

        });
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
