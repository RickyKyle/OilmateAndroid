package com.rickykyle.oilmate.views;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.adapters.ReadingListAdapter;
import com.rickykyle.oilmate.entities.Reading;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadingListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_list);
        setReadingListRecycler();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    public void setReadingListRecycler(){
        recyclerView = findViewById(R.id.reading_list_recycler);

        List<Reading> reversedReadingList = new ArrayList<>();
        reversedReadingList.addAll(Globals.readings);
        Collections.reverse(reversedReadingList);

        adapter = new ReadingListAdapter(reversedReadingList);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }


    public void onHomeButtonClick(View v) {
        Intent goToHome = new Intent(getBaseContext(), CurrentOilActivity.class);
        startActivity(goToHome);
    }

    public void onGraphButtonClick(View v){
        Intent goToGraph =  new Intent(getBaseContext(), ReadingGraphActivity.class);
        startActivity(goToGraph);
    }

    public void onSettingsButtonClick(View v){
        Intent goToSettings = new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(goToSettings);
    }
}