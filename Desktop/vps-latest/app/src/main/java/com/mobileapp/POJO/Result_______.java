package com.mobileapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result_______ {
    @SerializedName("cores")
    @Expose
    private String cores;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("qty")
    @Expose
    private Integer qty;

    public String getCores() {
        return cores;
    }

    public void setCores(String cores) {
        this.cores = cores;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }



}
