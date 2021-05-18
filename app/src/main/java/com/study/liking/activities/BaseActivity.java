package com.study.liking.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.study.liking.listeners.OnWaitingListener;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initActions();
    }

    public void showToast(String message) {
        new Handler(Looper.myLooper()).post(() -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void onWaitingTime(float timeInSeconds, @NonNull OnWaitingListener listener) {
        new Thread(() -> {
            long end = System.currentTimeMillis() + (int) (timeInSeconds * 1000L);
            boolean isWaitingReached = false;

            while (!isWaitingReached) {
                if (end < System.currentTimeMillis()) {
                    isWaitingReached = true;
                }
            }
            listener.run();
        }).start();
    }

    protected abstract void setContentView();

    protected abstract void initActions();
}