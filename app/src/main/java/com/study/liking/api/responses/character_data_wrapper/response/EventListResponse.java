package com.study.liking.api.responses.character_data_wrapper.response;

import com.google.gson.annotations.SerializedName;

public class EventListResponse {

    @SerializedName("available")
    public int available;

    @SerializedName("returned")
    public int returned;

    @SerializedName("collectionURI")
    public String collectionURI;

    @SerializedName("items")
    public EventSummaryResponse items;

}
