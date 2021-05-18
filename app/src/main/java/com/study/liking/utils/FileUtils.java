package com.study.liking.utils;

import android.util.Log;

import com.study.liking.models.Debug;

public class FileUtils {

    public static void writeFile(String url, String content) {
        Log.d("API", "writing...");
        Debug debug = new Debug();
        debug.url = url;
        debug.debug = content;

        debug.save();
    }
}
