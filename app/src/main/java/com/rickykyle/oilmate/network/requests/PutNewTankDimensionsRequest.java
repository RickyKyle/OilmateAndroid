package com.rickykyle.oilmate.network.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Model for the changing tank dimensions request.
 */
public class PutNewTankDimensionsRequest {

    @SerializedName("userID")
    @Expose
    private Integer userID;
    @SerializedName("diameter")
    @Expose
    private double diameter;
    @SerializedName("length")
    @Expose
    private double length;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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

}
