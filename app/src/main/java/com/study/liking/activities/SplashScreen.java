package com.study.liking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.databinding.DataBindingUtil;

import com.study.liking.R;
import com.study.liking.databinding.SplashScreenBinding;
import com.study.liking.model_view_controller.main.MainActivity;

public class SplashScreen extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        SplashScreenBinding binding = DataBindingUtil.setContentView(this, R.layout.splash_screen);
    }

    @Override
    protected void initActions() {}

    @Override
    protected void onPostResume() {
        super.onPostResume();

        new Handler(Looper.myLooper()).postDelayed(this::goToMainActivity, 2000);
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}