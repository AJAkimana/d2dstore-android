package com.example.d2dstore.utils;

import android.content.Context;

import com.example.d2dstore.models.Store;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Constants {
    public static final int SPLASH_TIME = 3000;
    public static final String USER_DETAILS = "USER_DETAILS";
    public static final String NO_NETWORK_ERROR = "Sorry, check your network";
    public static final String TRY_AGAIN = "Please try again";
    public static final String UNKNOWN_ERROR = "Sorry something goes wrong";
    public static final String BASE_URL = "http://192.168.43.127:5600/api/";

    /**
     * Overview descriptions
     */

    public static final String STORE_TOTAL_ITEMS = "Total items";
    public static final String STORE_INCOME = "Total income";
    public static final String STORE_EXPENSES = "Total expenditure";
    public static final String STORE_BALANCE = "Balance";
    public static List<Store> storeList = new ArrayList<>();

    public static String getServerError(String jsonFromServer){
        try {
            JSONObject jsonObject = new JSONObject(jsonFromServer);
            String errorMessage = jsonObject.getString("error");

            return errorMessage;
        } catch (JSONException e) {

            return UNKNOWN_ERROR;
        }
    }

    public static Retrofit getRetrofit(Context context){
        final PreferenceManager manager = new PreferenceManager(context);

        Gson gson = new GsonBuilder().setLenient().create();
        GsonConverterFactory factory = GsonConverterFactory.create(gson);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("authorization", manager.getUserInfo().get("token")).build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(factory).client(httpClient.build()).build();

        return retrofit;
    }
}
