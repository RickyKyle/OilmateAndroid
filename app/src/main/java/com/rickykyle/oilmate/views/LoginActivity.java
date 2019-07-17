package com.rickykyle.oilmate.views;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rickykyle.oilmate.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void onLoginClick (View v){
        Intent initiateLogin = new Intent(getBaseContext(), CurrentOilActivity.class);
        startActivity(initiateLogin);
    }

    public void onCreateAccountClick (View v){
        Intent initiateCreateAccount = new Intent(getBaseContext(), AccountCreateActivity.class);
        startActivity(initiateCreateAccount);
    }
}
