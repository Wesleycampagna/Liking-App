package com.study.liking.model_view_controller.super_hero;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.adapters.SuperHeroRecyclerViewAdapter;
import com.study.liking.databinding.ActivitySuperHeroBinding;
import com.study.liking.model_view_controller.super_hero_info.SuperHeroInfoActivity;
import com.study.liking.models.SuperHero;
import com.study.liking.utils.Constants;
import com.study.liking.utils.TextUtils;

import java.util.List;

public class SuperHeroActivity extends BaseActivity implements SuperHeroContract.View {

    private ActivitySuperHeroBinding binding;
    private SuperHeroContract.Presenter presenter;
    private SuperHeroRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SuperHeroPresenter(this, this, getIntent());
        presenter.init();
    }

    @Override
    protected void setContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_super_hero);
    }

    @Override
    protected void initActions() {
        binding.btnGoBack.setOnClickListener(v -> goBack());
    }

    private void goBack() {
        hideKeyboard(this);
        finish();
    }

    @Override
    public void initRecyclerView() {
        adapter = new SuperHeroRecyclerViewAdapter(this, presenter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.setAdapter(adapter);
        RecyclerView recyclerView = binding.recycler;
    }

    @Override
    public void initSearchComponent() {
        binding.searchSuperHero.addTextChangedListener(TextUtils.searchTextWatcherListener(binding.searchSuperHero, presenter));
    }

    @Override
    public void fitUI(Intent intent) {
        binding.labelWelcomeUser.setText(getString(R.string.label_welcome_user, intent != null && intent.getStringExtra(Constants.Bundle.USER_NAME) != null ? intent.getStringExtra(Constants.Bundle.USER_NAME) : ""));
    }

    @Override
    public void updateRecyclerViewAtUiThread(List<SuperHero> heroes) {
        runOnUiThread(() -> {
            loadingIndicator(false);
            adapter.setSuperHeroes(heroes);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void goToSuperHeroInfo(SuperHero superHero) {
        hideKeyboard(this);
        Intent intent = new Intent(this, SuperHeroInfoActivity.class);
        intent.putExtra(Constants.Bundle.SUPER_HERO_ID, superHero.idSuperHero);
        startActivity(intent);
    }

    @Override
    public void loadingIndicator(boolean state) {
        new Handler(getMainLooper()).post(() -> {
            if (state) {
                binding.recyclerContainer.setVisibility(View.GONE);
                binding.loadingComponent.loading.setVisibility(View.VISIBLE);
            } else {
                binding.recyclerContainer.setVisibility(View.VISIBLE);
                binding.loadingComponent.loading.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showToast(String message) {
        super.showToast(message);
    }
}