package com.study.liking.model_view_controller.main;

import android.content.Intent;

public interface MainContract {

    interface View {

        void showToast();
    }

    interface Presenter {

        void saveData(Intent data);
    }
}
