package com.tangent.assessment.tangentboard.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rudi Stapelfeldt on 2017/10/20.
 */

public class EmployeeReview {

    @SerializedName("id")
    private int mId;

    @SerializedName("date")
    private String mDate;

    @SerializedName("salary")
    private String mSalary;

    @SerializedName("type")
    private String mType;

    public EmployeeReview() {
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmSalary() {
        return mSalary;
    }

    public void setmSalary(String mSalary) {
        this.mSalary = mSalary;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }
}
