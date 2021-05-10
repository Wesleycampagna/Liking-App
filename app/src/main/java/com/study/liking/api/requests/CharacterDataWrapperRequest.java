package com.study.liking.api.requests;

import android.content.Context;

import com.study.liking.api.ApiKey;
import com.study.liking.utils.CryptoUtils;

import java.util.Date;

public class CharacterDataWrapperRequest {

    public Context context;
    public String ts;
    public String apikey;
    public String hash;
    public String name;
    public String nameStartsWith;
    public Date modifiedSince;
    public int comics;
    public int series;
    public int events;
    public int stories;
    public String orderBy;
    public int limit;
    public int offset;

    private CharacterDataWrapperRequest(Builder builder) {
        this.ts = builder.getTs();
        this.apikey = builder.getApikey();
        this.hash = builder.getHash();

        this.context = builder.context;
        this.name = builder.name;
        this.nameStartsWith = builder.nameStartsWith;
        this.modifiedSince = builder.modifiedSince;
        this.comics = builder.comics;
        this.series = builder.series;
        this.events = builder.events;
        this.stories = builder.stories;
        this.orderBy = builder.orderBy;
        this.limit = builder.limit;
        this.offset = builder.offset;
    }

    public static class Builder {

        private Context context;
        private String ts;
        private String apikey;
        private String hash;
        private String name;
        private String nameStartsWith;
        private Date modifiedSince;
        private int comics;
        private int series;
        private int events;
        private int stories;
        private String orderBy;
        private int limit;
        private int offset;

        public Builder setTs() {
            long tsLong = System.currentTimeMillis() / 1000;
            this.ts = Long.toString(tsLong);
            return this;
        }

        protected String getTs() {
            return ts != null ? ts : setTsAndReturn();
        }

        protected String getApikey() {
            return ApiKey.getPublicKey();
        }

        private String setTsAndReturn() {
            this.setTs();
            return getTs();
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        protected String getHash() {
            String ts = getTs() != null ? getTs() : setTsAndReturn();
            return CryptoUtils.md5(ts + ApiKey.getPrivateKey() + getApikey());
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setNameStartsWith(String nameStartsWith) {
            this.nameStartsWith = nameStartsWith;
            return this;
        }

        public Builder setModifiedSince(Date modifiedSince) {
            this.modifiedSince = modifiedSince;
            return this;
        }

        public Builder setComics(int comics) {
            this.comics = comics;
            return this;
        }

        public Builder setSeries(int series) {
            this.series = series;
            return this;
        }

        public Builder setEvents(int events) {
            this.events = events;
            return this;
        }

        public Builder setStories(int stories) {
            this.stories = stories;
            return this;
        }

        public Builder setOrderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        public Builder setLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder setOffset(int offset) {
            this.offset = offset;
            return this;
        }

        public CharacterDataWrapperRequest build() {
            return new CharacterDataWrapperRequest(this);
        }
    }
}
