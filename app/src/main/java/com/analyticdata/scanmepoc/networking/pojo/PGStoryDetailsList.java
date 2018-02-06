package com.analyticdata.scanmepoc.networking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by M1030099 on 11/17/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PGStoryDetailsList implements Serializable{

    @JsonProperty("PGStory")
    private ArrayList<PGStoryDetails> mPgDetalList;

    public ArrayList<PGStoryDetails> getmPgDetalList() {
        return mPgDetalList;
    }

    public void setmPgDetalList(ArrayList<PGStoryDetails> mPgDetalList) {
        this.mPgDetalList = mPgDetalList;
    }
}
