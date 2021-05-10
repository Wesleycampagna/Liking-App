package com.study.liking.api.responses;

import com.google.gson.annotations.SerializedName;
import com.study.liking.api.responses.character_data_wrapper.response.CharacterDataContainerResponse;

public class CharacterDataWrapperResponse {

    @SerializedName("code")
    public long code;

    @SerializedName("status")
    public String status;

    @SerializedName("copyright")
    public String copyright;

    @SerializedName("attributionText")
    public String attributionText;

    @SerializedName("attributionHTML")
    public String attributionHTML;

    @SerializedName("data")
    public CharacterDataContainerResponse data;

    @SerializedName("etag")
    public String etag;

}
