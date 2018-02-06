package com.analyticdata.scanmepoc.networking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by M1030099 on 11/21/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLeaderBoardDetail implements Serializable {

    @JsonProperty("ContactNumber")
    private String mContactNumber;

    @JsonProperty("EmailID")
    private String mEmailId;

    @JsonProperty("FirstName")
    private String mFirstName;

    @JsonProperty("LastName")
    private String mLastName;

    @JsonProperty("Locality")
    private String mLocality;

    @JsonProperty("LoyaltyPoints")
    private String mLoyalityPoint;

    @JsonProperty("ProfileImage")
    private String mProfileImage;

    @JsonProperty("ID")
    private String mId;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmContactNumber() {
        return mContactNumber;
    }

    public void setmContactNumber(String mContactNumber) {
        this.mContactNumber = mContactNumber;
    }

    public String getmEmailId() {
        return mEmailId;
    }

    public void setmEmailId(String mEmailId) {
        this.mEmailId = mEmailId;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmLocality() {
        return mLocality;
    }

    public void setmLocality(String mLocality) {
        this.mLocality = mLocality;
    }

    public String getmLoyalityPoint() {
        return mLoyalityPoint;
    }

    public void setmLoyalityPoint(String mLoyalityPoint) {
        this.mLoyalityPoint = mLoyalityPoint;
    }

    public String getmProfileImage() {
        return mProfileImage;
    }

    public void setmProfileImage(String mProfileImage) {
        this.mProfileImage = mProfileImage;
    }
}
