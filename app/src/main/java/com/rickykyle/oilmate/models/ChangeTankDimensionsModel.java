package com.rickykyle.oilmate.models;

import android.provider.Settings;
import android.util.Log;

import com.rickykyle.oilmate.contracts.ChangeLowerOilLimitContract;
import com.rickykyle.oilmate.contracts.ChangeTankDimensionsContract;
import com.rickykyle.oilmate.network.OilmateApi;
import com.rickykyle.oilmate.network.ServiceCreator;
import com.rickykyle.oilmate.network.requests.PutNewOilLimitRequest;
import com.rickykyle.oilmate.network.requests.PutNewTankDimensionsRequest;
import com.rickykyle.oilmate.network.responses.GetCurrentOilLimitResponse;
import com.rickykyle.oilmate.network.responses.GetCurrentTankDimensionsResponse;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeTankDimensionsModel implements ChangeTankDimensionsContract.Model {

    @Override
    public void getCurrentTankDimensions(final ChangeTankDimensionsContract.GetCurrentTankDimensionsListener listener) {
        try {
            OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);
            oilmateApi.getCurrentTankDimensions(Globals.token, Globals.userID).enqueue(new Callback<List<GetCurrentTankDimensionsResponse>>() {
                @Override
                public void onResponse(Call<List<GetCurrentTankDimensionsResponse>> call, Response<List<GetCurrentTankDimensionsResponse>> response) {
                    listener.onSuccess(response);
                }

                @Override
                public void onFailure(Call<List<GetCurrentTankDimensionsResponse>> call, Throwable t) {
                    listener.onFailure(t);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putNewTankDimensions(int userID, double diameter, double length) {
        try {
            OilmateApi oilmateApi = ServiceCreator.createService(OilmateApi.class);
            oilmateApi.putNewTankDimensions(Globals.token, userID, diameter, length).enqueue(new Callback<PutNewTankDimensionsRequest> () {

                public void onResponse(Call<PutNewTankDimensionsRequest> call, Response<PutNewTankDimensionsRequest> response) {
                    Log.i("API", "Put Success!");
                }

                @Override
                public void onFailure(Call<PutNewTankDimensionsRequest> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
