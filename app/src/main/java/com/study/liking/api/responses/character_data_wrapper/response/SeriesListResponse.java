package com.study.liking.api.responses.character_data_wrapper.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SeriesListResponse implements Serializable {

    @SerializedName("available")
    public int available;

    @SerializedName("returned")
    public int returned;

    @SerializedName("collectionURI")
    public String collectionURI;

    @SerializedName("items")
    public List<SeriesSummaryResponse> items;
}
