package com.rickykyle.oilmate.models;

import android.provider.Settings;
import android.util.Log;

import com.rickykyle.oilmate.contracts.CurrentOilContract;
import com.rickykyle.oilmate.network.responses.Reading;
import com.rickykyle.oilmate.network.OilmateApi;
import com.rickykyle.oilmate.network.ServiceCreator;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * This class handles the network calls necessary for gathering all of the readings taken
 * by the user' sdevice.
 */
public class ReadingModel implements CurrentOilContract.Model {

    @Override
    public void getOilReadings(final CurrentOilContract.APIListener listener) {

        try {
            // Create Retrofit object.
            OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);

            // Select the getOilReadings GET method from the API interface.
            Call<List<Reading>> call = oilmateApi.getOilReadings(Globals.token, Globals.userID);

            call.enqueue(new Callback<List<Reading>>() {
                @Override
                public void onResponse(Call<List<Reading>> call, Response<List<Reading>> response) {
                    Log.e("Response Code!", "onResponse: " + response.code());
                    if (response.isSuccessful()) {
                        // Pass array of readings to the listener.
                        listener.onSuccess(response);
                    } else {
                        listener.onError(response);
                    }
                }

                @Override
                public void onFailure(Call<List<Reading>> call, Throwable t) {
                    listener.onFailure(t);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
