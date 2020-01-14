package com.example.d2dstore.backgroundTasks.controllers;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d2dstore.adapters.HomeMonthAdapter;
import com.example.d2dstore.helpers.GridSpaceItemDecoration;
import com.example.d2dstore.models.ServerResponse;
import com.example.d2dstore.models.Store;
import com.example.d2dstore.services.StoreService;
import com.example.d2dstore.utils.Constants;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MontlyController implements Callback<ServerResponse<Store>> {

    Context context;
    HomeMonthAdapter monthAdapter;
    RecyclerView recyclerView;

    public MontlyController(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    public void start(){
        Retrofit retrofit = Constants.getRetrofit(context);

        StoreService storeService = retrofit.create(StoreService.class);

        Call<ServerResponse<Store>> call = storeService.getOverviews("montly");

        call.enqueue(this);
    }
    @Override
    public void onResponse(Call<ServerResponse<Store>> call, Response<ServerResponse<Store>> response) {

        if(response.isSuccessful()){
            try {
                List<Store> storeList = response.body().getList();
                monthAdapter = new HomeMonthAdapter(context, storeList);

                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);
                GridSpaceItemDecoration itemDecoration = new GridSpaceItemDecoration(context, 2, monthAdapter.dpToPx(10), true);
                DefaultItemAnimator itemAnimator = new DefaultItemAnimator();
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.addItemDecoration(itemDecoration);
                recyclerView.setItemAnimator(itemAnimator);

                recyclerView.setAdapter(monthAdapter);

                Constants.montlyStoreList = storeList;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                String errorMessage = Constants.getServerError(response.errorBody().string());
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
        Toast.makeText(context, Constants.NO_NETWORK_ERROR, Toast.LENGTH_LONG).show();
    }
}
