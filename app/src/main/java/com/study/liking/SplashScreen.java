package com.study.liking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.study.liking.databinding.SplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreenBinding binding = DataBindingUtil.setContentView(this, R.layout.splash_screen);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        new Handler(Looper.myLooper()).postDelayed(this::goToMainActivity, 2000);
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}