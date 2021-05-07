package com.study.liking.model_view_controller.list_super_hero;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.databinding.ActivityListSuperHeroBinding;

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

    }
}