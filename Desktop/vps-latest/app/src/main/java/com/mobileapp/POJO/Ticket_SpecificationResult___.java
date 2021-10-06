package com.mobileapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket_SpecificationResult___ {

    @SerializedName("departmentid")
    @Expose
    private String departmentid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("departmenttype")
    @Expose
    private String departmenttype;
    @SerializedName("departmentapp")
    @Expose
    private String departmentapp;
    @SerializedName("isdefault")
    @Expose
    private String isdefault;
    @SerializedName("displayorder")
    @Expose
    private String displayorder;
    @SerializedName("parentdepartmentid")
    @Expose
    private String parentdepartmentid;
    @SerializedName("uservisibilitycustom")
    @Expose
    private String uservisibilitycustom;

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartmenttype() {
        return departmenttype;
    }

    public void setDepartmenttype(String departmenttype) {
        this.departmenttype = departmenttype;
    }

    public String getDepartmentapp() {
        return departmentapp;
    }

    public void setDepartmentapp(String departmentapp) {
        this.departmentapp = departmentapp;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    public String getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(String displayorder) {
        this.displayorder = displayorder;
    }

    public String getParentdepartmentid() {
        return parentdepartmentid;
    }

    public void setParentdepartmentid(String parentdepartmentid) {
        this.parentdepartmentid = parentdepartmentid;
    }

    public String getUservisibilitycustom() {
        return uservisibilitycustom;
    }

    public void setUservisibilitycustom(String uservisibilitycustom) {
        this.uservisibilitycustom = uservisibilitycustom;
    }

}
