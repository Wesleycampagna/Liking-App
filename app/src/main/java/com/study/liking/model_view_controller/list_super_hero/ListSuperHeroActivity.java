package com.study.liking.model_view_controller.list_super_hero;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.adapters.SuperHeroListRecyclerViewAdapter;
import com.study.liking.api.responses.character_data_wrapper.response.CharacterDataContainerResponse;
import com.study.liking.databinding.ActivityListSuperHeroBinding;
import com.study.liking.model_view_controller.super_hero.SuperHeroActivity;
import com.study.liking.models.SuperHero;
import com.study.liking.utils.Constants;
import com.study.liking.utils.TextUtils;

import java.util.HashMap;

public class ListSuperHeroActivity extends BaseActivity implements ListSuperHeroContract.View {

    private ActivityListSuperHeroBinding binding;
    private ListSuperHeroContract.Presenter presenter;
    private SuperHeroListRecyclerViewAdapter adapter;

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
    public void fitUI(Intent intent) {
        binding.labelLogin.setText(getString(R.string.label_info_login_password, intent != null && intent.getStringExtra(Constants.Bundle.USER_LOGIN) != null ? intent.getStringExtra(Constants.Bundle.USER_LOGIN) : ""));
        binding.labelEmail.setText(intent != null && intent.getStringExtra(Constants.Bundle.USER_EMAIL) != null ? intent.getStringExtra(Constants.Bundle.USER_EMAIL) : "");
        binding.labelWelcomeUser.setText(getString(R.string.label_welcome_user, intent != null && intent.getStringExtra(Constants.Bundle.USER_NAME) != null ? intent.getStringExtra(Constants.Bundle.USER_NAME) : ""));
    }

    @Override
    public void updateRecyclerViewAtUiThread(CharacterDataContainerResponse response, HashMap<Long, SuperHero> heroes) {
        runOnUiThread(() -> {
            loadingIndicator(false);
            adapter.setSuperHeroResponse(response, heroes);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void loadingIndicator(boolean state) {
        new Handler(getMainLooper()).post(() -> {
            if (state) {
                binding.recyclerNestedScroll.setVisibility(View.GONE);
                binding.loadingComponent.loading.setVisibility(View.VISIBLE);
            } else {
                binding.recyclerNestedScroll.setVisibility(View.VISIBLE);
                binding.loadingComponent.loading.setVisibility(View.GONE);
            }
            binding.containerErrorRequest.setVisibility(View.GONE);
        });
    }

    @Override
    public void loadError() {
        new Handler(getMainLooper()).post(() -> {
            binding.recyclerNestedScroll.setVisibility(View.GONE);
            binding.loadingComponent.loading.setVisibility(View.GONE);
            binding.containerErrorRequest.setVisibility(View.VISIBLE);

        });
    }

    @Override
    protected void initActions() {
        binding.btnGoBack.setOnClickListener(v -> goBack());
        binding.proceedToRegistering.setOnClickListener(v -> goToPersonalListSuperHero());
    }

    private void goBack() {
        hideKeyboard(this);
        finish();
    }

    private void goToPersonalListSuperHero() {
        hideKeyboard(this);
        Intent intent = new Intent(this, SuperHeroActivity.class);
        Intent oldIntent = presenter.getIntent();
        intent.putExtra(Constants.Bundle.USER_NAME, oldIntent.getStringExtra(Constants.Bundle.USER_NAME));
        startActivity(intent);
    }

    @Override
    public void initRecyclerView() {
        adapter = new SuperHeroListRecyclerViewAdapter(this, presenter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.setAdapter(adapter);
        RecyclerView recyclerView = binding.recycler;
    }

    @Override
    public void initSearchComponent() {
        binding.searchSuperHero.addTextChangedListener(TextUtils.searchTextWatcherListener(binding.searchSuperHero, presenter));
    }
}