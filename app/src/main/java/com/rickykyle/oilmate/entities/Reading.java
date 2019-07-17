package com.rickykyle.oilmate.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Date date;
    @SerializedName("time")
    @Expose
    private String time;

    public Reading(int readingID, int deviceID, float reading, Date date, String time) {
        this.readingID = readingID;
        this.deviceID = deviceID;
        this.reading = reading;
        this.date = date;
        this.time = time;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

