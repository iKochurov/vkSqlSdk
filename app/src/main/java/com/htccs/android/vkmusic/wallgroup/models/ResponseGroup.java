package com.htccs.android.vkmusic.wallgroup.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseGroup {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("screen_name")
    @Expose
    private String screenName;
    @SerializedName("photo_50")
    @Expose
    private String photoFirstSize;
    @SerializedName("photo_100")
    @Expose
    private String photoSecondSize;
    @SerializedName("photo_200")
    @Expose
    private String photoThirdSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getPhotoFirstSize() {
        return photoFirstSize;
    }

    public void setPhotoFirstSize(String photoFirstSize) {
        this.photoFirstSize = photoFirstSize;
    }

    public String getPhotoSecondSize() {
        return photoSecondSize;
    }

    public void setPhotoSecondSize(String photoSecondSize) {
        this.photoSecondSize = photoSecondSize;
    }

    public String getPhotoThirdSize() {
        return photoThirdSize;
    }

    public void setPhotoThirdSize(String photoThirdSize) {
        this.photoThirdSize = photoThirdSize;
    }

}