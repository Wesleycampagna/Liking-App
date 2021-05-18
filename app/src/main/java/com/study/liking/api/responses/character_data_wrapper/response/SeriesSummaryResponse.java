package com.study.liking.api.responses.character_data_wrapper.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SeriesSummaryResponse implements Serializable {

    @SerializedName("resourceURI")
    public String resourceURI;

    @SerializedName("name")
    public String name;
}
