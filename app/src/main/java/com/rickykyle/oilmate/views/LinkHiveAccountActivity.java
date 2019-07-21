package com.rickykyle.oilmate.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.contracts.LinkHiveAccountContract;
import com.rickykyle.oilmate.network.responses.GetCurrentHiveAccountResponse;
import com.rickykyle.oilmate.presenters.LinkHiveAccountPresenter;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.List;

public class LinkHiveAccountActivity extends AppCompatActivity implements LinkHiveAccountContract.View {

    Button submit;
    TextView hiveDescription;
    TextView hiveEmailTitle;
    TextView hivePasswordTitle;
    TextView hiveTargetTemperatureTitle;
    TextView hiveAccountLinked;
    EditText hiveEmail;
    EditText hivePassword;
    EditText hiveTargetTemperature;
    ImageView greentick;
    LinkHiveAccountContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_hive_account);
        presenter = new LinkHiveAccountPresenter(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void setupUI() {
        hiveDescription = findViewById(R.id.hiveDescription);
        hiveEmailTitle = findViewById(R.id.hiveEmailTitle);
        hivePasswordTitle = findViewById(R.id.hivePasswordTitle);
        hiveTargetTemperatureTitle = findViewById(R.id.hiveTargetTemperatureTitle);
        hiveEmail = findViewById(R.id.hiveEmail);
        hivePassword = findViewById(R.id.hivePassword);
        hiveTargetTemperature = findViewById(R.id.hiveTargetTempeature);
        hiveAccountLinked = findViewById(R.id.hiveAccountLinked);
        greentick = findViewById(R.id.greenTick);

        submit = findViewById(R.id.linkNewHiveAccountButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = hiveEmail.getText().toString();
                String password = hivePassword.getText().toString();
                int setTemperature = Integer.parseInt(hiveTargetTemperature.getText().toString());
                presenter.postNewHiveAccount(username, password, setTemperature);
            }
        });
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateCurrentHiveAccountDisplay(Boolean accountExists) {
        if (accountExists){
            hiveDescription.setVisibility(View.INVISIBLE);
            hiveEmailTitle.setVisibility(View.INVISIBLE);
            hivePasswordTitle.setVisibility(View.INVISIBLE);
            hiveTargetTemperatureTitle.setVisibility(View.INVISIBLE);
            hiveEmail.setVisibility(View.INVISIBLE);
            hivePassword.setVisibility(View.INVISIBLE);
            hiveTargetTemperature.setVisibility(View.INVISIBLE);
            submit.setVisibility(View.INVISIBLE);
            greentick.setVisibility(View.VISIBLE);
            hiveAccountLinked.setVisibility(View.VISIBLE);
        }
    }

    public void onLinkHiveAccountBackArrowClick (View v){
        Intent goToSettings = new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(goToSettings);
    }
}
