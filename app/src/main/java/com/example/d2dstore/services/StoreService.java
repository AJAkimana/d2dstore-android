package com.example.d2dstore.services;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.example.d2dstore.models.*;
import com.example.d2dstore.pojos.LoginResponse;
import com.example.d2dstore.pojos.OverViewResponse;
import com.example.d2dstore.pojos.Overview;

import java.util.List;

public interface StoreService {

    @POST("user/login")
    Call<LoginResponse> login(@Body AuthModel authModel);

    @GET("store/{display_type}")
    Call<OverViewResponse> getOverviews(@Path("display_type") String displayType);
}
