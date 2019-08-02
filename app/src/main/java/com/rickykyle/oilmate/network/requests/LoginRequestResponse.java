package com.rickykyle.oilmate.network.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Model for the request to and response from the API when the user logs in.
 */
public class LoginRequestResponse {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @Expose
    private String token;

    @Expose
    private int userID;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

}