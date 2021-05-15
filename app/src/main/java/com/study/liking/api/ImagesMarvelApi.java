package com.study.liking.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.study.liking.env.Environment;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class ImagesMarvelApi {

    public static ImagesMarvelApi.Routes routes;

    public static ImagesMarvelApi.Routes getApiMarvel(Context context) {

        if (routes == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("dd/MM/yyyy")
                    .create();

            RestAdapter.Builder builder = new RestAdapter.Builder()
                    .setEndpoint(Environment.getMarvelBaseUrl.image)
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .setClient(new OkClient())
                    .setLog(message -> Log.d("API", message));

            RestAdapter restAdapter = builder.build();

            routes = restAdapter.create(ImagesMarvelApi.Routes.class);
        }
        return routes;
    }

    public interface Routes {

    }
}
