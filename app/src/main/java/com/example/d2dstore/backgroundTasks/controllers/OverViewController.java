package com.example.d2dstore.backgroundTasks.controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.example.d2dstore.adapters.HomeAdapter;
import com.example.d2dstore.models.*;
import com.example.d2dstore.services.StoreService;
import com.example.d2dstore.utils.Constants;
import com.example.d2dstore.utils.PreferenceManager;

public class OverViewController implements Callback<ServerResponse<Store>> {

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
        Retrofit retrofit = Constants.getRetrofit(context);

        StoreService storeService = retrofit.create(StoreService.class);

        Call<ServerResponse<Store>> call = storeService.getOverviews("overview");

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ServerResponse<Store>> call, Response<ServerResponse<Store>> response) {
        if(response.isSuccessful()){
            try {
                List<Store> storeList = response.body().getList();
                homeAdapter = new HomeAdapter(storeList);

                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(layoutManager);

                recyclerView.setAdapter(homeAdapter);
                Constants.storeList = storeList;

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
    public void onFailure(Call<ServerResponse<Store>> call, Throwable t) {
        /**
         *
         * Network error
         * */
        Log.d("HOME_FAILURE", t.getMessage());
        Toast.makeText(context, Constants.NO_NETWORK_ERROR, Toast.LENGTH_LONG).show();
    }
}
