package com.htccs.android.vkmusic.finder.findgroups.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("screen_name")
    @Expose
    private String screenName;
    @SerializedName("is_closed")
    @Expose
    private Integer isClosed;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("is_admin")
    @Expose
    private Integer isAdmin;
    @SerializedName("is_member")
    @Expose
    private Integer isMember;
    @SerializedName("is_advertiser")
    @Expose
    private Integer isAdvertiser;
    @SerializedName("photo_50")
    @Expose
    private String photoFirstSize;

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

    public Integer getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Integer isClosed) {
        this.isClosed = isClosed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getIsMember() {
        return isMember;
    }

    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }

    public Integer getIsAdvertiser() {
        return isAdvertiser;
    }

    public void setIsAdvertiser(Integer isAdvertiser) {
        this.isAdvertiser = isAdvertiser;
    }

    public String getPhotoFirstSize() {
        return photoFirstSize;
    }

    public void setPhotoFirstSize(String photoFirstSize) {
        this.photoFirstSize = photoFirstSize;
    }

}
