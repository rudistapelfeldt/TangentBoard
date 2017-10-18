package com.tangent.assessment.tangentboard.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rudi Stapelfeldt on 2017/10/18.
 * Name: Login.java
 * Description: A model to retrieve the response from the login api call
 */

public class LoginData {

    @SerializedName("token")
    private String token;

    public LoginData(){

    }

    public LoginData(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
