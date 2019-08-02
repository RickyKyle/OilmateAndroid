package com.rickykyle.oilmate.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.contracts.CurrentOilContract;
import com.rickykyle.oilmate.utilities.Globals;

/*
 * Class consisting mostly of buttons to navigate to each of the activities which
 * customise the user's experience.
 */
public class SettingsActivity extends AppCompatActivity {

    ImageView backArrow;
    SharedPreferences sharedPreferences;

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

    /*
     * Erases session variables and sends user the login screen.
     */
    public void onLogOutButtonClick(View vi){
        Globals.userID = 0;
        Globals.token = null;
        Intent goToLogIn = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(goToLogIn);
    }





}
