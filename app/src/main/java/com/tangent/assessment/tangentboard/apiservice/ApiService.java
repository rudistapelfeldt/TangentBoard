package com.tangent.assessment.tangentboard.apiservice;

import com.tangent.assessment.tangentboard.model.LoginData;
import com.tangent.assessment.tangentboard.model.StatisticsData;
import com.tangent.assessment.tangentboard.model.UserData;

import java.util.List;
import java.util.Map;

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

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @GET(Request.EMPLOYEE_DETAIL)
    Observable<List<StatisticsData>> getEmployees(@Field("race") String race,
                                                  @Field("position") String position,
                                                  @Field("gender") String gender,
                                                  @Field("start_date_range") int startDateRange,
                                                  @Field("birth_date_range") int birthDateRange,
                                                  @Field("user") int userId,
                                                  @Field("email_contains") String emailContains);

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @GET(Request.EMPLOYEE_DETAIL)
    Observable<List<StatisticsData>> getEmployeesMap(Map<String,Object> parameters);
}
