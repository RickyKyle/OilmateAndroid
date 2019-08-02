package com.rickykyle.oilmate.presenters;

import android.content.SharedPreferences;
import android.util.Log;

import com.rickykyle.oilmate.contracts.LoginContract;
import com.rickykyle.oilmate.models.LoginModel;
import com.rickykyle.oilmate.utilities.Globals;

/*
 * This class passes the log in data input by the user from the view to the model.
 * If the log in is successful, it returns the token and userID which are necessary
 * to maintain a session.  On an invalid log in, it returns an error message.
 */
public class LoginPresenter implements LoginContract.Presenter {

    static LoginContract.View view;
    LoginContract.Model model;

    public LoginPresenter(LoginContract.View v){
        view = v;
        model = new LoginModel();

        view.setupUI();
    }

    /*
     * On a successful response, assigns the token and userID to their globals and changes the
     * view to the home readings screen.
     */
    public static void setTokenChangeView(String token, int userID){
            Globals.token = token;
            Globals.userID = userID;
            view.goToReadings();
    }

    /*
     * On an invalid log in, presents an error message.
     */
    public static void invalidLogin(String message){
        view.showMessage(message);
    }

    /*
     * Passes the log in data to the model.
     */
    @Override
    public void postNewLogin(String username, String password, String token) {
        model.postNewLogin(username, password, token);
    }
}
