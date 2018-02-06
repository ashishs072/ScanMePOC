package com.analyticdata.scanmepoc.networking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by M1030099 on 11/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CelebrityStoryDetailList implements Serializable {
    @JsonProperty("CelebrityStory")
    private ArrayList<CelebrityStoryDetails> mCelebrityPgDetalList;

    public ArrayList<CelebrityStoryDetails> getmCelebrityPgDetalList() {
        return mCelebrityPgDetalList;
    }

    public void setmCelebrityPgDetalList(ArrayList<CelebrityStoryDetails> mCelebrityPgDetalList) {
        this.mCelebrityPgDetalList = mCelebrityPgDetalList;
    }
}
