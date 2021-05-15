package com.study.liking.model_view_controller.super_hero;

import android.content.Context;
import android.content.Intent;

import com.study.liking.R;
import com.study.liking.models.SuperHero;

import java.util.ArrayList;
import java.util.List;

public class SuperHeroPresenter implements SuperHeroContract.Presenter {

    private Context context;
    private SuperHeroContract.View view;
    private Intent intent;

    public SuperHeroPresenter(Context context, SuperHeroContract.View view, Intent intent) {
        this.context = context;
        this.view = view;
        this.intent = intent;
    }

    @Override
    public void init() {
        this.view.fitUI(intent);
        this.view.initRecyclerView();
        this.view.initSearchComponent();
        updateRecyclerView();
    }

    @Override
    public void updateRecyclerView() {
        List<SuperHero> superHeroes = SuperHero.findAll();
        updateRecyclerViewAtUiThread(superHeroes);
    }

    @Override
    public void filterData(String text) {
        List<SuperHero> superHeroes = SuperHero.filterSuperHeroesBy(text);
        updateRecyclerViewAtUiThread(superHeroes);
    }

    private void updateRecyclerViewAtUiThread(List<SuperHero> superHeroes) {
        new Thread(() -> {
            // update recyclerView
            if (superHeroes.size() > 0 && superHeroes != null) {
                view.updateRecyclerViewAtUiThread(superHeroes);
            }
            else view.updateRecyclerViewAtUiThread(new ArrayList<>());
        }).start();
    }

    @Override
    public void onEdit(SuperHero superHero) {
        this.view.goToSuperHeroInfo(superHero);
    }
}
