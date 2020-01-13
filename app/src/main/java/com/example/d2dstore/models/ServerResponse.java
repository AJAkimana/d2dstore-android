package com.example.d2dstore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServerResponse<dataModel> {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("stores")
    @Expose
    private List<dataModel> list = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<dataModel> getList() {
        return list;
    }

    public void setList(List<dataModel> list) {
        this.list = list;
    }
}
