package com.rickykyle.oilmate.presenters;

import com.rickykyle.oilmate.contracts.ChangeTankDimensionsContract;
import com.rickykyle.oilmate.models.ChangeTankDimensionsModel;
import com.rickykyle.oilmate.network.responses.GetCurrentTankDimensionsResponse;

import java.util.List;

import retrofit2.Response;

public class ChangeTankDimensionsPresenter implements ChangeTankDimensionsContract.Presenter, ChangeTankDimensionsContract.GetCurrentTankDimensionsListener {

    ChangeTankDimensionsContract.View view;
    ChangeTankDimensionsContract.Model model;

    public ChangeTankDimensionsPresenter(ChangeTankDimensionsContract.View v){
        view = v;
        model = new ChangeTankDimensionsModel();

        view.setupUI();
        getCurrentTankDimensions();
    }

    @Override
    public void getCurrentTankDimensions() {
        model.getCurrentTankDimensions(this);
    }

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

    @Override
    public void onSuccess(Response<List<GetCurrentTankDimensionsResponse>> response) {
        view.displayCurrentTankDimensions(response.body());
    }

    @Override
    public void onError(Response<List<GetCurrentTankDimensionsResponse>> response) {
        view.showMessage("Error.");
    }

    @Override
    public void onFailure(Throwable t) {
        view.showMessage(t.getMessage());
    }
}
