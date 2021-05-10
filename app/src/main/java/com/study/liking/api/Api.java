package com.study.liking.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.study.liking.api.responses.CharacterDataWrapperResponse;
import com.study.liking.env.Environment;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Query;

public class Api {

    public static Api.Routes routes;

    public static Api.Routes getApiMarvel(Context context) {

        if (routes == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("dd/MM/yyyy")
                    .create();

            RestAdapter.Builder builder = new RestAdapter.Builder()
                    .setEndpoint(Environment.getMarvelBaseUrl())
                    .setConverter(new GsonConverter(gson))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(new OkClient())
                    .setLog(message -> Log.d("API", message));

            RestAdapter restAdapter = builder.build();

            routes = restAdapter.create(Api.Routes.class);
        }
        return routes;
    }

    public interface Routes {

//        @GET("/v1/public/characters")
//        CharacterDataWrapperResponse getMarvelCharactersResponse(
//                @Query("ts") String ts,
//                @Query("apikey") String apikey,
//                @Query("hash") String hash,
//                @Query("name") String name,
//                @Query("nameStartsWith") String nameStartsWith,
//                @Query("modifiedSince") Date modifiedSince,
//                @Query("comics") int comics,
//                @Query("series") int series,
//                @Query("events") int events,
//                @Query("stories") int stories,
//                @Query("orderBy") String orderBy,
//                @Query("limit") int limit,
//                @Query("offset") int offset
//        );

        @GET("/v1/public/characters")
        CharacterDataWrapperResponse getMarvelCharactersResponse(
                @Query("ts") String ts,
                @Query("apikey") String apikey,
                @Query("hash") String hash,
                @Query("nameStartsWith") String nameStartsWith,
                @Query("modifiedSince") Date modifiedSince,
                @Query("limit") int limit,
                @Query("offset") int offset
        );
    }
}


