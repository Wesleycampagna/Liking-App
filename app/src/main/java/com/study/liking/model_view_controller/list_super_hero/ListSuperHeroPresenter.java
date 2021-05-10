package com.study.liking.model_view_controller.list_super_hero;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.study.liking.APIHelper;
import com.study.liking.api.requests.CharacterDataWrapperRequest;
import com.study.liking.api.responses.CharacterDataWrapperResponse;
import com.study.liking.env.Environment;
import com.study.liking.utils.FileUtils;

public class ListSuperHeroPresenter implements ListSuperHeroContract.Presenter {

    private Context context;
    private ListSuperHeroContract.View view;
    private Intent intent;

    public ListSuperHeroPresenter(ListSuperHeroContract.View view, Context context, Intent intent) {
        this.context = context;
        this.view = view;
        this.intent = intent;
    }

    @Override
    public void init() {
        this.view.fitUI(intent);
        this.view.initRecyclerView();
        updateRecyclerView();
    }

    @Override
    public void updateRecyclerView() {
        new Thread(() -> {
            CharacterDataWrapperRequest request = new CharacterDataWrapperRequest.Builder()
                    .setLimit(20)
                    .setOffset(0)
                    .build();

//            try {
//                CharacterDataWrapperResponse response = APIHelper.getCharacters(request);
//                Log.d("API", response.toString());
//                FileUtils.writeFile(Environment.getMarvelBaseUrl() + "/v1/public/characters", new Gson().toJson(response));
//            }
//            catch (Exception e) {
//                Log.d("API", e.getMessage());
//            }
        }).start();
    }

    @Override
    public void filterData(String text) {

    }
}
