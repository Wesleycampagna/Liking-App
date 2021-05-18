package com.study.liking.model_view_controller.super_hero_info;

import android.content.Context;
import android.content.Intent;

import com.study.liking.R;
import com.study.liking.api.responses.character_data_wrapper.response.CharacterResponse;
import com.study.liking.models.SuperHero;
import com.study.liking.utils.Constants;

public class SuperHeroInfoPresenter implements SuperHeroInfoContract.Presenter {

    private Context context;
    private Intent intent;
    private SuperHeroInfoContract.View view;

    public SuperHeroInfoPresenter(Context context, SuperHeroInfoContract.View view, Intent intent) {
        this.context = context;
        this.intent = intent;
        this.view = view;
    }

    @Override
    public void init() {
        SuperHero superHero;
        if (intent == null) {
            view.showToastMessage("Houve um erro durante a renderização da tela!");
            view.closeActivity();
            return;
        }

        if (intent.getBooleanExtra(Constants.Bundle.IS_SUPER_HERO_SAVED, false)) {
            CharacterResponse characterResponse = (CharacterResponse) intent.getSerializableExtra(Constants.Bundle.SUPER_HERO);
            superHero = new SuperHero();
            superHero.imageExtension = characterResponse.thumbnail.extension;
            superHero.description = !characterResponse.description.equals("") && characterResponse.description != null ? characterResponse.description : context.getString(R.string.no_description);
            superHero.urlImage = characterResponse.thumbnail.path;
            superHero.idSuperHero = characterResponse.id;
            superHero.name = characterResponse.name;
            superHero.api = true;
        }
        else {
            long superHeroId = intent != null ? intent.getLongExtra(Constants.Bundle.SUPER_HERO_ID, 0L) : 0L;
            if (superHeroId != 0L) superHero = SuperHero.find(superHeroId);
            else {
                view.showToastMessage("Houve um erro durante a renderização da tela!");
                view.closeActivity();
                return;
            }
        }
        this.view.fitUI(superHero);
    }
}
