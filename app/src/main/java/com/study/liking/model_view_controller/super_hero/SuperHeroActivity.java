package com.study.liking.model_view_controller.super_hero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.study.liking.R;
import com.study.liking.databinding.ActivitySuperHeroBinding;

public class SuperHeroActivity extends AppCompatActivity {

    private ActivitySuperHeroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_hero);
    }
}