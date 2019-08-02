package com.rickykyle.oilmate.presenters;

import com.rickykyle.oilmate.contracts.SetTargetTemperatureContract;
import com.rickykyle.oilmate.models.SetTargetTemperatureModel;
import com.rickykyle.oilmate.network.responses.GetCurrentTargetTemperatureResponse;

import java.util.List;

import retrofit2.Response;

/*
 * This class gets the current target temperature from the model and passes it to the view,
 * and takes the new target temperature from the view and passes it to the model.
 */
public class SetTargetTemperaturePresenter implements SetTargetTemperatureContract.Presenter, SetTargetTemperatureContract.GetCurrentTargetTemperatureListener {

    static SetTargetTemperatureContract.View view;
    SetTargetTemperatureContract.Model model;

    public SetTargetTemperaturePresenter(SetTargetTemperatureContract.View v){
        view = v;
        model = new SetTargetTemperatureModel();

        view.setupUI();
        getCurrentTargetTemperature();
    }

    /*
     * Gets the current target temperature by passing the listener to the model.
     */
    @Override
    public void getCurrentTargetTemperature() {
        model.getCurrentTargetTemperature(this);
    }

    /*
     * Validates data sent by the user before passing it to the model.
     */
    @Override
    public void putNewTargetTemperature(int userID, int targetTemperature) {
       if(targetTemperature > 50){
           view.showMessage("Target temperature must not be greater than 50 degrees.");
       } else if (targetTemperature <= 0){
           view.showMessage("Target temperature must be greater than 0 degrees.");
       } else {
           model.putNewTargetTemperature(userID, targetTemperature);
           view.updateCurrentTargetTemperatureDisplay(targetTemperature);
           view.showMessage("Target temperature changed successfully.");
       }
    }

    /*
     * Updates view on successful response.
     */
    @Override
    public void onSuccess(Response<List<GetCurrentTargetTemperatureResponse>> response) {
            view.displayCurrentTargetTemperature(response.body());
    }

    /*
     * Display error message.
     */
    @Override
    public void onError(Response<List<GetCurrentTargetTemperatureResponse>> response) {
        view.showMessage("Error.");
    }

    /*
     * Display failure message.
     */
    @Override
    public void onFailure(Throwable t) {
        view.showMessage(t.getMessage());
    }
}
