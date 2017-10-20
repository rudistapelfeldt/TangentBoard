package com.tangent.assessment.tangentboard.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rudi Stapelfeldt on 2017/10/19.
 */

public class User {

    @SerializedName("id")
    private int mId;

    @SerializedName("username")
    private String mUsername;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("is_active")
    private boolean mIsActive;

    @SerializedName("is_staff")
    private boolean mIsStaff;

    @SerializedName("last_name")
    private String mLastName;

    public User(){

    }

    public User(int mId, String mUsername, String mEmail, String mFirstName, boolean mIsActive, boolean mIsStaff, String mLastName) {
        this.mId = mId;
        this.mUsername = mUsername;
        this.mEmail = mEmail;
        this.mFirstName = mFirstName;
        this.mIsActive = mIsActive;
        this.mIsStaff = mIsStaff;
        this.mLastName = mLastName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public boolean isActive() {
        return mIsActive;
    }

    public void setIsActive(boolean isActive) {
        this.mIsActive = isActive;
    }

    public boolean isStaff() {
        return mIsStaff;
    }

    public void setIsStaff(boolean isStaff) {
        this.mIsStaff = isStaff;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }
}
