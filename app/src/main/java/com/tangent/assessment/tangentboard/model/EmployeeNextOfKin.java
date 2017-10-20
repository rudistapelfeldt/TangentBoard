package com.tangent.assessment.tangentboard.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rudi Stapelfeldt on 2017/10/20.
 */

public class EmployeeNextOfKin {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("relationship")
    private String mRelationshp;

    @SerializedName("phone_number")
    private String mPhoneNumber;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("physical_address")
    private String mPhysicalAddress;

    @SerializedName("employee")
    private int mEmployee;

    public EmployeeNextOfKin() {
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

    public String getmRelationshp() {
        return mRelationshp;
    }

    public void setmRelationshp(String mRelationshp) {
        this.mRelationshp = mRelationshp;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPhysicalAddress() {
        return mPhysicalAddress;
    }

    public void setmPhysicalAddress(String mPhysicalAddress) {
        this.mPhysicalAddress = mPhysicalAddress;
    }

    public int getmEmployee() {
        return mEmployee;
    }

    public void setmEmployee(int mEmployee) {
        this.mEmployee = mEmployee;
    }
}
