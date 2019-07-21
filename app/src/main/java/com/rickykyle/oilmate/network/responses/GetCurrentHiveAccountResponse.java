package com.rickykyle.oilmate.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCurrentHiveAccountResponse {

    @SerializedName("hiveID")
    @Expose
    private Integer hiveID;
    @SerializedName("userID")
    @Expose
    private Integer userID;
    @SerializedName("hiveUsername")
    @Expose
    private String hiveUsername;
    @SerializedName("hivePassword")
    @Expose
    private String hivePassword;
    @SerializedName("setTemperature")
    @Expose
    private Integer setTemperature;

    public Integer getHiveID() {
        return hiveID;
    }

    public void setHiveID(Integer hiveID) {
        this.hiveID = hiveID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getHiveUsername() {
        return hiveUsername;
    }

    public void setHiveUsername(String hiveUsername) {
        this.hiveUsername = hiveUsername;
    }

    public String getHivePassword() {
        return hivePassword;
    }

    public void setHivePassword(String hivePassword) {
        this.hivePassword = hivePassword;
    }

    public Integer getSetTemperature() {
        return setTemperature;
    }

    public void setSetTemperature(Integer setTemperature) {
        this.setTemperature = setTemperature;
    }
}
