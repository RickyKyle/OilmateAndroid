package com.rickykyle.oilmate.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.Reading;
import com.rickykyle.oilmate.api.OilmateApi;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReadingListActivity extends AppCompatActivity {

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_list);

        result = findViewById(R.id.result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://159.65.93.37/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OilmateApi oilmateApi = retrofit.create(OilmateApi.class);

        Call<List<Reading>> call = oilmateApi.loadReadings();

        call.enqueue(new Callback<List<Reading>>() {
            @Override
            public void onResponse(Call<List<Reading>> call, Response<List<Reading>> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                List<Reading> readings = response.body();

                for (Reading reading : readings){
                    String readingsToOutput = "";
                    readingsToOutput += "Date: " + reading.getDate() + "\nTime: " + reading.getTime()
                        + "\nReading: " + reading.getReading() + "litres \n\n";
                    result.append(readingsToOutput);
                }

            }

            @Override
            public void onFailure(Call<List<Reading>> call, Throwable t) {
                result.setText((t.getMessage()));

            }

        });
    }

    public void onHomeButtonClick (View v) {
        Intent goToHome = new Intent(getBaseContext(), MainActivity.class);
        startActivity(goToHome);
    }
}
