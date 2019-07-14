package com.rickykyle.oilmate.contracts;

import com.rickykyle.oilmate.entities.Reading;

import java.util.List;

import retrofit2.Response;

public interface CurrentOilContract {

    interface Model {
        void getOilReadings(final APIListener listener);
    }

    interface View {

        void setupUI();

        void displayReadingData(List<Reading> readingList);
        void showMessage(String msg);

        void showProgressDialog();
        void hideProgressDialog();
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
