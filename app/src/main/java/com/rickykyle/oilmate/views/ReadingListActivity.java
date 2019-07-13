//package com.rickykyle.oilmate.views;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import com.rickykyle.oilmate.R;
//import com.rickykyle.oilmate.entities.Reading;
//import com.rickykyle.oilmate.network.OilmateApi;
//import com.rickykyle.oilmate.network.ServiceCreator;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class ReadingListActivity extends AppCompatActivity {
//
//    private TextView result;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reading_list);
//
//        result = findViewById(R.id.result);
//
//        OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);
//        Call<List<Reading>> call = oilmateApi.getOilReadings();
//
//        call.enqueue(new Callback<List<Reading>>() {
//            @Override
//            public void onResponse(Call<List<Reading>> call, Response<List<Reading>> response) {
//                if (!response.isSuccessful()) {
//                    result.setText("Code: " + response.code());
//                    return;
//                }
//
//                List<Reading> readings = response.body();
//
//                for (Reading reading : readings) {
//                    String readingsToOutput = "";
//                    readingsToOutput += "Date: " + reading.getDate() + "\nTime: " + reading.getTime()
//                            + "\nReading: " + reading.getReading() + "litres \n\n";
//                    result.append(readingsToOutput);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Reading>> call, Throwable t) {
//                result.setText((t.getMessage()));
//
//            }
//
//        });
//    }
//
//    public void onHomeButtonClick(View v) {
//        Intent goToHome = new Intent(getBaseContext(), CurrentOilActivity.class);
//        startActivity(goToHome);
//    }
//
//    public void onGraphButtonClick(View v){
//        Intent goToGraph =  new Intent(getBaseContext(), ReadingGraphActivity.class);
//        startActivity(goToGraph);
//    }
//}