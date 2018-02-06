package com.analyticdata.scanmepoc.networking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by M1030099 on 11/16/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoryDetails implements Serializable{
    @JsonProperty("Thumbnail")
    private String mThumnail;
    @JsonProperty("ID")
    private String id;

    @JsonProperty("UserID")
    private String userID;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Image")
    private String imageLink;

    @JsonProperty("Links")
    private String links;

    @JsonProperty("CreatedDate")
    private String createdDate;

    public String getmThumnail() {
        return mThumnail;
    }

    public void setmThumnail(String mThumnail) {
        this.mThumnail = mThumnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
