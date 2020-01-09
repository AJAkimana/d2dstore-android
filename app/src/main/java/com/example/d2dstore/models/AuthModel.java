package com.example.d2dstore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthModel {

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("password")
    @Expose
    public String password;

    public AuthModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
