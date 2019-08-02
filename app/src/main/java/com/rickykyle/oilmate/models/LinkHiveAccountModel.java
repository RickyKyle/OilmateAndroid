package com.rickykyle.oilmate.models;

import android.util.Log;

import com.rickykyle.oilmate.contracts.LinkHiveAccountContract;
import com.rickykyle.oilmate.network.OilmateApi;
import com.rickykyle.oilmate.network.ServiceCreator;
import com.rickykyle.oilmate.network.requests.PostNewHiveAccountRequest;
import com.rickykyle.oilmate.network.responses.GetCurrentHiveAccountResponse;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * This class handles the network calls for linking a Hive account.
 */
public class LinkHiveAccountModel implements LinkHiveAccountContract.Model {

    /*
     * Checks if the user has a hive account already.
     */
    @Override
    public void getCurrentHiveAccount(final LinkHiveAccountContract.GetCurrentHiveAccountListener listener) {
        try {
            // Create Retrofit instance.
            OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);

            // Select HTTP request from the oilmateApi interface and pass the needed data.
            oilmateApi.getCurrentHiveAccount(Globals.token, Globals.userID).enqueue(new Callback<List<GetCurrentHiveAccountResponse>>() {
                @Override
                public void onResponse(Call<List<GetCurrentHiveAccountResponse>> call, Response<List<GetCurrentHiveAccountResponse>> response) {
                    // Pass response to listener.
                    listener.onSuccess(response);
                    Log.i("API RESPONSE", "onResponse: " + response.code());
                }

                @Override
                public void onFailure(Call<List<GetCurrentHiveAccountResponse>> call, Throwable t) {
                    listener.onFailure(t);
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Handles POSTing a new Hive account to the API.
     */
    @Override
    public void postNewHiveAccount(int userID, String hiveUsername, String hivePassword, int setTemperature) {
        try {
            OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);
            oilmateApi.postNewHiveAccount(Globals.token, userID, hiveUsername, hivePassword, setTemperature).enqueue(new Callback<PostNewHiveAccountRequest>() {
                @Override
                public void onResponse(Call<PostNewHiveAccountRequest> call, Response<PostNewHiveAccountRequest> response) {
                    Log.i("API", "Put Success!");
                }

                @Override
                public void onFailure(Call<PostNewHiveAccountRequest> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
