package com.rickykyle.oilmate.contracts;

import com.rickykyle.oilmate.network.responses.GetCurrentTankDimensionsResponse;

import java.util.List;

import retrofit2.Response;

public interface ChangeTankDimensionsContract {

    interface Model {
        void getCurrentTankDimensions(final ChangeTankDimensionsContract.GetCurrentTankDimensionsListener listener);
        void putNewTankDimensions(int userID, double diameter, double length);
    }

    interface View {
        void setupUI();
        void displayCurrentTankDimensions(List<GetCurrentTankDimensionsResponse> currentTankDimensions);
        void showMessage(String msg);
        void updateCurrentTankDimensionsDisplay(double diameter, double length);
    }

    interface Presenter {
        void getCurrentTankDimensions();
        void putNewTankDimensions(int userID, double diameter, double length);
    }

    interface GetCurrentTankDimensionsListener{
        void onSuccess(Response<List<GetCurrentTankDimensionsResponse>> response);
        void onError(Response<List<GetCurrentTankDimensionsResponse>> response);
        void onFailure(Throwable t);
    }
}
