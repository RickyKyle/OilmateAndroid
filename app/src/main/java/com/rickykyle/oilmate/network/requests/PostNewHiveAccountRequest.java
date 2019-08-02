package com.rickykyle.oilmate.network.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Model for the data sent in a POST to the API when the user is linking
 * their Hive account.
 */
public class PostNewHiveAccountRequest {

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