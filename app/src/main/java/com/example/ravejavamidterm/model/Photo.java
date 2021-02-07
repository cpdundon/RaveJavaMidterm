package com.example.ravejavamidterm.model;

public class Photo {
    public final String photographer;
    public final String photographer_url;
    public final Src src;

    Photo (String photographer, String photographerUrl, Src src) {
        this.photographer = photographer;
        this.photographer_url = photographerUrl;
        this.src = src;
    }
}

