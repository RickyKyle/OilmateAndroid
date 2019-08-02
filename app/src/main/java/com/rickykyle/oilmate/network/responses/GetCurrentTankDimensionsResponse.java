package com.rickykyle.oilmate.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Model response for getting the user's current tank dimensions.
 */
public class GetCurrentTankDimensionsResponse {

    @SerializedName("userID")
    @Expose
    private Integer userID;
    @SerializedName("tankID")
    @Expose
    private Integer tankID;
    @SerializedName("diameter")
    @Expose
    private double diameter;
    @SerializedName("length")
    @Expose
    private double length;
    @SerializedName("oillowerlimit")
    @Expose
    private Integer oillowerlimit;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getTankID() {
        return tankID;
    }

    public void setTankID(Integer tankID) {
        this.tankID = tankID;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Integer getOillowerlimit() {
        return oillowerlimit;
    }

    public void setOillowerlimit(Integer oillowerlimit) {
        this.oillowerlimit = oillowerlimit;
    }

}

