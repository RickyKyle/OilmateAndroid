package com.rickykyle.oilmate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reading {

    @SerializedName("readingID")
    @Expose
    private int readingID;
    @SerializedName("deviceID")
    @Expose
    private int deviceID;
    @SerializedName("reading")
    @Expose
    private float reading;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;

    public int getReadingID() {
        return readingID;
    }

    public void setReadingID(int readingID) {
        this.readingID = readingID;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public float getReading() {
        return reading;
    }

    public void setReading(float reading) {
        this.reading = reading;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}