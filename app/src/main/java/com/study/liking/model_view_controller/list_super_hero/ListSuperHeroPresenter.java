package com.study.liking.model_view_controller.list_super_hero;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.study.liking.APIHelper;
import com.study.liking.api.requests.CharacterDataWrapperRequest;
import com.study.liking.api.responses.CharacterDataWrapperResponse;

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

            try {
                CharacterDataWrapperResponse response = APIHelper.getCharacters(request);
                Log.d("API", response.toString());
            }
            catch (Exception e) {
                Log.d("API", e.getMessage());
            }
        }).start();
    }

    @Override
    public void filterData(String text) {

    }
}
