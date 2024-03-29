package com.rickykyle.oilmate.contracts;

import com.rickykyle.oilmate.network.responses.Reading;

import java.util.List;

import retrofit2.Response;

/*
 * This interface is the contract between the models-views-presenters of the CurrentOil
 * Activity (the home screen).
 */
public interface CurrentOilContract {

    interface Model {
        void getOilReadings(final APIListener listener);
    }

    interface View {

        void setupUI();
        void displayReadingData(List<Reading> readingList);
        void showMessage(String msg);
    }

    interface Presenter {

        void getOilReadings();
    }

    interface APIListener{

        void onSuccess(Response<List<Reading>> response);
        void onError(Response<List<Reading>> response);
        void onFailure(Throwable t);
    }
}
