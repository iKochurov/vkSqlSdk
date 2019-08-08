package com.htccs.android.vkmusic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Audio {

    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("track_code")
    @Expose
    private String trackCode;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("main_artists")
    @Expose
    private List<MainArtist> mainArtists = null;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTrackCode() {
        return trackCode;
    }

    public void setTrackCode(String trackCode) {
        this.trackCode = trackCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public List<MainArtist> getMainArtists() {
        return mainArtists;
    }

    public void setMainArtists(List<MainArtist> mainArtists) {
        this.mainArtists = mainArtists;
    }
}
