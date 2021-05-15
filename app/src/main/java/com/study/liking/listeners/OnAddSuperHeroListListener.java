package com.study.liking.listeners;

import com.study.liking.api.responses.character_data_wrapper.response.CharacterResponse;

public interface OnAddSuperHeroListListener {

    void onAdd(CharacterResponse character);
}
