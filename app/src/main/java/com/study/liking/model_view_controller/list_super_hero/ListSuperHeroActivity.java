package com.study.liking.model_view_controller.list_super_hero;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.adapters.PeopleRecyclerViewAdapter;
import com.study.liking.adapters.SuperHeroRecyclerViewAdapter;
import com.study.liking.databinding.ActivityListSuperHeroBinding;
import com.study.liking.model_view_controller.registry_user.RegistryUserActivity;
import com.study.liking.model_view_controller.registry_user.RegistryUserPresenter;
import com.study.liking.model_view_controller.super_hero.SuperHeroActivity;

public class ListSuperHeroActivity extends BaseActivity implements ListSuperHeroContract.View {

    private ActivityListSuperHeroBinding binding;
    private ListSuperHeroContract.Presenter presenter;
    private SuperHeroRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListSuperHeroPresenter(this, this, getIntent());
        presenter.init();
    }

    @Override
    protected void setContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_super_hero);
    }

    @Override
    protected void initActions() {
        binding.btnGoBack.setOnClickListener(v -> goBack());
        binding.proceedToRegistering.setOnClickListener(v -> goToPersonalListSuperHero());
    }

    private void goBack() {
        finish();
    }

    private void goToPersonalListSuperHero() {
        Intent intent = new Intent(this, SuperHeroActivity.class);
        startActivity(intent);
    }

    @Override
    public void initRecyclerView() {
        adapter = new SuperHeroRecyclerViewAdapter(this);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.setAdapter(adapter);
        RecyclerView recyclerView = binding.recycler;
    }

    @Override
    public void initSearchComponent() {

    }
}