package com.example.d2dstore.services;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import com.example.d2dstore.models.*;
import com.example.d2dstore.pojos.LoginResponse;

public interface StoreService {

    @POST("user/login")
    Call<LoginResponse> login(@Body AuthModel authModel);
}
