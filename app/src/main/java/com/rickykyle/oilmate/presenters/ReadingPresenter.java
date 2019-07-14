package com.rickykyle.oilmate.presenters;

import com.rickykyle.oilmate.contracts.CurrentOilContract;
import com.rickykyle.oilmate.entities.Reading;
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

        view.showProgressDialog();
        model.getOilReadings(this);
    }

    @Override
    public void onSuccess(Response<List<Reading>>response) {
        view.hideProgressDialog();
        view.displayReadingData(response.body());

    }

    @Override
    public void onError(Response<List<Reading>> response) {
        view.hideProgressDialog();
        view.showMessage("Error.");
    }

    @Override
    public void onFailure(Throwable t) {
        view.hideProgressDialog();
        view.showMessage(t.getMessage());

    }
}
