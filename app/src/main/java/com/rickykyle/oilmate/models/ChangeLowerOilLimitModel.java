package com.rickykyle.oilmate.models;

import android.util.Log;

import com.rickykyle.oilmate.contracts.ChangeLowerOilLimitContract;
import com.rickykyle.oilmate.network.requests.PutNewOilLimitRequest;
import com.rickykyle.oilmate.network.responses.GetCurrentOilLimitResponse;
import com.rickykyle.oilmate.network.OilmateApi;
import com.rickykyle.oilmate.network.ServiceCreator;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * This class handles the network calls for changing the user's lower
 * oil limit.
 */
public class ChangeLowerOilLimitModel implements ChangeLowerOilLimitContract.Model {

    /*
     * Get the current lower oil limit from the API.
     */
    @Override
    public void getCurrentLowerOilLimit(final ChangeLowerOilLimitContract.GetCurrentOilLimitListener listener) {

        try {
            // Create Retrofit instance.
            OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);

            // Select and send HTTP request and pass needed data.
            oilmateApi.getOilLowerLimit(Globals.token, Globals.userID).enqueue(new Callback<List<GetCurrentOilLimitResponse>>() {
                @Override
                public void onResponse(Call<List<GetCurrentOilLimitResponse>> call, Response<List<GetCurrentOilLimitResponse>> response) {
                   // Pass response to listener.
                    listener.onSuccess(response);
                }

                @Override
                public void onFailure(Call<List<GetCurrentOilLimitResponse>> call, Throwable t) {
                    // Pass failure to listener.
                    listener.onFailure(t);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /*
     * Change the oil limit via a PUT request.
     */
    @Override
    public void putNewLowerOilLimit(int userID, int newLimit) {

        try {
            OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);
            oilmateApi.putNewOilLimit(Globals.token, userID, newLimit).enqueue(new Callback<PutNewOilLimitRequest> () {

                public void onResponse(Call<PutNewOilLimitRequest> call, Response<PutNewOilLimitRequest> response) {
                    Log.i("API", "Put Success!");
                }

                @Override
                public void onFailure(Call<PutNewOilLimitRequest> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

