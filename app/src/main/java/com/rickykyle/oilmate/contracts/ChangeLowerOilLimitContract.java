package com.rickykyle.oilmate.contracts;

import com.rickykyle.oilmate.network.responses.GetCurrentOilLimitResponse;

import java.util.List;

import retrofit2.Response;

/*
 * This interface is the contract between the model-view-presenter for the ChangeLowerOilLimit
 * Activity in the Settings menu.
 */
public interface ChangeLowerOilLimitContract {

    interface Model {
        void getCurrentLowerOilLimit(final ChangeLowerOilLimitContract.GetCurrentOilLimitListener listener);
        void putNewLowerOilLimit(int userID, int newLimit);
    }

    interface View {
        void setupUI();
        void displayCurrentLowerOilLimit(List<GetCurrentOilLimitResponse> currentOilLimit);
        void showMessage(String msg);
        void updateCurrentOilLowerLimitDisplay(int updatedOilLimit);
    }

    interface Presenter {
        void getCurrentLowerOilLimit();
        void putNewLimit(int userID, int newLimit);
    }

    interface GetCurrentOilLimitListener{
        void onSuccess(Response<List<GetCurrentOilLimitResponse>> response);
        void onError(Response<List<GetCurrentOilLimitResponse>> response);
        void onFailure(Throwable t);
    }
}
