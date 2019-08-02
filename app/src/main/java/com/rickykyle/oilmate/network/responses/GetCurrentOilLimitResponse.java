package com.rickykyle.oilmate.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Model response for getting current oil limit.
 */
public class GetCurrentOilLimitResponse {

    @SerializedName("oilLowerLimit")
    @Expose
    private Integer oilLowerLimit;

    public Integer getOilLowerLimit() {
        return oilLowerLimit;
    }

    public void setOilLowerLimit(Integer oilLowerLimit) {
        this.oilLowerLimit = oilLowerLimit;
    }

}