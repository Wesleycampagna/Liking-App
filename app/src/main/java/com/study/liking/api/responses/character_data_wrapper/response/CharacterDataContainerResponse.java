package com.study.liking.api.responses.character_data_wrapper.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterDataContainerResponse {

    @SerializedName("offset")
    public int offset;

    @SerializedName("limit")
    public int limit;

    @SerializedName("total")
    public int total;

    @SerializedName("count")
    public int count;

    @SerializedName("results")
    public List<CharacterResponse> results;

}
