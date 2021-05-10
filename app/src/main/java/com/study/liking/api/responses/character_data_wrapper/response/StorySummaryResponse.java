package com.study.liking.api.responses.character_data_wrapper.response;

import com.google.gson.annotations.SerializedName;

public class StorySummaryResponse {

    @SerializedName("resourceURI")
    public String resourceURI;

    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;

}
