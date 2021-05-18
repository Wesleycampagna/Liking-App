package com.study.liking.api.responses.character_data_wrapper.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImageResponse implements Serializable {

    @SerializedName("path")
    public String path;

    @SerializedName("extension")
    public String extension;
}
