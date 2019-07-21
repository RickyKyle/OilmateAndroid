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

public class ChangeTankDimensionsActivity extends AppCompatActivity implements ChangeTankDimensionsContract.View {

    Button submit;
    EditText newDiameter;
    EditText newLength;
    TextView currentDiameter;
    TextView currentLength;
    List<GetCurrentTankDimensionsResponse> currentTankDimensionsResponse;
    ChangeTankDimensionsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_tank_dimensions);
        presenter = new ChangeTankDimensionsPresenter(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void setupUI() {
        submit = findViewById(R.id.submitNewTankDimensionsButton);
        newDiameter = findViewById(R.id.newDiameter);
        newLength = findViewById(R.id.newLength);
        currentDiameter = findViewById(R.id.currentDiameter);
        currentLength = findViewById(R.id.currentLength);
        currentTankDimensionsResponse = new ArrayList<>();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double dDiameter = Double.parseDouble(newDiameter.getText().toString());
                double dLength = Double.parseDouble(newLength.getText().toString());
                presenter.putNewTankDimensions(Globals.userID, dDiameter, dLength);
            }
        });
    }

    @Override
    public void displayCurrentTankDimensions(List<GetCurrentTankDimensionsResponse> currentTankDimensions) {
        currentTankDimensionsResponse.addAll(currentTankDimensions);

        String length = String.valueOf(currentTankDimensionsResponse.get(0).getLength());
        String diameter = String.valueOf(currentTankDimensionsResponse.get(0).getDiameter());
        String formattedLength = ("l" + length + "cm");
        String formattedDiameter = ("d" + diameter + "cm");

        currentDiameter.setText(formattedDiameter);
        currentLength.setText(formattedLength);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateCurrentTankDimensionsDisplay(double diameter, double length) {
        String sLength = String.valueOf(length);
        String sDiameter = String.valueOf(diameter);
        String formattedLength = ("l" + sLength + "cm");
        String formattedDiameter = ("d" + sDiameter + "cm");

        currentDiameter.setText(formattedDiameter);
        currentLength.setText(formattedLength);
    }

    public void onTankDimensionsBackArrowClick (View v){
        Intent goToSettings =  new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(goToSettings);
    }
}
