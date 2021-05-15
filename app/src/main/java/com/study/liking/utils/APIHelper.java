package com.study.liking.utils;

import com.study.liking.api.MarvelApi;
import com.study.liking.api.requests.CharacterDataWrapperRequest;
import com.study.liking.api.responses.CharacterDataWrapperResponse;

public class APIHelper {

//    public static CharacterDataWrapperResponse getCharacters(CharacterDataWrapperRequest request) {
//        return Api.getApiMarvel(request.context).getMarvelCharactersResponse(
//                request.ts,
//                request.apikey,
//                request.hash,
//                request.name,
//                request.nameStartsWith,
//                request.modifiedSince,
//                request.comics,
//                request.series,
//                request.events,
//                request.stories,
//                request.orderBy,
//                request.limit,
//                request.offset
//        );
//    }

    public static CharacterDataWrapperResponse getCharacters(CharacterDataWrapperRequest request) {
        return MarvelApi.getApiMarvel(request.context).getMarvelCharactersResponse(
                request.ts,
                request.apikey,
                request.hash,
                request.nameStartsWith,
                request.modifiedSince,
                request.limit,
                request.offset
        );
    }
}
