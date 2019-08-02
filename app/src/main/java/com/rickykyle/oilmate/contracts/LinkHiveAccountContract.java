package com.rickykyle.oilmate.contracts;

import com.rickykyle.oilmate.network.responses.GetCurrentHiveAccountResponse;

import java.util.List;

import retrofit2.Response;

/*
 * This interface is the contract for the LinkHiveAccount Activity from the
 * settings screen.
 */
public interface LinkHiveAccountContract {

    interface Model {
        void getCurrentHiveAccount(final LinkHiveAccountContract.GetCurrentHiveAccountListener listener);
        void postNewHiveAccount(int userID, String hiveUsername, String hivePassword, int setTemperature);
    }

    interface View {
        void setupUI();
        void showMessage(String msg);
        void updateCurrentHiveAccountDisplay(Boolean accountExists);
    }

    interface Presenter {
        void checkCurrentHiveAccount();
        void postNewHiveAccount(String hiveUsername, String hivePassword, int setTemperature);
    }

    interface GetCurrentHiveAccountListener {
        void onSuccess(Response<List<GetCurrentHiveAccountResponse>> response);
        void onError(Response<List<GetCurrentHiveAccountResponse>> response);
        void onFailure(Throwable t);
    }
}
