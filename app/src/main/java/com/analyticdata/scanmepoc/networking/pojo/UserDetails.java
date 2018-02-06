package com.analyticdata.scanmepoc.networking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by M1030099 on 11/16/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails implements Serializable {
    @JsonProperty("ID")
    private String Id;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("Locality")
    private String locality;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("EmailID")
    private String mailID;

    @JsonProperty("ContactNumber")
    private String contactNumber;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("CurrentLoyaltyPoints")
    private String currentLoyalitypoints;

    @JsonProperty("LoyaltyPoints")
    private String mLoyalityPoint;

    public String getmLoyalityPoint() {
        return mLoyalityPoint;
    }

    public void setmLoyalityPoint(String mLoyalityPoint) {
        this.mLoyalityPoint = mLoyalityPoint;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailID() {
        return mailID;
    }

    public void setMailID(String mailID) {
        this.mailID = mailID;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentLoyalitypoints() {
        return currentLoyalitypoints;
    }

    public void setCurrentLoyalitypoints(String currentLoyalitypoints) {
        this.currentLoyalitypoints = currentLoyalitypoints;
    }
}
