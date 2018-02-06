package com.analyticdata.scanmepoc.networking.response;

import com.analyticdata.scanmepoc.networking.pojo.AgencyDetailList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by M1030099 on 11/24/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CameraResultDetailResponse implements Serializable {

    @JsonProperty("Agencies")
    private AgencyDetailList mAgencyDetailList;

    @JsonProperty("Details")
    private CameraResultResponse mCameraDetail;

    public AgencyDetailList getmAgencyDetailList() {
        return mAgencyDetailList;
    }

    public void setmAgencyDetailList(AgencyDetailList mAgencyDetailList) {
        this.mAgencyDetailList = mAgencyDetailList;
    }

    public CameraResultResponse getmCameraDetail() {
        return mCameraDetail;
    }

    public void setmCameraDetail(CameraResultResponse mCameraDetail) {
        this.mCameraDetail = mCameraDetail;
    }
}
