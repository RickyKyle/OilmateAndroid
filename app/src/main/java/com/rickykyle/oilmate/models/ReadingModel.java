package com.rickykyle.oilmate.models;

import android.util.Log;

import com.rickykyle.oilmate.contracts.CurrentOilContract;
import com.rickykyle.oilmate.entities.Reading;
import com.rickykyle.oilmate.entities.ReadingResponse;
import com.rickykyle.oilmate.network.OilmateApi;
import com.rickykyle.oilmate.network.ServiceCreator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadingModel implements CurrentOilContract.Model {

    @Override
    public void getOilReadings(final CurrentOilContract.APIListener listener) {

        try {

            OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);
            Call<List<Reading>> call = oilmateApi.getOilReadings();

            call.enqueue(new Callback<List<Reading>>() {
                @Override
                public void onResponse(Call<List<Reading>> call, Response<List<Reading>> response) {
                    Log.e("Response Code!", "onResponse: " + response.code());
                    if (response.isSuccessful()) {
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
