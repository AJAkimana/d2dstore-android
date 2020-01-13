package com.example.d2dstore.backgroundTasks.controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d2dstore.adapters.HomeAdapter;
import com.example.d2dstore.models.OverViewResponse;
import com.example.d2dstore.services.StoreService;
import com.example.d2dstore.utils.Constants;
import com.example.d2dstore.utils.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OverViewController implements Callback<OverViewResponse> {

    Context context;
    PreferenceManager preferenceManager;
    HomeAdapter homeAdapter;
    RecyclerView recyclerView;

    public OverViewController(Context context, RecyclerView recyclerView) {
        this.context = context;
        preferenceManager = new PreferenceManager(context);
        this.recyclerView = recyclerView;
    }

    public void start(){

        Gson gson = new GsonBuilder().setLenient().create();
        GsonConverterFactory factory = GsonConverterFactory.create(gson);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("authorization", preferenceManager.getUserInfo().get("token")).build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(factory).client(httpClient.build()).build();


        StoreService storeService = retrofit.create(StoreService.class);

        Call<OverViewResponse> call = storeService.getOverviews("overview");

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<OverViewResponse> call, Response<OverViewResponse> response) {
        if(response.isSuccessful()){
            try {
                List<OverViewResponse.Store> storeList = response.body().getStores();
                homeAdapter = new HomeAdapter(storeList);

                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(layoutManager);

                recyclerView.setAdapter(homeAdapter);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                String errorMessage = Constants.getServerError(response.errorBody().string());
                Log.d("HOME_OVER_FAILURE", errorMessage);
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
            }  catch (IOException e) {

                Toast.makeText(context, Constants.UNKNOWN_ERROR, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailure(Call<OverViewResponse> call, Throwable t) {
        /**
         *
         * Network error
         * */
        Log.d("HOME_FAILURE", t.getMessage());
        Toast.makeText(context, Constants.NO_NETWORK_ERROR, Toast.LENGTH_LONG).show();
    }
}
