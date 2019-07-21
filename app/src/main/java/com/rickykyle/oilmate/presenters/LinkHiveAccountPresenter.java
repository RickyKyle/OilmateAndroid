package com.rickykyle.oilmate.presenters;

import android.util.Log;

import com.rickykyle.oilmate.contracts.LinkHiveAccountContract;
import com.rickykyle.oilmate.models.LinkHiveAccountModel;
import com.rickykyle.oilmate.network.responses.GetCurrentHiveAccountResponse;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.List;

import retrofit2.Response;

public class LinkHiveAccountPresenter implements LinkHiveAccountContract.Presenter, LinkHiveAccountContract.GetCurrentHiveAccountListener {

    LinkHiveAccountContract.View view;
    LinkHiveAccountContract.Model model;

    public LinkHiveAccountPresenter(LinkHiveAccountContract.View v){
        view = v;
        model = new LinkHiveAccountModel();

        view.setupUI();
        checkCurrentHiveAccount();

    }
    @Override
    public void checkCurrentHiveAccount() {
        model.getCurrentHiveAccount(this);
    }

    @Override
    public void postNewHiveAccount(String hiveUsername, String hivePassword, int setTemperature) {
        model.postNewHiveAccount(Globals.userID, hiveUsername, hivePassword, setTemperature);
        view.showMessage("New account created successfully.");
        view.updateCurrentHiveAccountDisplay(true);
    }

    @Override
    public void onSuccess(Response<List<GetCurrentHiveAccountResponse>> response) {
        if(response.body().size() != 0){
            view.updateCurrentHiveAccountDisplay(true);
        }
    }

    @Override
    public void onError(Response<List<GetCurrentHiveAccountResponse>> response) {
    }

    @Override
    public void onFailure(Throwable t) {
        view.showMessage(t.getMessage());
    }
}
