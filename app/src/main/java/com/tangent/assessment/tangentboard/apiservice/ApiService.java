package com.tangent.assessment.tangentboard.apiservice;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Rudi Stapelfeldt on 2017/10/18.
 */

public interface ApiService {

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST(Request.AUTH_TOKEN)
    Observable getAuthToken(@Field("username") String username, @Field("password") String password);
}
