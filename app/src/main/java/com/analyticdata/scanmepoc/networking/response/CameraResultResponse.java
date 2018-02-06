package com.analyticdata.scanmepoc.networking.response;

import com.analyticdata.scanmepoc.networking.pojo.AgencyId;
import com.analyticdata.scanmepoc.networking.pojo.MethodDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by M1030099 on 11/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CameraResultResponse implements Serializable {

    @JsonProperty("category")
    private String mCategory;

    @JsonProperty("region")
    private String mRegion;

    @JsonProperty("source")
    private String mSource;

    @JsonProperty("agencies")
    private AgencyId mAgencyId;

    @JsonProperty("methods")
    private MethodDetail mmethodId;

    public AgencyId getmAgencyId() {
        return mAgencyId;
    }

    public void setmAgencyId(AgencyId mAgencyId) {
        this.mAgencyId = mAgencyId;
    }

    public MethodDetail getMmethodId() {
        return mmethodId;
    }

    public void setMmethodId(MethodDetail mmethodId) {
        this.mmethodId = mmethodId;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmRegion() {
        return mRegion;
    }

    public void setmRegion(String mRegion) {
        this.mRegion = mRegion;
    }

    public String getmSource() {
        return mSource;
    }

    public void setmSource(String mSource) {
        this.mSource = mSource;
    }

}