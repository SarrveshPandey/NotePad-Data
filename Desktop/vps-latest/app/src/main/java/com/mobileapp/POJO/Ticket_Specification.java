package com.mobileapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket_Specification {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("results")
    @Expose
    private Integer results;
    @SerializedName("result")
    @Expose
    private Ticket_SpecificationResult result;

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

    public Ticket_SpecificationResult getResult() {
        return result;
    }

    public void setResult(Ticket_SpecificationResult result) {
        this.result = result;
    }

}
