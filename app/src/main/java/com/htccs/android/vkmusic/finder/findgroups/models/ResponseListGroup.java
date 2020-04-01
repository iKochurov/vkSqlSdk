package com.htccs.android.vkmusic.finder.findgroups.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseListGroup {

    @SerializedName("response")
    @Expose
    private ResponseFind response;

    public ResponseFind getResponse() {
        return response;
    }

    public void setResponse(ResponseFind response) {
        this.response = response;
    }
}
