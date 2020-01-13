package com.example.d2dstore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {
    @SerializedName("total_input")
    @Expose
    private Integer totalInput;
    @SerializedName("inflow")
    @Expose
    private Integer inflow;
    @SerializedName("outflow")
    @Expose
    private Integer outflow;
    @SerializedName("total_stores")
    @Expose
    private Integer totalStores;
    @SerializedName("month")
    @Expose
    private String month;

    public Integer getTotalInput() {
        return totalInput;
    }

    public void setTotalInput(Integer totalInput) {
        this.totalInput = totalInput;
    }

    public Integer getInflow() {
        return inflow;
    }

    public void setInflow(Integer inflow) {
        this.inflow = inflow;
    }

    public Integer getOutflow() {
        return outflow;
    }

    public void setOutflow(Integer outflow) {
        this.outflow = outflow;
    }

    public Integer getTotalStores() {
        return totalStores;
    }

    public void setTotalStores(Integer totalStores) {
        this.totalStores = totalStores;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
