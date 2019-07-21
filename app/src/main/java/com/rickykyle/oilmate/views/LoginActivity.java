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

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    LoginContract.Presenter presenter;
    EditText username;
    EditText password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
    }

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

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void goToReadings(){
        Intent goToReadings = new Intent(getBaseContext(), CurrentOilActivity.class);
        startActivity(goToReadings);
    }


}
