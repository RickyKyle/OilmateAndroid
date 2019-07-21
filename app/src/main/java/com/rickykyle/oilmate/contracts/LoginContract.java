package com.rickykyle.oilmate.contracts;

public interface LoginContract {
    interface Model {
        void postNewLogin (String username, String password, String token);
    }

    interface View {
        void setupUI();
        void showMessage(String msg);
        void goToReadings();
    }

    interface Presenter {
        void postNewLogin(String username, String password, String token);
    }

}
