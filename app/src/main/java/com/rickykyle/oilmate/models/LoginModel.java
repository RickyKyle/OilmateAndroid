package com.rickykyle.oilmate.models;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.rickykyle.oilmate.contracts.LoginContract;
import com.rickykyle.oilmate.network.OilmateApi;
import com.rickykyle.oilmate.network.ServiceCreator;
import com.rickykyle.oilmate.network.requests.LoginRequestResponse;
import com.rickykyle.oilmate.presenters.LoginPresenter;
import com.rickykyle.oilmate.views.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements LoginContract.Model {

    @Override
    public void postNewLogin(String username, String password, String token) {
        try {
            OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);
            oilmateApi.postLogin(username, password).enqueue(new Callback<LoginRequestResponse>() {
                @Override
                public void onResponse(Call<LoginRequestResponse> call, Response<LoginRequestResponse> response) {
                    if (response.body() == null){
                        LoginPresenter.invalidLogin("Invalid or missing login credentials.");
                    } else {
                        String token = response.body().getToken();
                        int userID = response.body().getUserID();
                        LoginPresenter.setTokenChangeView(token, userID);
                    }
                }

                @Override
                public void onFailure(Call<LoginRequestResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
