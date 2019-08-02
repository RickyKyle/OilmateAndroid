package com.rickykyle.oilmate.presenters;

import com.rickykyle.oilmate.contracts.CurrentOilContract;
import com.rickykyle.oilmate.network.responses.Reading;
import com.rickykyle.oilmate.models.ReadingModel;

import java.util.List;

import retrofit2.Response;

/*
 * This class gets the reading data from the model and passes it to the view.
 */
public class ReadingPresenter implements CurrentOilContract.Presenter, CurrentOilContract.APIListener {

    CurrentOilContract.View view;
    CurrentOilContract.Model model;

    public ReadingPresenter(CurrentOilContract.View v){
        view = v;
        model = new ReadingModel();

        view.setupUI();
        getOilReadings();
    }

    /*
     * Pass listener to the model.
     */
    @Override
    public void getOilReadings() {
        model.getOilReadings(this);
    }

    /*
     * Update the view with the readings.
     */
    @Override
    public void onSuccess(Response<List<Reading>>response) {
        view.displayReadingData(response.body());
    }

    /*
     * Display error message.
     */
    @Override
    public void onError(Response<List<Reading>> response) {
        view.showMessage("Error.");
    }

    /*
     * Display network failure message.
     */
    @Override
    public void onFailure(Throwable t) {
        view.showMessage(t.getMessage());
    }
}
