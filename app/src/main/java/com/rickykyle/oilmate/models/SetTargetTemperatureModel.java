package com.rickykyle.oilmate.models;

import android.util.Log;

import com.rickykyle.oilmate.contracts.SetTargetTemperatureContract;
import com.rickykyle.oilmate.network.OilmateApi;
import com.rickykyle.oilmate.network.ServiceCreator;
import com.rickykyle.oilmate.network.requests.PutNewOilLimitRequest;
import com.rickykyle.oilmate.network.requests.PutNewTargetTemperatureRequest;
import com.rickykyle.oilmate.network.responses.GetCurrentOilLimitResponse;
import com.rickykyle.oilmate.network.responses.GetCurrentTargetTemperatureResponse;
import com.rickykyle.oilmate.presenters.SetTargetTemperaturePresenter;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetTargetTemperatureModel implements SetTargetTemperatureContract.Model {

    @Override
    public void getCurrentTargetTemperature(final SetTargetTemperatureContract.GetCurrentTargetTemperatureListener listener) {

        try {
            OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);
            oilmateApi.getTargetTemperature(Globals.token, Globals.userID).enqueue(new Callback<List<GetCurrentTargetTemperatureResponse>>() {
                @Override
                public void onResponse(Call<List<GetCurrentTargetTemperatureResponse>> call, Response<List<GetCurrentTargetTemperatureResponse>> response) {
                        listener.onSuccess(response);
                }

                @Override
                public void onFailure(Call<List<GetCurrentTargetTemperatureResponse>> call, Throwable t) {
                    listener.onFailure(t);
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void putNewTargetTemperature(int userID, int targetTemperature) {
        try {
            OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);
            oilmateApi.putNewTargetTemperature(Globals.token, userID, targetTemperature).enqueue(new Callback<PutNewTargetTemperatureRequest> () {

                public void onResponse(Call<PutNewTargetTemperatureRequest> call, Response<PutNewTargetTemperatureRequest> response) {
                    Log.i("API", "Put Success!");
                }

                @Override
                public void onFailure(Call<PutNewTargetTemperatureRequest> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
