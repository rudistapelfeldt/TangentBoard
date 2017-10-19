package com.tangent.assessment.tangentboard.apiservice;

import com.tangent.assessment.tangentboard.model.EmployeeData;
import com.tangent.assessment.tangentboard.model.LoginData;
import com.tangent.assessment.tangentboard.model.UserData;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    Observable<LoginData> getAuthToken(@Field("username") String username, @Field("password") String password);

    @Headers({"Content-Type: application/json"})
    @GET(Request.USER_DETAILS)
    Observable<UserData> getMyDetails();

    @Headers({"Content-Type: application/json"})
    @GET(Request.EMPLOYEE_DETAIL)
    Observable<EmployeeData> getEmployees();
}
