package com.htccs.android.vkmusic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainArtist {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("is_followed")
    @Expose
    private Boolean isFollowed;
    @SerializedName("can_follow")
    @Expose
    private Boolean canFollow;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("id")
    @Expose
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(Boolean isFollowed) {
        this.isFollowed = isFollowed;
    }

    public Boolean getCanFollow() {
        return canFollow;
    }

    public void setCanFollow(Boolean canFollow) {
        this.canFollow = canFollow;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
