package com.rickykyle.oilmate.presenters;

import android.content.Intent;
import android.util.Log;

import com.rickykyle.oilmate.contracts.LoginContract;
import com.rickykyle.oilmate.models.LoginModel;
import com.rickykyle.oilmate.network.requests.LoginRequestResponse;
import com.rickykyle.oilmate.utilities.Globals;
import com.rickykyle.oilmate.views.SettingsActivity;

import java.util.List;

import retrofit2.Response;

import static androidx.core.content.ContextCompat.startActivity;

public class LoginPresenter implements LoginContract.Presenter {

    static LoginContract.View view;
    LoginContract.Model model;

    public LoginPresenter(LoginContract.View v){
        view = v;
        model = new LoginModel();

        view.setupUI();

    }

    public static void setTokenChangeView(String token){
        Globals.token = token;
        Log.i("!!HERE!!YES!!", Globals.token);
        view.goToReadings();
    }

    @Override
    public void postNewLogin(String username, String password, String token) {
        model.postNewLogin(username, password, token);
        view.showMessage("Login.");
    }



}
