package com.analyticdata.scanmepoc.networking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by M1030099 on 11/17/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PGStoryDetails implements Serializable {

    @JsonProperty("ID")
    private String ID;

    @JsonProperty("Title")
    private String mTitle;

    @JsonProperty("Description")
    private String mDescription;

    @JsonProperty("ImageLink")
    private String Imagelink;

    @JsonProperty("Links")
    private String mLinks;

    @JsonProperty("CreatedDate")
    private String mCreatedDate;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getImagelink() {
        return Imagelink;
    }

    public void setImagelink(String imagelink) {
        Imagelink = imagelink;
    }

    public String getmLinks() {
        return mLinks;
    }

    public void setmLinks(String mLinks) {
        this.mLinks = mLinks;
    }

    public String getmCreatedDate() {
        return mCreatedDate;
    }

    public void setmCreatedDate(String mCreatedDate) {
        this.mCreatedDate = mCreatedDate;
    }
}
