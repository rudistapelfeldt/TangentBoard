package com.tangent.assessment.tangentboard.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rudi Stapelfeldt on 2017/10/19.
 */

public class StatisticsData {

    @SerializedName("user")
    private User mUser;

    @SerializedName("position")
    private Position mPosition;

    @SerializedName("phone_number")
    private String mPhoneNumber;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("github_user")
    private String mGitHubUser;

    @SerializedName("birth_date")
    private String birthDate;

    @SerializedName("gender")
    private String mGender;

    @SerializedName("race")
    private String mRace;

    @SerializedName("years_worked")
    private int mYearsWorked;

    @SerializedName("age")
    private int mAge;

    @SerializedName("days_to_birthday")
    private int mDaysToBirthday;


    public StatisticsData(User mUser, Position mPosition, String mPhoneNumber, String mEmail, String mGitHubUser, String birthDate, String mGender, String mRace, int mYearsWorked, int mAge, int mDaysToBirthday) {
        this.mUser = mUser;
        this.mPosition = mPosition;
        this.mPhoneNumber = mPhoneNumber;
        this.mEmail = mEmail;
        this.mGitHubUser = mGitHubUser;
        this.birthDate = birthDate;
        this.mGender = mGender;
        this.mRace = mRace;
        this.mYearsWorked = mYearsWorked;
        this.mAge = mAge;
        this.mDaysToBirthday = mDaysToBirthday;
    }

    public StatisticsData(){
        
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

    public String getmGitHubUser() {
        return mGitHubUser;
    }

    public void setmGitHubUser(String mGitHubUser) {
        this.mGitHubUser = mGitHubUser;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

    public int getmDaysToBirthday() {
        return mDaysToBirthday;
    }

    public void setmDaysToBirthday(int mDaysToBirthday) {
        this.mDaysToBirthday = mDaysToBirthday;
    }
}
