package com.rickykyle.oilmate.network.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PutNewTargetTemperatureRequest {

    @SerializedName("userID")
    @Expose
    private Integer userID;
    @SerializedName("setTemperature")
    @Expose
    private Integer setTemperature;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getSetTemperature() {
        return setTemperature;
    }

    public void setSetTemperature(Integer setTemperature) {
        this.setTemperature = setTemperature;
    }

}