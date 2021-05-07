package com.study.liking.model_view_controller.list_super_hero;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.databinding.ActivityListSuperHeroBinding;
import com.study.liking.model_view_controller.registry_user.RegistryUserActivity;
import com.study.liking.model_view_controller.super_hero.SuperHeroActivity;

public class ListSuperHeroActivity extends BaseActivity {

    private ActivityListSuperHeroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}