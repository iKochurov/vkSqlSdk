package com.htccs.android.vkmusic.finder.findgroups.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.htccs.android.vkmusic.wallgroup.models.ResponseGroup;

import java.util.List;

public class GroupInfo {

    @SerializedName("response")

    @Expose
    private List<ResponseGroup> response = null;

    public List<ResponseGroup> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseGroup> response) {
        this.response = response;
    }

}
