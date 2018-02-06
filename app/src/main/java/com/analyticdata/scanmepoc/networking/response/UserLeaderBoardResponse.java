package com.analyticdata.scanmepoc.networking.response;

import com.analyticdata.scanmepoc.networking.pojo.UserLeaderBoardList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by M1030099 on 11/21/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLeaderBoardResponse implements Serializable {
    @JsonProperty("GetLeaderBoardDetailsResult")
    private UserLeaderBoardList mLeaderResponse;

    public UserLeaderBoardList getmLeaderResponse() {
        return mLeaderResponse;
    }

    public void setmLeaderResponse(UserLeaderBoardList mLeaderResponse) {
        this.mLeaderResponse = mLeaderResponse;
    }
}
