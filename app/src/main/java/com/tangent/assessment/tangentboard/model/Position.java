package com.tangent.assessment.tangentboard.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rudi Stapelfeldt on 2017/10/19.
 */

public class Position {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("level")
    private String mLevel;

    @SerializedName("sort")
    private int mSort;

    public Position(){

    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmLevel() {
        return mLevel;
    }

    public void setmLevel(String mLevel) {
        this.mLevel = mLevel;
    }

    public int getmSort() {
        return mSort;
    }

    public void setmSort(int mSort) {
        this.mSort = mSort;
    }
}
