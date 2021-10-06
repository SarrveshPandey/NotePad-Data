package com.mobileapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DomainResult {

    @SerializedName("domain_id")
    @Expose
    private String domainId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("client")
    @Expose
    private String client;
    @SerializedName("subclient")
    @Expose
    private Object subclient;

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Object getSubclient() {
        return subclient;
    }

    public void setSubclient(Object subclient) {
        this.subclient = subclient;
    }


}
