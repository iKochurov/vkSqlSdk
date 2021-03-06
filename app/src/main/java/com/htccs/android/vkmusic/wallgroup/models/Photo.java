package com.htccs.android.vkmusic.wallgroup.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("album_id")
    @Expose
    private Integer albumId;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("photo_604")
    @Expose
    private String photoOneSize;
    @SerializedName("photo_807")
    @Expose
    private String photoTwoSize;
    @SerializedName("photo_1280")
    @Expose
    private String photoMaxSize;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("access_key")
    @Expose
    private String accessKey;

    public String getPhotoMaxSize() {
        return photoMaxSize;
    }

    public void setPhotoMaxSize(String photoMaxSize) {
        this.photoMaxSize = photoMaxSize;
    }

    public String getPhotoTwoSize() {
        return photoTwoSize;
    }

    public void setPhotoTwoSize(String photoTwoSize) {
        this.photoTwoSize = photoTwoSize;
    }

    public String getPhotoOneSize() {
        return photoOneSize;
    }

    public void setPhotoOneSize(String photoOneSize) {
        this.photoOneSize = photoOneSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
