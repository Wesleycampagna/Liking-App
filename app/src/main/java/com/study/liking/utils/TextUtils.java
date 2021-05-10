package com.study.liking.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.study.liking.listeners.OnTextChangeListener;

public class TextUtils {

    public static TextWatcher searchTextWatcherListener(final EditText ediTxt, OnTextChangeListener listener) {
        return new TextWatcher() {
            boolean isUpdating;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                listener.filterData(s.toString());
                isUpdating = true;
                ediTxt.setText(s.toString());
                ediTxt.setSelection(s.length());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }
}
