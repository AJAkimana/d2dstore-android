package com.example.d2dstore.backgroundTasks.controllers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.d2dstore.HomeActivity;
import com.example.d2dstore.models.AuthModel;
import com.example.d2dstore.models.LoginResponse;
import com.example.d2dstore.services.StoreService;
import com.example.d2dstore.utils.Constants;
import com.example.d2dstore.utils.LoginUtils;
import com.example.d2dstore.utils.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginController implements Callback<LoginResponse> {

    String email;
    String password;
    Context context;
    PreferenceManager manager;
    LoginUtils loginUtils;

    public LoginController(Context context, String username, String password) {
        this.email = username;
        this.password = password;
        this.context = context;

        manager = new PreferenceManager(context);
    }

    public void start(){
        Gson gson = new GsonBuilder().setLenient().create();
        GsonConverterFactory factory = GsonConverterFactory.create(gson);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(factory).build();

        StoreService storeService = retrofit.create(StoreService.class);

        AuthModel authModel = new AuthModel(email, password);
        Call<LoginResponse> call = storeService.login(authModel);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        if(response.isSuccessful()) {
            int id = Integer.parseInt(response.body().getUser().get("id"));
            String names = response.body().getUser().get("names");
            String username = response.body().getUser().get("username");
            String email = response.body().getUser().get("email");
            String token = response.body().getUser().get("token");

            manager.saveUserDetails(id, names, username, email, token);

            Toast.makeText(context, "Welcome "+response.body().getUser().get("names"),
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else {
            try {
                String errorMessage = Constants.getServerError(response.errorBody().string());
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
            }  catch (IOException e) {

                Toast.makeText(context, Constants.UNKNOWN_ERROR, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailure(Call<LoginResponse> call, Throwable t) {
        /**
         *
         * Network error
         * */
        Toast.makeText(context, Constants.NO_NETWORK_ERROR, Toast.LENGTH_LONG).show();
    }
}
