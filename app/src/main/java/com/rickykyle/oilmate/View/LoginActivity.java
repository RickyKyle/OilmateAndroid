package com.rickykyle.oilmate.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rickykyle.oilmate.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginClick (View v){
        Intent initiateLogin = new Intent(getBaseContext(), MainActivity.class);
        startActivity(initiateLogin);
    }

    public void onCreateAccountClick (View v){
        Intent initiateCreateAccount = new Intent(getBaseContext(), AccountCreateActivity.class);
        startActivity(initiateCreateAccount);
    }
}
