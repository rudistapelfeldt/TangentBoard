/**
 * Created by Rudi Stapelfeldt on 2016/11/22.
 * Name: RetrofitClient.java
 */

package com.tangent.assessment.tangentboard.apiservice;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.tangent.assessment.tangentboard.database.DatabaseHelper;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String TAG = RetrofitClient.class.getSimpleName();
    private static ApiService mApiService;
    private SharedPreferences mSharedPreferences;
    private Activity mActivity;
    private String mToken;

    private ClearableCookieJar cookieJar;

    public static RetrofitClient getInstance(Activity activity, boolean isAuthenticated) {
        return new RetrofitClient(activity, isAuthenticated);
    }

    public ClearableCookieJar getCookieJar(){
        if (cookieJar == null){
            cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(mActivity));
            return cookieJar;
        }else {
            return cookieJar;
        }
    }

    public RetrofitClient(Activity activity, boolean isAuthenticated) {
        mActivity = activity;
        Retrofit retrofit = null;

        if (isAuthenticated) {
            //get token from database
            Cursor cursor = activity.getContentResolver().query(DatabaseHelper.TOKEN_CONTENT_URI, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0){
                cursor.moveToFirst();
                mToken = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TOKEN));
            }

            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Request.BASE_URL_API)
                    .client(getAuthClient())
                    .build();
        }else{
            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Request.BASE_URL_API)
                    .client(getClient())
                    .build();
        }

        mApiService = retrofit.create(ApiService.class);
    }

    public OkHttpClient getClient() {

        cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(mActivity));
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .cookieJar(cookieJar)
                .build();
        return client;
    }

    public OkHttpClient getAuthClient(){
        CookieHandler cookieHandler = new CookieManager(
                new PersistentCookieStore(mActivity), CookiePolicy.ACCEPT_ALL);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        okhttp3.Request original = chain.request();
                        okhttp3.Request request = original.newBuilder()
                                .addHeader("AUTHORIZATION", "TOKEN " + mToken)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .cookieJar(new JavaNetCookieJar(cookieHandler))
                .build();
        return client;
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
