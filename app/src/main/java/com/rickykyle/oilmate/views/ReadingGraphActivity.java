//package com.rickykyle.oilmate.views;
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//
//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.LineData;
//import com.github.mikephil.charting.data.LineDataSet;
//import com.rickykyle.oilmate.R;
//import com.rickykyle.oilmate.entities.Reading;
//import com.rickykyle.oilmate.network.OilmateApi;
//import com.rickykyle.oilmate.network.ServiceCreator;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class ReadingGraphActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reading_graph);
//
//        OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);
//        Call<List<Reading>> call = oilmateApi.loadReadings();
//
//        call.enqueue(new Callback<List<Reading>>() {
//            @Override
//            public void onResponse(Call<List<Reading>> call, Response<List<Reading>> response) {
//                if (!response.isSuccessful()) {
//                    System.out.print("Code: " + response.code());
//                    return;
//                }
//                LineChart chart = findViewById(R.id.lineChart);
//
//                List<Reading> readings = response.body();
//
//                List<Entry> entries = new ArrayList<Entry>();
//
//                for(Reading reading : readings){
//                    entries.add(new Entry (reading.getReadingID(), reading.getReading()));
//                }
//
//                chart.setDrawBorders(false);
//                chart.setNoDataText("No data currently available.");
//                LineDataSet dataSet = new LineDataSet(entries, "Oil Usage");
//                dataSet.setColor(Color.BLACK);
//                dataSet.setLineWidth(3f);
//
//                LineData lineData = new LineData(dataSet);
//                chart.setData(lineData);
//                chart.invalidate();
//            }
//
//            @Override
//            public void onFailure(Call<List<Reading>> call, Throwable t) {
//                System.out.print((t.getMessage()));
//
//            }
//        });
//    }
//
//    public void onHomeButtonClick(View v) {
//        Intent goToHome = new Intent(getBaseContext(), CurrentOilActivity.class);
//        startActivity(goToHome);
//    }
//
//    public void onReadingListClick(View v) {
//        Intent goToReadingList = new Intent(getBaseContext(), ReadingListActivity.class);
//        startActivity(goToReadingList);
//    }
//}
//
