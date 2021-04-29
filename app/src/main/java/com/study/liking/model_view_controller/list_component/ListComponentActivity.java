package com.study.liking.model_view_controller.list_component;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.study.liking.R;
import com.study.liking.activities.BaseActivity;
import com.study.liking.databinding.ActivityListComponentBinding;

public class ListComponentActivity extends BaseActivity {

    private ActivityListComponentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_component);
    }

    @Override
    protected void initActions() {

    }
}