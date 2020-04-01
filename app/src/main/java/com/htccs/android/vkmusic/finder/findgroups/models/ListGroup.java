package com.htccs.android.vkmusic.finder.findgroups.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListGroup {

    @SerializedName("response")
    @Expose
    private ResponseListGroup response;

    public ResponseListGroup getResponse() {
        return response;
    }

    public void setResponse(ResponseListGroup response) {
        this.response = response;
    }
}
