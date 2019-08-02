package com.rickykyle.oilmate.presenters;

import com.rickykyle.oilmate.contracts.ChangeTankDimensionsContract;
import com.rickykyle.oilmate.models.ChangeTankDimensionsModel;
import com.rickykyle.oilmate.network.responses.GetCurrentTankDimensionsResponse;

import java.util.List;

import retrofit2.Response;

/*
 * Passes the data needed for the ChangeTankDimensions activity between the view and model,
 * and performs validation.
 */
public class ChangeTankDimensionsPresenter implements ChangeTankDimensionsContract.Presenter, ChangeTankDimensionsContract.GetCurrentTankDimensionsListener {

    ChangeTankDimensionsContract.View view;
    ChangeTankDimensionsContract.Model model;

    public ChangeTankDimensionsPresenter(ChangeTankDimensionsContract.View v){
        view = v;
        model = new ChangeTankDimensionsModel();

        view.setupUI();
        getCurrentTankDimensions();
    }

    /*
     * Pass listener to the model.
     */
    @Override
    public void getCurrentTankDimensions() {
        model.getCurrentTankDimensions(this);
    }

    /*
     * Validate and send the new dimensions to the model.
     */
    @Override
    public void putNewTankDimensions(int userID, double diameter, double length) {
       if(diameter <= 0 || length <= 0){
           view.showMessage("Tank dimensions must be greater than 0");
       } else if (diameter >= 50000 || length >= 50000){
           view.showMessage("Tank dimensions must be less than or equal to 50000cm");
       } else {
           model.putNewTankDimensions(userID, diameter, length);
           view.showMessage("Successfully changed tank dimensions.");
           view.updateCurrentTankDimensionsDisplay(diameter, length);
       }
    }

    /*
     * Update display on successful network call.
     */
    @Override
    public void onSuccess(Response<List<GetCurrentTankDimensionsResponse>> response) {
        view.displayCurrentTankDimensions(response.body());
    }

    /*
     * Display an error on an erroneous call.
     */
    @Override
    public void onError(Response<List<GetCurrentTankDimensionsResponse>> response) {
        view.showMessage("Error.");
    }

    /*
     * On failure.
     */
    @Override
    public void onFailure(Throwable t) {
        view.showMessage(t.getMessage());
    }
}
