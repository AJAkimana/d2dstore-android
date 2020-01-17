package com.example.d2dstore.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.example.d2dstore.models.*;

import java.util.Map;

public interface StoreService {

    @POST("user/login")
    Call<LoginResponse> login(@Body AuthModel authModel);

    @GET("store/{display_type}")
    Call<ServerResponse<Store>> getOverviews(@Path("display_type") String displayType);

    @FormUrlEncoded
    @POST("{transaction_type}")
    Call<ServerResponse<Store>> recordStore(@Path("transaction_type") String transactionType,
                                            @FieldMap Map<String, String> dataRecords);
}
