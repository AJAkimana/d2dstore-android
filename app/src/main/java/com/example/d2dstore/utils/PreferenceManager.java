package com.example.d2dstore.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.auth0.android.jwt.JWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PreferenceManager {
    Context context;

    public PreferenceManager(Context context) {
        this.context = context;
    }

    public void saveUserDetails(int id,
                                 String names,
                                 String username,
                                 String email,
                                 String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.USER_DETAILS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", id);
        editor.putString("names", names);
        editor.putString("username", username);
        editor.putString("email", email);
        editor.putString("token", token);
        editor.commit();
    }

    public String getEmail() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.USER_DETAILS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("email", "");
    }

    public Map<String, String> getUserInfo(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.USER_DETAILS, Context.MODE_PRIVATE);
        HashMap<String, String> user = new HashMap<>();

        user.put("id", String.valueOf(sharedPreferences.getInt("id", 0)));
        user.put("names", sharedPreferences.getString("names", ""));
        user.put("username", sharedPreferences.getString("username", ""));
        user.put("token", sharedPreferences.getString("token", ""));
        user.put("email", sharedPreferences.getString("email", ""));
        return user;
    }

    public boolean userHasAccess() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.USER_DETAILS, Context.MODE_PRIVATE);
        String userToken = sharedPreferences.getString("token", "");
        boolean hasAccess = false;
        if(!userToken.isEmpty()){
            JWT jwt = new JWT(userToken);
            Date hasExpired = jwt.getExpiresAt();
            hasAccess = hasExpired.after(new Date()) ? true : false;
        }

        return hasAccess;
    }
}
