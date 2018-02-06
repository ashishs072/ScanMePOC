package com.analyticdata.scanmepoc.networking.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by M1030099 on 11/13/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDetailObject {
    @JsonProperty("EmailID")
    private String userName;
    @JsonProperty("Password")
    private String mPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
