package com.tangent.assessment.tangentboard.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Rudi Stapelfeldt on 2017/10/20.
 */

public class MyEmployeeData {

    @SerializedName("id")
    private int mId;

    @SerializedName("user")
    private User mUser;

    @SerializedName("position")
    private Position mPosition;

    @SerializedName("employee_next_of_kin")
    private ArrayList<EmployeeNextOfKin> mEmployeeNextOfKinList;

    @SerializedName("employee_review")
    private ArrayList<EmployeeReview> mEmployeeReview;

    @SerializedName("id_number")
    private String mIdNumber;

    @SerializedName("phone_number")
    private String mPhoneNumber;

    @SerializedName("physical_address")
    private String mPhysicalAddress;

    @SerializedName("tax_number")
    private String mTaxNumber;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("personal_email")
    private String mPersonalEmail;

    @SerializedName("github_user")
    private String mGitHubUser;

    @SerializedName("birth_date")
    private String mBirthDate;

    @SerializedName("start_date")
    private String mStartDate;

    @SerializedName("end_date")
    private String mEndDate;

    @SerializedName("id_document")
    private String mIdDocument;

    @SerializedName("visa_document")
    private String mVisaDocument;

    @SerializedName("is_employed")
    private boolean mIsEmployed;

    @SerializedName("is_foreigner")
    private boolean mIsForeigner;

    @SerializedName("gender")
    private String mGender;

    @SerializedName("race")
    private String mRace;

    @SerializedName("years_worked")
    private int mYearsWorked;

    @SerializedName("age")
    private int mAge;

    @SerializedName("next_review")
    private String mNextReview;

    @SerializedName("days_to_birthday")
    private int mDaysToBirthday;

    @SerializedName("leave_remaining")
    private String mLeaveRemaining;


    public MyEmployeeData() {
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    public Position getmPosition() {
        return mPosition;
    }

    public void setmPosition(Position mPosition) {
        this.mPosition = mPosition;
    }

    public ArrayList<EmployeeNextOfKin> getmEmployeeNextOfKinList() {
        return mEmployeeNextOfKinList;
    }

    public void setmEmployeeNextOfKinList(ArrayList<EmployeeNextOfKin> mEmployeeNextOfKinList) {
        this.mEmployeeNextOfKinList = mEmployeeNextOfKinList;
    }

    public ArrayList<EmployeeReview> getmEmployeeReview() {
        return mEmployeeReview;
    }

    public void setmEmployeeReview(ArrayList<EmployeeReview> mEmployeeReview) {
        this.mEmployeeReview = mEmployeeReview;
    }

    public String getmIdNumber() {
        return mIdNumber;
    }

    public void setmIdNumber(String mIdNumber) {
        this.mIdNumber = mIdNumber;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmPhysicalAddress() {
        return mPhysicalAddress;
    }

    public void setmPhysicalAddress(String mPhysicalAddress) {
        this.mPhysicalAddress = mPhysicalAddress;
    }

    public String getmTaxNumber() {
        return mTaxNumber;
    }

    public void setmTaxNumber(String mTaxNumber) {
        this.mTaxNumber = mTaxNumber;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPersonalEmail() {
        return mPersonalEmail;
    }

    public void setmPersonalEmail(String mPersonalEmail) {
        this.mPersonalEmail = mPersonalEmail;
    }

    public String getmGitHubUser() {
        return mGitHubUser;
    }

    public void setmGitHubUser(String mGitHubUser) {
        this.mGitHubUser = mGitHubUser;
    }

    public String getmBirthDate() {
        return mBirthDate;
    }

    public void setmBirthDate(String mBirthDate) {
        this.mBirthDate = mBirthDate;
    }

    public String getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public String getmEndDate() {
        return mEndDate;
    }

    public void setmEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }

    public String getmIdDocument() {
        return mIdDocument;
    }

    public void setmIdDocument(String mIdDocument) {
        this.mIdDocument = mIdDocument;
    }

    public String getmVisaDocument() {
        return mVisaDocument;
    }

    public void setmVisaDocument(String mVisaDocument) {
        this.mVisaDocument = mVisaDocument;
    }

    public boolean ismIsEmployed() {
        return mIsEmployed;
    }

    public void setmIsEmployed(boolean mIsEmployed) {
        this.mIsEmployed = mIsEmployed;
    }

    public boolean ismIsForeigner() {
        return mIsForeigner;
    }

    public void setmIsForeigner(boolean mIsForeigner) {
        this.mIsForeigner = mIsForeigner;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public String getmRace() {
        return mRace;
    }

    public void setmRace(String mRace) {
        this.mRace = mRace;
    }

    public int getmYearsWorked() {
        return mYearsWorked;
    }

    public void setmYearsWorked(int mYearsWorked) {
        this.mYearsWorked = mYearsWorked;
    }

    public int getmAge() {
        return mAge;
    }

    public void setmAge(int mAge) {
        this.mAge = mAge;
    }

    public String getmNextReview() {
        return mNextReview;
    }

    public void setmNextReview(String mNextReview) {
        this.mNextReview = mNextReview;
    }

    public int getmDaysToBirthday() {
        return mDaysToBirthday;
    }

    public void setmDaysToBirthday(int mDaysToBirthday) {
        this.mDaysToBirthday = mDaysToBirthday;
    }

    public String getmLeaveRemaining() {
        return mLeaveRemaining;
    }

    public void setmLeaveRemaining(String mLeaveRemaining) {
        this.mLeaveRemaining = mLeaveRemaining;
    }
}

