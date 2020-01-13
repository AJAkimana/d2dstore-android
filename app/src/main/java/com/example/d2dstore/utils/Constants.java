package com.example.d2dstore.utils;

import org.json.JSONException;
import org.json.JSONObject;

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

    public static String getServerError(String jsonFromServer){
        try {
            JSONObject jsonObject = new JSONObject(jsonFromServer);
            String errorMessage = jsonObject.getString("error");

            return errorMessage;
        } catch (JSONException e) {

            return UNKNOWN_ERROR;
        }
    }
}
