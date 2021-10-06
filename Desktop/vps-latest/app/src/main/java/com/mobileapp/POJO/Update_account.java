package com.mobileapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Update_account {

    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("companyname")
    @Expose
    private Object companyname;
    @SerializedName("phonenumber")
    @Expose
    private Object phonenumber;
    @SerializedName("subaccount")
    @Expose
    private Boolean subaccount;
    @SerializedName("domainemails")
    @Expose
    private Integer domainemails;
    @SerializedName("generalemails")
    @Expose
    private Object generalemails;
    @SerializedName("invoiceemails")
    @Expose
    private Object invoiceemails;
    @SerializedName("productemails")
    @Expose
    private Productemails productemails;
    @SerializedName("supportemails")
    @Expose
    private Object supportemails;
    @SerializedName("affiliateemails")
    @Expose
    private Integer affiliateemails;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("auth_pin")
    @Expose
    private Object authPin;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Object getCompanyname() {
        return companyname;
    }

    public void setCompanyname(Object companyname) {
        this.companyname = companyname;
    }

    public Object getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Object phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Boolean getSubaccount() {
        return subaccount;
    }

    public void setSubaccount(Boolean subaccount) {
        this.subaccount = subaccount;
    }

    public Integer getDomainemails() {
        return domainemails;
    }

    public void setDomainemails(Integer domainemails) {
        this.domainemails = domainemails;
    }

    public Object getGeneralemails() {
        return generalemails;
    }

    public void setGeneralemails(Object generalemails) {
        this.generalemails = generalemails;
    }

    public Object getInvoiceemails() {
        return invoiceemails;
    }

    public void setInvoiceemails(Object invoiceemails) {
        this.invoiceemails = invoiceemails;
    }

    public Productemails getProductemails() {
        return productemails;
    }

    public void setProductemails(Productemails productemails) {
        this.productemails = productemails;
    }

    public Object getSupportemails() {
        return supportemails;
    }

    public void setSupportemails(Object supportemails) {
        this.supportemails = supportemails;
    }

    public Integer getAffiliateemails() {
        return affiliateemails;
    }

    public void setAffiliateemails(Integer affiliateemails) {
        this.affiliateemails = affiliateemails;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getAuthPin() {
        return authPin;
    }

    public void setAuthPin(Object authPin) {
        this.authPin = authPin;
    }
    public class Productemails {

        @SerializedName("parameter_id")
        @Expose
        private String parameterId;
        @SerializedName("parameter_endpoint_id")
        @Expose
        private String parameterEndpointId;
        @SerializedName("parameter_type")
        @Expose
        private String parameterType;
        @SerializedName("parameter_title")
        @Expose
        private String parameterTitle;
        @SerializedName("parameter_description")
        @Expose
        private String parameterDescription;
        @SerializedName("parameter_required")
        @Expose
        private String parameterRequired;
        @SerializedName("parameter_regex")
        @Expose
        private String parameterRegex;
        @SerializedName("parameter_regex_required")
        @Expose
        private String parameterRegexRequired;
        @SerializedName("parameter_enum_options")
        @Expose
        private String parameterEnumOptions;
        @SerializedName("parameter_sub_function")
        @Expose
        private Object parameterSubFunction;
        @SerializedName("parameter_sub_function_required")
        @Expose
        private String parameterSubFunctionRequired;

        public String getParameterId() {
            return parameterId;
        }

        public void setParameterId(String parameterId) {
            this.parameterId = parameterId;
        }

        public String getParameterEndpointId() {
            return parameterEndpointId;
        }

        public void setParameterEndpointId(String parameterEndpointId) {
            this.parameterEndpointId = parameterEndpointId;
        }

        public String getParameterType() {
            return parameterType;
        }

        public void setParameterType(String parameterType) {
            this.parameterType = parameterType;
        }

        public String getParameterTitle() {
            return parameterTitle;
        }

        public void setParameterTitle(String parameterTitle) {
            this.parameterTitle = parameterTitle;
        }

        public String getParameterDescription() {
            return parameterDescription;
        }

        public void setParameterDescription(String parameterDescription) {
            this.parameterDescription = parameterDescription;
        }

        public String getParameterRequired() {
            return parameterRequired;
        }

        public void setParameterRequired(String parameterRequired) {
            this.parameterRequired = parameterRequired;
        }

        public String getParameterRegex() {
            return parameterRegex;
        }

        public void setParameterRegex(String parameterRegex) {
            this.parameterRegex = parameterRegex;
        }

        public String getParameterRegexRequired() {
            return parameterRegexRequired;
        }

        public void setParameterRegexRequired(String parameterRegexRequired) {
            this.parameterRegexRequired = parameterRegexRequired;
        }

        public String getParameterEnumOptions() {
            return parameterEnumOptions;
        }

        public void setParameterEnumOptions(String parameterEnumOptions) {
            this.parameterEnumOptions = parameterEnumOptions;
        }

        public Object getParameterSubFunction() {
            return parameterSubFunction;
        }

        public void setParameterSubFunction(Object parameterSubFunction) {
            this.parameterSubFunction = parameterSubFunction;
        }

        public String getParameterSubFunctionRequired() {
            return parameterSubFunctionRequired;
        }

        public void setParameterSubFunctionRequired(String parameterSubFunctionRequired) {
            this.parameterSubFunctionRequired = parameterSubFunctionRequired;
        }

    }

}


