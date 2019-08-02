package com.rickykyle.oilmate.models;

import com.rickykyle.oilmate.contracts.LoginContract;
import com.rickykyle.oilmate.network.OilmateApi;
import com.rickykyle.oilmate.network.ServiceCreator;
import com.rickykyle.oilmate.network.requests.LoginRequestResponse;
import com.rickykyle.oilmate.presenters.LoginPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * This class handles the network requests necessary for logging the user in.
 */
public class LoginModel implements LoginContract.Model {

    @Override
    public void postNewLogin(String username, String password, String token) {
        try {
            // Create Retrofit instance.
            OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);

            // Pass data and make network call.
            oilmateApi.postLogin(username, password).enqueue(new Callback<LoginRequestResponse>() {
                @Override
                public void onResponse(Call<LoginRequestResponse> call, Response<LoginRequestResponse> response) {
                    // If the response is empty, there is no user so pass an error message to the
                    // presenter.
                    if (response.body() == null){
                        LoginPresenter.invalidLogin("Invalid or missing login credentials.");
                    } else {
                        // If the response isn't empty, strip the response of the data necessary
                        // for creating and maintaining a session.
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
