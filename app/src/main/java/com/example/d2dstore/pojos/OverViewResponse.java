package com.example.d2dstore.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OverViewResponse {
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
    }


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("stores")
    @Expose
    private List<Store> stores = null;

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

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

}
