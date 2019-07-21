package com.rickykyle.oilmate.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.contracts.SetTargetTemperatureContract;
import com.rickykyle.oilmate.network.responses.GetCurrentTargetTemperatureResponse;
import com.rickykyle.oilmate.presenters.SetTargetTemperaturePresenter;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.ArrayList;
import java.util.List;

public class SetTargetTemperatureActivity extends AppCompatActivity implements SetTargetTemperatureContract.View {

    Button submit;
    EditText newTargetTemperature;
    TextView currentTargetTemperatureTextView;
    List<GetCurrentTargetTemperatureResponse> currentTargetTemperatureResponse;
    SetTargetTemperatureContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_target_temperature);
        presenter = new SetTargetTemperaturePresenter(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void setupUI() {
        currentTargetTemperatureTextView = findViewById(R.id.currentTargetTemperature);
        currentTargetTemperatureResponse = new ArrayList<>();

        submit = findViewById(R.id.submitNewTargetTemperatureButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTargetTemperature = findViewById(R.id.newTargetTemperature);
                int newTargetTemperatureAsInt = Integer.parseInt(newTargetTemperature.getText().toString());
                presenter.putNewTargetTemperature(Globals.userID, newTargetTemperatureAsInt);
            }
        });


    }

    @Override
    public void displayCurrentTargetTemperature(List<GetCurrentTargetTemperatureResponse> currentTargetTemperature) {
        currentTargetTemperatureResponse.addAll(currentTargetTemperature);
        String sCurrentTargetTemperature = String.valueOf(currentTargetTemperature.get(0).getSetTemperature());
        String formattedDisplay = sCurrentTargetTemperature + " degrees";
        currentTargetTemperatureTextView.setText(formattedDisplay);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void updateCurrentTargetTemperatureDisplay(int targetTemperature) {
        String formattedDisplay = targetTemperature + " degrees";
        currentTargetTemperatureTextView.setText(formattedDisplay);
    }

    public void onSetTargetTemperatureBackArrowClick (View v){
        Intent goToSettings = new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(goToSettings);
    }
}
