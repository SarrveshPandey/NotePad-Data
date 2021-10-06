package com.mobileapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result______ {

    @SerializedName("arpa")
    @Expose
    private String arpa;
    @SerializedName("rdns")
    @Expose
    private String rdns;
    @SerializedName("addr")
    @Expose
    private String addr;
    @SerializedName("smtp")
    @Expose
    private String smtp;
    @SerializedName("abuse")
    @Expose
    private String abuse;
    @SerializedName("long")
    @Expose
    private Integer _long;

    public String getArpa() {
        return arpa;
    }

    public void setArpa(String arpa) {
        this.arpa = arpa;
    }

    public String getRdns() {
        return rdns;
    }

    public void setRdns(String rdns) {
        this.rdns = rdns;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getAbuse() {
        return abuse;
    }

    public void setAbuse(String abuse) {
        this.abuse = abuse;
    }

    public Integer getLong() {
        return _long;
    }

    public void setLong(Integer _long) {
        this._long = _long;
    }


}
