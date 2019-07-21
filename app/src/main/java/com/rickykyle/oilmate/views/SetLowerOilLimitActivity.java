package com.rickykyle.oilmate.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.contracts.ChangeLowerOilLimitContract;
import com.rickykyle.oilmate.network.responses.GetCurrentOilLimitResponse;
import com.rickykyle.oilmate.presenters.ChangeLowerOilLimitPresenter;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.ArrayList;
import java.util.List;

public class SetLowerOilLimitActivity extends AppCompatActivity implements ChangeLowerOilLimitContract.View {

    Button submit;
    EditText newOilLimit;
    TextView currentOilLimitDisplay;
    List<GetCurrentOilLimitResponse> currentOilLimitResponse;
    ChangeLowerOilLimitContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_lower_oil_limit);
        presenter = new ChangeLowerOilLimitPresenter(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void setupUI() {
        currentOilLimitDisplay = findViewById(R.id.currentLowerLimitDisplay);
        currentOilLimitResponse = new ArrayList<>();

        submit = findViewById(R.id.changeOilLimitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newOilLimit = findViewById(R.id.newOilLimit);
                int newOilLimitAsInt = Integer.parseInt(newOilLimit.getText().toString());
                presenter.putNewLimit(Globals.userID, newOilLimitAsInt);
            }
        });
    }

    public void updateCurrentOilLowerLimitDisplay(int updatedOilLimit){
        String formattedDisplay = updatedOilLimit + " litres";
        currentOilLimitDisplay.setText(formattedDisplay);
    }

    @Override
    public void displayCurrentLowerOilLimit(List<GetCurrentOilLimitResponse> currentOilLimit) {
        currentOilLimitResponse.addAll(currentOilLimit);
        int currentLowerOilLimit = currentOilLimitResponse.get(0).getOilLowerLimit();
        String formattedDisplay = currentLowerOilLimit + " litres";
        currentOilLimitDisplay.setText(formattedDisplay);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void onOilLimitBackArrowClick (View v){
        Intent goToSettings =  new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(goToSettings);
    }
}
