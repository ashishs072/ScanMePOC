package com.analyticdata.scanmepoc.networking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by M1030099 on 11/24/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyDetails implements Serializable {
    @JsonProperty("ID")
    private String ID;

    @JsonProperty("Name")
    private String mName;

    @JsonProperty("Region")
    private String mRegion;

    @JsonProperty("Website")
    private String mWebsite;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmRegion() {
        return mRegion;
    }

    public void setmRegion(String mRegion) {
        this.mRegion = mRegion;
    }

    public String getmWebsite() {
        return mWebsite;
    }

    public void setmWebsite(String mWebsite) {
        this.mWebsite = mWebsite;
    }
}
