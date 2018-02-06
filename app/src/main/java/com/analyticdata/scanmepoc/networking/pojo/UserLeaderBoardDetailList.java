package com.analyticdata.scanmepoc.networking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by M1030099 on 11/21/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLeaderBoardDetailList implements Serializable {

    @JsonProperty("User")
    private ArrayList<UserLeaderBoardDetail> mLeaderBoardList;


    public ArrayList<UserLeaderBoardDetail> getmLeaderBoardList() {
        return mLeaderBoardList;
    }

    public void setmLeaderBoardList(ArrayList<UserLeaderBoardDetail> mLeaderBoardList) {
        this.mLeaderBoardList = mLeaderBoardList;
    }
}
