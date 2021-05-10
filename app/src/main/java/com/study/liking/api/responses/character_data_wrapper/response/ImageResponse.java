package com.study.liking.api.responses.character_data_wrapper.response;

import com.google.gson.annotations.SerializedName;

class ImageResponse {

    @SerializedName("path")
    public String path;

    @SerializedName("extension")
    public String extension;
}
