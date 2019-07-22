package com.rickykyle.oilmate.presenters;

import android.content.SharedPreferences;
import android.util.Log;

import com.rickykyle.oilmate.contracts.LoginContract;
import com.rickykyle.oilmate.models.LoginModel;
import com.rickykyle.oilmate.utilities.Globals;


public class LoginPresenter implements LoginContract.Presenter {

    static LoginContract.View view;
    LoginContract.Model model;

    public LoginPresenter(LoginContract.View v){
        view = v;
        model = new LoginModel();

        view.setupUI();
    }

    public static void setTokenChangeView(String token, int userID){
            Globals.token = token;
            Globals.userID = userID;
            view.goToReadings();
    }

    public static void invalidLogin(String message){
        view.showMessage(message);
    }

    @Override
    public void postNewLogin(String username, String password, String token) {
        model.postNewLogin(username, password, token);
    }
}
