package com.study.liking.model_view_controller.main;

import android.content.Intent;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void saveData(Intent data) {
        // save in sprinkles
        this.view.showToast();
    }
}
