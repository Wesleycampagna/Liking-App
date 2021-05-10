package com.study.liking.api.responses.character_data_wrapper.response;

import com.google.gson.annotations.SerializedName;

public class SeriesListResponse {

    @SerializedName("available")
    public String available;

    @SerializedName("returned")
    public String returned;

    @SerializedName("collectionURI")
    public String collectionURI;

    @SerializedName("items")
    public String items;
}
