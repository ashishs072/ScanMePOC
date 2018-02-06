package com.analyticdata.scanmepoc.networking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by M1030099 on 11/21/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLeaderBoardList implements Serializable {

    @JsonProperty("Users")
    private UserLeaderBoardDetailList mLeaderList;


    public UserLeaderBoardDetailList getmLeaderList() {
        return mLeaderList;
    }

    public void setmLeaderList(UserLeaderBoardDetailList mLeaderList) {
        this.mLeaderList = mLeaderList;
    }
}
