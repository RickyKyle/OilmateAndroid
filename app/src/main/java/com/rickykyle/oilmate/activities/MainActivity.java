package com.rickykyle.oilmate.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rickykyle.oilmate.api.OilmateApi;
import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.Reading;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.remainingOil);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://159.65.93.37/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OilmateApi oilmateApi = retrofit.create(OilmateApi.class);

        Call<List<Reading>> call = oilmateApi.getReadings();

        call.enqueue(new Callback<List<Reading>>() {
            @Override
            public void onResponse(Call<List<Reading>> call, Response<List<Reading>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Reading> readings = response.body();
                String displayLatestReading = "";
                Reading latestReading = readings.get(readings.size() - 1);
                int remainingOil = (int) latestReading.getReading();
                String displayRemainingOil = remainingOil + " litres";
                textViewResult.append(displayRemainingOil);

            }

            @Override
            public void onFailure(Call<List<Reading>> call, Throwable t) {
                textViewResult.setText((t.getMessage()));

            }

        });



    }

    public void onReadingListClick (View v){
        Intent goToReadingList = new Intent(getBaseContext(), ReadingListActivity.class);
        startActivity(goToReadingList);
    }

}
