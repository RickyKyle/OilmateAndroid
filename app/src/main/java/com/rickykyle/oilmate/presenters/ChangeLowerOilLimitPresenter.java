package com.rickykyle.oilmate.presenters;

import com.rickykyle.oilmate.contracts.ChangeLowerOilLimitContract;
import com.rickykyle.oilmate.models.ChangeLowerOilLimitModel;
import com.rickykyle.oilmate.network.responses.GetCurrentOilLimitResponse;
import com.rickykyle.oilmate.utilities.Constants;

import java.util.List;

import retrofit2.Response;

/*
 * This class pulls the data from the ChangeLowerOilLimitModel and takes the data
 * from the view to give to the model.  It also contains validation for the data.
 */
public class ChangeLowerOilLimitPresenter implements ChangeLowerOilLimitContract.Presenter, ChangeLowerOilLimitContract.GetCurrentOilLimitListener {

    ChangeLowerOilLimitContract.View view;
    ChangeLowerOilLimitContract.Model model;

    public ChangeLowerOilLimitPresenter(ChangeLowerOilLimitContract.View v){
        view = v;
        model = new ChangeLowerOilLimitModel();

        view.setupUI();
        getCurrentLowerOilLimit();
    }

    /*
     * Pass an instance of this class, including the listener, to the getLowerOilLimit method in
     * the model.
     */
    @Override
    public void getCurrentLowerOilLimit() {
        model.getCurrentLowerOilLimit(this);
    }

    /*
     * Is passed the data from the view to send to the model but
     * performs validation before.
     */
    @Override
    public void putNewLimit(int userID, int newLimit) {
        if(newLimit > Constants.MAX_TANK){
            view.showMessage("Lower limit must be 1000 litres or less.");
        } else if (newLimit <= 0) {
            view.showMessage("Lower limit cannot be equal to or less than 0");
        } else {
            model.putNewLowerOilLimit(userID, newLimit);
            view.showMessage("Successfully changed lower limit.");
            view.updateCurrentOilLowerLimitDisplay(newLimit);
        }
    }

    /*
     * On a successful response from the model, passes the current oil limit to
     * the view.
     */
    @Override
    public void onSuccess(Response<List<GetCurrentOilLimitResponse>> response) {
        view.displayCurrentLowerOilLimit(response.body());
    }

    /*
     * Displays an error message if the response is erroneous.
     */
    @Override
    public void onError(Response<List<GetCurrentOilLimitResponse>> response) {
        view.showMessage("Error");
    }

    /*
     * On failed network call.
     */
    @Override
    public void onFailure(Throwable t) {
        view.showMessage(t.getMessage());
    }


}
