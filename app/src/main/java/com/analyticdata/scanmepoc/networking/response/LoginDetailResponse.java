package com.analyticdata.scanmepoc.networking.response;

import com.analyticdata.scanmepoc.networking.pojo.CelebrityStoryDetailList;
import com.analyticdata.scanmepoc.networking.pojo.CelebrityStoryDetails;
import com.analyticdata.scanmepoc.networking.pojo.PGStoryDetailsList;
import com.analyticdata.scanmepoc.networking.pojo.StoryDetails;
import com.analyticdata.scanmepoc.networking.pojo.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by M1030099 on 11/14/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDetailResponse implements Serializable {
    @JsonProperty("User")
    private UserDetails mUserDetail;

    @JsonProperty("Stories")
    private ArrayList<StoryDetails> storyDetail;

    @JsonProperty("CelebrityStories")
    private CelebrityStoryDetailList celebritystoryDetail;


    @JsonProperty("PgStories")
    private PGStoryDetailsList pgDetails;

    public CelebrityStoryDetailList getCelebritystoryDetail() {
        return celebritystoryDetail;
    }

    public void setCelebritystoryDetail(CelebrityStoryDetailList celebritystoryDetail) {
        this.celebritystoryDetail = celebritystoryDetail;
    }

    public PGStoryDetailsList getPgDetails() {
        return pgDetails;
    }

    public void setPgDetails(PGStoryDetailsList pgDetails) {
        this.pgDetails = pgDetails;
    }

    public UserDetails getmUserDetail() {
        return mUserDetail;
    }

    public void setmUserDetail(UserDetails mUserDetail) {
        this.mUserDetail = mUserDetail;
    }

    public ArrayList<StoryDetails> getStoryDetail() {
        return storyDetail;
    }

    public void setStoryDetail(ArrayList<StoryDetails> storyDetail) {
        this.storyDetail = storyDetail;
    }
}
