package com.mobileapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Auth_Result {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("companyname")
    @Expose
    private String companyname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("postcode")
    @Expose
    private String postcode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("phonenumber")
    @Expose
    private String phonenumber;
    @SerializedName("tax_id")
    @Expose
    private String taxId;
    @SerializedName("subaccount")
    @Expose
    private String subaccount;
    @SerializedName("permissions")
    @Expose
    private String permissions;
    @SerializedName("domainemails")
    @Expose
    private String domainemails;
    @SerializedName("generalemails")
    @Expose
    private String generalemails;
    @SerializedName("invoiceemails")
    @Expose
    private String invoiceemails;
    @SerializedName("productemails")
    @Expose
    private String productemails;
    @SerializedName("supportemails")
    @Expose
    private String supportemails;
    @SerializedName("affiliateemails")
    @Expose
    private String affiliateemails;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("auth_pin")
    @Expose
    private Object authPin;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getSubaccount() {
        return subaccount;
    }

    public void setSubaccount(String subaccount) {
        this.subaccount = subaccount;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getDomainemails() {
        return domainemails;
    }

    public void setDomainemails(String domainemails) {
        this.domainemails = domainemails;
    }

    public String getGeneralemails() {
        return generalemails;
    }

    public void setGeneralemails(String generalemails) {
        this.generalemails = generalemails;
    }

    public String getInvoiceemails() {
        return invoiceemails;
    }

    public void setInvoiceemails(String invoiceemails) {
        this.invoiceemails = invoiceemails;
    }

    public String getProductemails() {
        return productemails;
    }

    public void setProductemails(String productemails) {
        this.productemails = productemails;
    }

    public String getSupportemails() {
        return supportemails;
    }

    public void setSupportemails(String supportemails) {
        this.supportemails = supportemails;
    }

    public String getAffiliateemails() {
        return affiliateemails;
    }

    public void setAffiliateemails(String affiliateemails) {
        this.affiliateemails = affiliateemails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getAuthPin() {
        return authPin;
    }

    public void setAuthPin(Object authPin) {
        this.authPin = authPin;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}


