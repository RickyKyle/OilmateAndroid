package com.rickykyle.oilmate.contracts;

import com.rickykyle.oilmate.network.responses.GetCurrentTargetTemperatureResponse;

import java.util.List;

import retrofit2.Response;

public interface SetTargetTemperatureContract {

    interface Model {
    void getCurrentTargetTemperature(final SetTargetTemperatureContract.GetCurrentTargetTemperatureListener listener);
    void putNewTargetTemperature(int userID, int targetTemperature);
    }

interface View {
    void setupUI();
    void displayCurrentTargetTemperature(List<GetCurrentTargetTemperatureResponse> currentTargetTemperature);
    void showMessage(String msg);
    void updateCurrentTargetTemperatureDisplay(int targetTemperature);
}

interface Presenter {
    void getCurrentTargetTemperature();
    void putNewTargetTemperature(int userID, int targetTemperature);
}

interface GetCurrentTargetTemperatureListener{
    void onSuccess(Response<List<GetCurrentTargetTemperatureResponse>> response);
    void onError(Response<List<GetCurrentTargetTemperatureResponse>> response);
    void onFailure(Throwable t);
}

}
