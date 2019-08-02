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

/*
 * This class is the view for the SetLowerLimit activity.
 */
public class SetLowerOilLimitActivity extends AppCompatActivity implements ChangeLowerOilLimitContract.View {

    // Creating variables for the views.
    Button submit;
    EditText newOilLimit;
    TextView currentOilLimitDisplay;

    // A list to store the response in.
    List<GetCurrentOilLimitResponse> currentOilLimitResponse;

    // To store the presenter.
    ChangeLowerOilLimitContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_lower_oil_limit);
        // Create a new instance of the ChangeLowerOilLimitPresenter
        presenter = new ChangeLowerOilLimitPresenter(this);
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
     * Initialises variables and sets the button action.
     */
    @Override
    public void setupUI() {
        currentOilLimitDisplay = findViewById(R.id.currentLowerLimitDisplay);
        currentOilLimitResponse = new ArrayList<>();

        submit = findViewById(R.id.changeOilLimitButton);

        // Posts captured data to the presenter on press.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newOilLimit = findViewById(R.id.newOilLimit);
                int newOilLimitAsInt = Integer.parseInt(newOilLimit.getText().toString());
                presenter.putNewLimit(Globals.userID, newOilLimitAsInt);
            }
        });
    }

    /*
     * Sets display with the oil limit.
     */
    public void updateCurrentOilLowerLimitDisplay(int updatedOilLimit){
        String formattedDisplay = updatedOilLimit + " litres";
        currentOilLimitDisplay.setText(formattedDisplay);
    }

    /*
     * Gets the current oil limit and displays it.
     */
    @Override
    public void displayCurrentLowerOilLimit(List<GetCurrentOilLimitResponse> currentOilLimit) {
        currentOilLimitResponse.addAll(currentOilLimit);
        int currentLowerOilLimit = currentOilLimitResponse.get(0).getOilLowerLimit();
        String formattedDisplay = currentLowerOilLimit + " litres";
        currentOilLimitDisplay.setText(formattedDisplay);
    }

    /*
     * Messages are shown as toasts.
     */
    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /*
     * When the back arrow is clicked, return to settings.
     */
    public void onOilLimitBackArrowClick (View v){
        Intent goToSettings =  new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(goToSettings);
    }
}
