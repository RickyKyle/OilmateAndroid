package com.rickykyle.oilmate.network.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PutNewOilLimitRequest {

    @SerializedName("userID")
    @Expose
    private Integer userID;
    @SerializedName("oilLowerLimit")
    @Expose
    private Integer oilLowerLimit;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getOilLowerLimit() {
        return oilLowerLimit;
    }

    public void setOilLowerLimit(Integer oilLowerLimit) {
        this.oilLowerLimit = oilLowerLimit;
    }

}
