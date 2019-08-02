package com.rickykyle.oilmate.presenters;

import android.util.Log;

import com.rickykyle.oilmate.contracts.LinkHiveAccountContract;
import com.rickykyle.oilmate.models.LinkHiveAccountModel;
import com.rickykyle.oilmate.network.responses.GetCurrentHiveAccountResponse;
import com.rickykyle.oilmate.utilities.Globals;

import java.util.List;

import retrofit2.Response;

/*
 * This class is responsible for pulling the data from - and feeding the data to - the model from
 * the view.
 */
public class LinkHiveAccountPresenter implements LinkHiveAccountContract.Presenter, LinkHiveAccountContract.GetCurrentHiveAccountListener {

    LinkHiveAccountContract.View view;
    LinkHiveAccountContract.Model model;

    public LinkHiveAccountPresenter(LinkHiveAccountContract.View v){
        view = v;
        model = new LinkHiveAccountModel();

        view.setupUI();
        checkCurrentHiveAccount();

    }

    /*
     * Passes an instance of the listener to the model.
     */
    @Override
    public void checkCurrentHiveAccount() {
        model.getCurrentHiveAccount(this);
    }

    /*
     * Takes the data from the view and passes it to the model.
     */
    @Override
    public void postNewHiveAccount(String hiveUsername, String hivePassword, int setTemperature) {
        model.postNewHiveAccount(Globals.userID, hiveUsername, hivePassword, setTemperature);
        view.showMessage("New account created successfully.");
        view.updateCurrentHiveAccountDisplay(true);
    }

    /*
     * On a successful response, updates the view.
     */
    @Override
    public void onSuccess(Response<List<GetCurrentHiveAccountResponse>> response) {
        if(response.body().size() != 0){
            view.updateCurrentHiveAccountDisplay(true);
        }
    }

    /*
     * On Error
     */
    @Override
    public void onError(Response<List<GetCurrentHiveAccountResponse>> response) {
        view.showMessage("Error");
    }

    /*
     * On failure
     */
    @Override
    public void onFailure(Throwable t) {
        view.showMessage(t.getMessage());
    }
}
