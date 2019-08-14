package com.htccs.android.vkmusic.wallgroup.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WallInfo {

    @SerializedName("response")
    @Expose
    private ResponseWall response;

    public ResponseWall getResponse() {
        return response;
    }

    public void setResponse(ResponseWall response) {
        this.response = response;
    }
}
