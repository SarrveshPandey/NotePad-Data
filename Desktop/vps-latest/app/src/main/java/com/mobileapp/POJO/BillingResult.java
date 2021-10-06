package com.mobileapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillingResult {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("invoicenum")
    @Expose
    private String invoicenum;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("duedate")
    @Expose
    private String duedate;
    @SerializedName("datepaid")
    @Expose
    private String datepaid;
    @SerializedName("last_capture_attempt")
    @Expose
    private String lastCaptureAttempt;
    @SerializedName("date_refunded")
    @Expose
    private String dateRefunded;
    @SerializedName("date_cancelled")
    @Expose
    private String dateCancelled;
    @SerializedName("subtotal")
    @Expose
    private String subtotal;
    @SerializedName("credit")
    @Expose
    private String credit;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("tax2")
    @Expose
    private String tax2;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("taxrate")
    @Expose
    private String taxrate;
    @SerializedName("taxrate2")
    @Expose
    private String taxrate2;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("paymentmethod")
    @Expose
    private String paymentmethod;
    @SerializedName("paymethodid")
    @Expose
    private Object paymethodid;
    @SerializedName("notes")
    @Expose
    private String notes;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getInvoicenum() {
        return invoicenum;
    }

    public void setInvoicenum(String invoicenum) {
        this.invoicenum = invoicenum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getDatepaid() {
        return datepaid;
    }

    public void setDatepaid(String datepaid) {
        this.datepaid = datepaid;
    }

    public String getLastCaptureAttempt() {
        return lastCaptureAttempt;
    }

    public void setLastCaptureAttempt(String lastCaptureAttempt) {
        this.lastCaptureAttempt = lastCaptureAttempt;
    }

    public String getDateRefunded() {
        return dateRefunded;
    }

    public void setDateRefunded(String dateRefunded) {
        this.dateRefunded = dateRefunded;
    }

    public String getDateCancelled() {
        return dateCancelled;
    }

    public void setDateCancelled(String dateCancelled) {
        this.dateCancelled = dateCancelled;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTax2() {
        return tax2;
    }

    public void setTax2(String tax2) {
        this.tax2 = tax2;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(String taxrate) {
        this.taxrate = taxrate;
    }

    public String getTaxrate2() {
        return taxrate2;
    }

    public void setTaxrate2(String taxrate2) {
        this.taxrate2 = taxrate2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public Object getPaymethodid() {
        return paymethodid;
    }

    public void setPaymethodid(Object paymethodid) {
        this.paymethodid = paymethodid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
