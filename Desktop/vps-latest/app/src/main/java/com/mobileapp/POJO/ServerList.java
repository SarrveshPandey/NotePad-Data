package com.mobileapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServerList {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("results")
    @Expose
    private Integer results;
    @SerializedName("result")
    @Expose
    private List<ServerListResult> result = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public List<ServerListResult> getResult() {
        return result;
    }

    public void setResult(List<ServerListResult> result) {
        this.result = result;
    }
}
