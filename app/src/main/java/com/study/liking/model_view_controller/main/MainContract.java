package com.study.liking.model_view_controller.main;

import android.content.Intent;

import com.study.liking.models.OwnUser;

public interface MainContract {

    interface View {
        void showToast(String message);
        void loadInterface(OwnUser ownUser);
        void setEmail(String email);
    }

    interface Presenter {
        void init();
        void saveData(Intent data);
        boolean isValidData(OwnUser ownUser);
    }
}
