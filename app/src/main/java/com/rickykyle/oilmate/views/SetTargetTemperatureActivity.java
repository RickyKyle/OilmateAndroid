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

/*
 * This class is the view for the setting of the target temperature for Hive users.
 */
public class SetTargetTemperatureActivity extends AppCompatActivity implements SetTargetTemperatureContract.View {

    // Create variables for the views, data, and presenter.
    Button submit;
    EditText newTargetTemperature;
    TextView currentTargetTemperatureTextView;
    List<GetCurrentTargetTemperatureResponse> currentTargetTemperatureResponse;
    SetTargetTemperatureContract.Presenter presenter;

    /*
     * On creation of this view, a new presenter is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_target_temperature);
        presenter = new SetTargetTemperaturePresenter(this);
    }

    /*
     * Override transition animation.
     */
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /*
     * Initialises variables and sets up the button action.
     */
    @Override
    public void setupUI() {
        currentTargetTemperatureTextView = findViewById(R.id.currentTargetTemperature);
        currentTargetTemperatureResponse = new ArrayList<>();

        submit = findViewById(R.id.submitNewTargetTemperatureButton);

        // On click, format and send data to presenter.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTargetTemperature = findViewById(R.id.newTargetTemperature);
                int newTargetTemperatureAsInt = Integer.parseInt(newTargetTemperature.getText().toString());
                presenter.putNewTargetTemperature(Globals.userID, newTargetTemperatureAsInt);
            }
        });
    }

    /*
     * Takes and displays the current target temperature from the presenter.
     */
    @Override
    public void displayCurrentTargetTemperature(List<GetCurrentTargetTemperatureResponse> currentTargetTemperature) {
        currentTargetTemperatureResponse.addAll(currentTargetTemperature);
        String sCurrentTargetTemperature = String.valueOf(currentTargetTemperature.get(0).getSetTemperature());
        String formattedDisplay = sCurrentTargetTemperature + " degrees";
        currentTargetTemperatureTextView.setText(formattedDisplay);
    }

    /*
     * Shows message as a toast.
     */
    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }

    /*
     * Updates display when the temperature has changed.
     */
    @Override
    public void updateCurrentTargetTemperatureDisplay(int targetTemperature) {
        String formattedDisplay = targetTemperature + " degrees";
        currentTargetTemperatureTextView.setText(formattedDisplay);
    }

    /*
     * Back arrow returns user to settings.
     */
    public void onSetTargetTemperatureBackArrowClick (View v){
        Intent goToSettings = new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(goToSettings);
    }
}
