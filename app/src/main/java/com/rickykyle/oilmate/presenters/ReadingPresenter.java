package com.rickykyle.oilmate.presenters;

import com.rickykyle.oilmate.contracts.CurrentOilContract;
import com.rickykyle.oilmate.network.responses.Reading;
import com.rickykyle.oilmate.models.ReadingModel;

import java.util.List;

import retrofit2.Response;

public class ReadingPresenter implements CurrentOilContract.Presenter, CurrentOilContract.APIListener {

    CurrentOilContract.View view;
    CurrentOilContract.Model model;

    public ReadingPresenter(CurrentOilContract.View v){
        view = v;
        model = new ReadingModel();

        view.setupUI();
        getOilReadings();
    }

    @Override
    public void getOilReadings() {
        model.getOilReadings(this);
    }

    @Override
    public void onSuccess(Response<List<Reading>>response) {
        view.displayReadingData(response.body());
    }

    @Override
    public void onError(Response<List<Reading>> response) {
        view.showMessage("Error.");
    }

    @Override
    public void onFailure(Throwable t) {
        view.showMessage(t.getMessage());
    }
}
