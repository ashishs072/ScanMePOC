package com.analyticdata.scanmepoc.networking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by M1030099 on 11/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyId implements Serializable {
    @JsonProperty("agencyID")
    private ArrayList<String> mAgencyId;


    public ArrayList<String> getmAgencyId() {
        return mAgencyId;
    }

    public void setmAgencyId(ArrayList<String> mAgencyId) {
        this.mAgencyId = mAgencyId;
    }
}
