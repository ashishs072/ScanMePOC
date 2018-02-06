package com.analyticdata.scanmepoc.networking.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by M1030099 on 11/6/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageDetailsObject {
    @JsonProperty("Image")
    private String mImagePath;
    @JsonProperty("Lattitude")
    private String latitiudePoint;
    @JsonProperty("Longitude")
    private String longnitudePoint;
    @JsonProperty("id")
    private String id;
    @JsonProperty("UserID")
    private String mUserName;


    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmImagePath() {
        return mImagePath;
    }

    public void setmImagePath(String mImagePath) {
        this.mImagePath = mImagePath;
    }

    public String getLatitiudePoint() {
        return latitiudePoint;
    }

    public void setLatitiudePoint(String latitiudePoint) {
        this.latitiudePoint = latitiudePoint;
    }

    public String getLongnitudePoint() {
        return longnitudePoint;
    }

    public void setLongnitudePoint(String longnitudePoint) {
        this.longnitudePoint = longnitudePoint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
