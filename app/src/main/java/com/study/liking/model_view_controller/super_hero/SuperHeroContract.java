package com.study.liking.model_view_controller.super_hero;

import android.content.Intent;

import com.study.liking.listeners.OnEditSuperHeroListener;
import com.study.liking.listeners.OnTextChangeListener;
import com.study.liking.models.SuperHero;

import java.util.List;

public interface SuperHeroContract {

    interface View {
        void initRecyclerView();
        void initSearchComponent();
        void fitUI(Intent intent);
        void updateRecyclerViewAtUiThread(List<SuperHero> heroes);
        void loadingIndicator(boolean state);
        void goToSuperHeroInfo(SuperHero superHero);
        void showToast(String message);
    }

    interface Presenter extends OnEditSuperHeroListener, OnTextChangeListener {
        void init();
        void updateRecyclerView();
        void filterData(String text);
    }
}
