package com.study.liking.api.responses.character_data_wrapper.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CharacterResponse implements Serializable {

    @SerializedName("id")
    public long id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("modified")
    public Date modified;

    @SerializedName("resourceURI")
    public String resourceURI;

    @SerializedName("urls")
    public List<URLResponse> urls;

    @SerializedName("thumbnail")
    public ImageResponse thumbnail;

    @SerializedName("comics")
    public ComicListLesponse comics;

    @SerializedName("stories")
    public StoryListResponse stories;

    @SerializedName("events")
    public EventListResponse events;

    @SerializedName("series")
    public SeriesListResponse series;

}
