package com.tangent.assessment.tangentboard.apiservice;

import com.tangent.assessment.tangentboard.model.LoginData;
import com.tangent.assessment.tangentboard.model.MyEmployeeData;
import com.tangent.assessment.tangentboard.model.StatisticsData;
import com.tangent.assessment.tangentboard.model.UserData;

import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
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

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @GET(Request.EMPLOYEE_DETAIL)
    Observable<List<StatisticsData>> getEmployees();

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @GET(Request.USER_EMPLOYEE_PROFILE)
    Observable<MyEmployeeData> getEmployeesMe();

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @GET(Request.EMPLOYEE_DETAIL)
    Observable<List<StatisticsData>> getEmployeesMap(@QueryMap Map<String,Object> parameters);
}
