package com.analyticdata.scanmepoc.networking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by M1030099 on 11/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MethodDetail implements Serializable {
    @JsonProperty("method")
    private ArrayList<String> methodDetail;


    public ArrayList<String> getMethodDetail() {
        return methodDetail;
    }

    public void setMethodDetail(ArrayList<String> methodDetail) {
        this.methodDetail = methodDetail;
    }
}
