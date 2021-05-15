package com.study.liking.model_view_controller.list_super_hero;

import android.content.Intent;

import com.study.liking.api.responses.character_data_wrapper.response.CharacterDataContainerResponse;
import com.study.liking.listeners.OnAddSuperHeroListListener;
import com.study.liking.listeners.OnTextChangeListener;
import com.study.liking.models.SuperHero;

import java.util.HashMap;

public interface ListSuperHeroContract {

    interface View {
        void initRecyclerView();
        void initSearchComponent();
        void fitUI(Intent intent);
        void updateRecyclerViewAtUiThread(CharacterDataContainerResponse response, HashMap<Long, SuperHero> heroes);
        void loadingIndicator(boolean state);
        void loadError();
        void showToast(String message);
    }

    interface Presenter extends OnTextChangeListener, OnAddSuperHeroListListener {
        void init();
        void updateRecyclerView();
        void filterData(String text);
        Intent getIntent();
    }
}
