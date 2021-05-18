package com.study.liking.model_view_controller.super_hero_info;

import com.study.liking.models.SuperHero;

public interface SuperHeroInfoContract {

    interface View {
        void fitUI(SuperHero superHero);
        void closeActivity();
        void showToastMessage(String message);
    }

    interface Presenter {
        void init();
    }
}
