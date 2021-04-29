package com.study.liking.model_view_controller.registry_user;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.databinding.ActivityRegistryUserBinding;

public class RegistryUserActivity extends BaseActivity {

    private ActivityRegistryUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registry_user);
    }

    @Override
    protected void initActions() {

    }
}