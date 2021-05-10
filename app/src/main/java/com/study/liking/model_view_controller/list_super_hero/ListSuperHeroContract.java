package com.study.liking.model_view_controller.list_super_hero;

import android.content.Intent;

import com.study.liking.models.User;

import java.util.List;

public interface ListSuperHeroContract {

    interface View {
        void initRecyclerView();
//        void updateRecyclerView(List<User> users);
        void initSearchComponent();
        void fitUI(Intent intent);
    }

    interface Presenter {
        void init();
        void updateRecyclerView();
        void filterData(String text);
    }
}
