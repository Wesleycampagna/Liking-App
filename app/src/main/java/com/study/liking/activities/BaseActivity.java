package com.study.liking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.study.liking.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initActions();
    }

    protected abstract void setContentView();

    protected abstract void initActions();
}