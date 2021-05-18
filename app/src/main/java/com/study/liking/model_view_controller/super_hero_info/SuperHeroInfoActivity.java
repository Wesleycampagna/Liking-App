package com.study.liking.model_view_controller.super_hero_info;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.databinding.ActivitySuperHeroInfoBinding;
import com.study.liking.models.SuperHero;
import com.study.liking.utils.Constants;

public class SuperHeroInfoActivity extends BaseActivity implements  SuperHeroInfoContract.View {

    private ActivitySuperHeroInfoBinding binding;
    private SuperHeroInfoContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SuperHeroInfoPresenter(this, this, getIntent());
        presenter.init();
    }

    @Override
    protected void setContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_super_hero_info);
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
    public void fitUI(SuperHero superHero) {
        runOnUiThread(() -> {
            if (superHero != null) {
                binding.labelSuperHeroName.setText(superHero.name);
                binding.superHeroDescription.setText(superHero.description);
                binding.labelListSuperHeroes.setText(superHero.api ? "" : getString(R.string.label_list_super_heroes));
                updateImage(superHero);
            }
            else showToastMessage("Houve um erro durante a renderização da tela 2!");
        });
    }

    @Override
    public void closeActivity() {
        this.finish();
    }

    @Override
    public void showToastMessage(String message) {
        showToast(message);
    }

    private void updateImage(SuperHero superHero) {
        try {
            Glide.with(this)
                    .load(superHero.urlImage + Constants.IMAGE_PATH + superHero.imageExtension)
                    .centerCrop()
                    .into(binding.superHeroAvatar);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}