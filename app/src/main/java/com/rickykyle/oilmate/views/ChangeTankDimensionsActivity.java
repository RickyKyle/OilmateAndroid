package com.rickykyle.oilmate.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.contracts.ChangeTankDimensionsContract;
import com.rickykyle.oilmate.network.responses.GetCurrentTankDimensionsResponse;
import com.rickykyle.oilmate.presenters.ChangeTankDimensionsPresenter;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.ArrayList;
import java.util.List;

/*
 * This class is the view for the ChangeTankDimensions activity.  It is responsible for
 * displaying the data and user interface.
 */
public class ChangeTankDimensionsActivity extends AppCompatActivity implements ChangeTankDimensionsContract.View {

    // Set up UI views
    Button submit;
    EditText newDiameter;
    EditText newLength;
    TextView currentDiameter;
    TextView currentLength;

    // List of the response objects
    List<GetCurrentTankDimensionsResponse> currentTankDimensionsResponse;

    // Instance of the presenter
    ChangeTankDimensionsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_tank_dimensions);

        // Create a new instance of the presenter.
        presenter = new ChangeTankDimensionsPresenter(this);
    }

    /*
     * Overrides transition between menus.
     */
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /*
     * Initialise views
     */
    @Override
    public void setupUI() {
        submit = findViewById(R.id.submitNewTankDimensionsButton);
        newDiameter = findViewById(R.id.newDiameter);
        newLength = findViewById(R.id.newLength);
        currentDiameter = findViewById(R.id.currentDiameter);
        currentLength = findViewById(R.id.currentLength);
        currentTankDimensionsResponse = new ArrayList<>();

        /*
         * Button on click.
         */
        submit.setOnClickListener(new View.OnClickListener() {
            /*
             * Convert data and submit to Presenter.
             */
            @Override
            public void onClick(View v) {
                double dDiameter = Double.parseDouble(newDiameter.getText().toString());
                double dLength = Double.parseDouble(newLength.getText().toString());
                presenter.putNewTankDimensions(Globals.userID, dDiameter, dLength);
            }
        });
    }

    /*
     * Display the current dimensions.
     */
    @Override
    public void displayCurrentTankDimensions(List<GetCurrentTankDimensionsResponse> currentTankDimensions) {
        // Add dimensions from response to local list.
        currentTankDimensionsResponse.addAll(currentTankDimensions);

        // Convert data types to those compatible with views.
        String length = String.valueOf(currentTankDimensionsResponse.get(0).getLength());
        String diameter = String.valueOf(currentTankDimensionsResponse.get(0).getDiameter());
        String formattedLength = ("l" + length + "cm");
        String formattedDiameter = ("d" + diameter + "cm");

        // Set data
        currentDiameter.setText(formattedDiameter);
        currentLength.setText(formattedLength);
    }

    /*
     * Show messages sent to the view as a toast.
     */
    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /*
     * When the dimensions change, change the displayed current dimensions.
     */
    @Override
    public void updateCurrentTankDimensionsDisplay(double diameter, double length) {
        String sLength = String.valueOf(length);
        String sDiameter = String.valueOf(diameter);
        String formattedLength = ("l" + sLength + "cm");
        String formattedDiameter = ("d" + sDiameter + "cm");

        currentDiameter.setText(formattedDiameter);
        currentLength.setText(formattedLength);
    }

    /*
     * Change views when the back arrow is clicked.
     */
    public void onTankDimensionsBackArrowClick (View v){
        Intent goToSettings =  new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(goToSettings);
    }
}
