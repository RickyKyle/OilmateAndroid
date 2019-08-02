package com.rickykyle.oilmate.views;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.contracts.LoginContract;
import com.rickykyle.oilmate.presenters.LoginPresenter;

/*
 * Log in activity view.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    // New presenter.
    LoginContract.Presenter presenter;

    // Views
    EditText username;
    EditText password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Create new presenter for login.
        presenter = new LoginPresenter(this);
    }

    /*
     * Overrides transition animation.
     */
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void setupUI() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.loginButton);

        // Set up button for submitting data to presenter.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String submittedUsername = username.getText().toString();
                String submittedPassword = password.getText().toString();
                String token = "empty";
                presenter.postNewLogin(submittedUsername, submittedPassword, token);
            }
        });

    }

    /*
     * Messages show as toasts.
     */
    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }

    /*
     * Goes to readings.
     */
    @Override
    public void goToReadings(){
        Intent goToReadings = new Intent(getBaseContext(), CurrentOilActivity.class);
        startActivity(goToReadings);
    }


}
