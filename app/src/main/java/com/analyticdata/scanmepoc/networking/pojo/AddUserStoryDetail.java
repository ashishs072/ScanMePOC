package com.analyticdata.scanmepoc.networking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by M1030099 on 11/23/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddUserStoryDetail {
    @JsonProperty("ID")
    private String mID;

    @JsonProperty("UserID")
    private String mUserName;

    @JsonProperty("Title")
    private String mTitle;

    @JsonProperty("Description")
    private String mDescription;

    @JsonProperty("Image")
    private String mImagePath;

    @JsonProperty("Thumbnail")
    private String mThumNailImagePath;

    @JsonProperty("CreatedDate")
    private String mCreatedDate;


    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmImagePath() {
        return mImagePath;
    }

    public void setmImagePath(String mImagePath) {
        this.mImagePath = mImagePath;
    }

    public String getmThumNailImagePath() {
        return mThumNailImagePath;
    }

    public void setmThumNailImagePath(String mThumNailImagePath) {
        this.mThumNailImagePath = mThumNailImagePath;
    }

    public String getmCreatedDate() {
        return mCreatedDate;
    }

    public void setmCreatedDate(String mCreatedDate) {
        this.mCreatedDate = mCreatedDate;
    }
}
