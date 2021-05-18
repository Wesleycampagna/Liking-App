package com.study.liking.model_view_controller.super_hero;

import android.content.Context;
import android.content.Intent;

import com.study.liking.models.SuperHero;
import com.study.liking.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class SuperHeroPresenter implements SuperHeroContract.Presenter {

    private Context context;
    private SuperHeroContract.View view;
    private Intent intent;
    private int offsetList;
    List<SuperHero> superHeroesAtAdapter;

    public SuperHeroPresenter(Context context, SuperHeroContract.View view, Intent intent) {
        this.context = context;
        this.view = view;
        this.intent = intent;
        this.offsetList = 0;
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
        List<SuperHero> superHeroes = SuperHero.findAllByOffsetAndQuantity(offsetList, Constants.QUANTITY_ITEM_LIST);
        superHeroesAtAdapter = superHeroes;
        updateRecyclerViewAtUiThread(superHeroes);
    }

    @Override
    public void filterData(String text) {
        List<SuperHero> superHeroes = SuperHero.filterSuperHeroesBy(text);
        superHeroesAtAdapter = superHeroes;
        updateRecyclerViewAtUiThread(superHeroes);
    }

    @Override
    public void onBottomFinallyReached() {
        List<SuperHero> superHeroes = SuperHero.findAllByOffsetAndQuantity(offsetList, Constants.QUANTITY_ITEM_LIST);
        ++offsetList;
        superHeroesAtAdapter.addAll(superHeroes);
        if (offsetList <= 2) {
            updateRecyclerViewAtUiThread(superHeroesAtAdapter);
        }
        else view.loadingIndicator(false);
    }

    @Override
    public int getListSize() {
        return superHeroesAtAdapter.size();
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
