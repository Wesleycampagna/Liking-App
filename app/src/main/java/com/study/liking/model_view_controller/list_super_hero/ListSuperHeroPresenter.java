package com.study.liking.model_view_controller.list_super_hero;

import android.content.Context;
import android.content.Intent;

import com.study.liking.R;
import com.study.liking.api.requests.CharacterDataWrapperRequest;
import com.study.liking.api.responses.CharacterDataWrapperResponse;
import com.study.liking.api.responses.character_data_wrapper.response.CharacterResponse;
import com.study.liking.models.SuperHero;
import com.study.liking.utils.APIHelper;

import java.util.HashMap;
import java.util.List;

import static com.study.liking.utils.Constants.Api.STATUS_OK;

public class ListSuperHeroPresenter implements ListSuperHeroContract.Presenter {

    private Context context;
    private ListSuperHeroContract.View view;
    private Intent intent;
    private Thread thread;

    public ListSuperHeroPresenter(ListSuperHeroContract.View view, Context context, Intent intent) {
        this.context = context;
        this.view = view;
        this.intent = intent;
    }

    @Override
    public void init() {
        this.view.fitUI(intent);
        this.view.initRecyclerView();
        this.view.initSearchComponent();
        updateRecyclerView();
    }

    @Override
    public void updateRecyclerView() {
        this.view.loadingIndicator(true);
        new Thread(() -> {
            CharacterDataWrapperRequest request = new CharacterDataWrapperRequest.Builder()
                    .setLimit(40)
                    .setOffset(0)
                    .build();

            requestCharacters(request);
        }).start();
    }

    @Override
    public void filterData(String text) {
        final long now = System.currentTimeMillis();

        if (thread != null && thread.isAlive())
            thread.interrupt();

        this.view.loadingIndicator(true);

        thread = new Thread(() -> {
            long end = System.currentTimeMillis() + 500;
            boolean isWaitingReached = false;

            while (!isWaitingReached) {
                if (end < System.currentTimeMillis()) {
                    if (thread.isInterrupted()) {
                        this.view.loadingIndicator(false);
                        return;
                    }
                    isWaitingReached = true;
                }
            }

            CharacterDataWrapperRequest request;

            if (text.equals("")) {
                request = new CharacterDataWrapperRequest.Builder()
                        .setLimit(40)
                        .setOffset(0)
                        .build();
            }
            else {
                request = new CharacterDataWrapperRequest.Builder()
                        .setLimit(20)
                        .setOffset(0)
                        .setNameStartsWith(text)
                        .build();
            }

            requestCharacters(request);
        });

        thread.start();
    }

    @Override
    public Intent getIntent() {
        return this.intent;
    }

    private void requestCharacters(CharacterDataWrapperRequest request) {
        try {
            CharacterDataWrapperResponse response = APIHelper.getCharacters(request);

            if (response.status.equals(STATUS_OK))
                this.view.updateRecyclerViewAtUiThread(response.data, getSuperHeros());

        } catch (Exception e) {
            if (!e.getMessage().equals("interrupted"))
                this.view.loadError();
        }
    }

    public ListSuperHeroPresenter() {
        super();
    }

    private HashMap<Long, SuperHero> getSuperHeros() {
        List<SuperHero> superHeroes = SuperHero.findAll();
        HashMap<Long, SuperHero> hashSuperHero = new HashMap<>();

        for(SuperHero superHero : superHeroes) {
            hashSuperHero.put(superHero.idSuperHero, superHero);
        }
        return hashSuperHero;
    }

    @Override
    public void onAdd(CharacterResponse character) {
        SuperHero superHero = new SuperHero();
        superHero.description = character.description;
        superHero.idSuperHero = character.id;
        superHero.name = character.name;
        superHero.quantityComics = character.comics != null && character.comics.items != null ? character.comics.items.size() : 0;
        superHero.imageExtension = character.thumbnail != null ? character.thumbnail.extension : "";
        superHero.urlImage = character.thumbnail != null ? character.thumbnail.path : "";

        boolean saved = superHero.save();

        if (saved)
            this.view.showToast(context.getString(R.string.successfuly_save_super_hero));
    }
}
