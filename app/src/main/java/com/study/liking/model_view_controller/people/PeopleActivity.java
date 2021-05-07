package com.study.liking.model_view_controller.people;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.DataBindingUtil;

import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.databinding.ActivityPeopleBinding;
import com.study.liking.model_view_controller.registry_user.RegistryUserActivity;

public class PeopleActivity extends BaseActivity {

    private ActivityPeopleBinding binding;
    private Animation fromBottom;
    private Animation toBottom;
    private boolean clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_people);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);
    }

    @Override
    protected void initActions() {
        binding.fabDefault.setOnClickListener(v -> setFabVisible());
        binding.fabAdd.setOnClickListener(v -> goToRegistryPerson());
        binding.btnGoBack.setOnClickListener(v -> goBack());
    }

    private void setFabVisible() {
        if (!clicked) {
            clicked = true;
            binding.fabAdd.setVisibility(View.VISIBLE);
            setFabAnimation(fromBottom);
        }
        else {
            clicked = false;
            binding.fabAdd.setVisibility(View.GONE);
            setFabAnimation(toBottom);
        }
    }
    private void setFabAnimation(Animation animation) {
        binding.fabAdd.startAnimation(animation);
    }

    private void goToRegistryPerson() {
        Intent intent = new Intent(this, RegistryUserActivity.class);
        startActivity(intent);
    }

    private void goBack() {
        finish();
    }
}