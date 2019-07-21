package com.rickykyle.oilmate.views;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.contracts.CurrentOilContract;

public class SettingsActivity extends AppCompatActivity {

    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void onBackArrowClick (View v){
        Intent goHome =  new Intent(getBaseContext(), CurrentOilActivity.class);
        startActivity(goHome);
    }

    public void onSetLowerOilLimitClick(View v){
        Intent goToSetLowerOilLimit =  new Intent(getBaseContext(), SetLowerOilLimitActivity.class);
        startActivity(goToSetLowerOilLimit);
    }

    public void onChangeTankDimensionsClick (View v){
        Intent goToChangeTankDimensions =  new Intent(getBaseContext(), ChangeTankDimensionsActivity.class);
        startActivity(goToChangeTankDimensions);
    }

    public void onLinkHiveAccountClick (View v){
        Intent goToLinkHive = new Intent(getBaseContext(), LinkHiveAccountActivity.class);
        startActivity(goToLinkHive);
    }

    public void onSetTargetTemperatureClick (View v){
        Intent goToTargetTemperature = new Intent(getBaseContext(), SetTargetTemperatureActivity.class);
        startActivity(goToTargetTemperature);
    }





}
