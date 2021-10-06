package com.mobileapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeriveSummaryResult {

    @SerializedName("cpus")
    @Expose
    private Integer cpus;
    @SerializedName("cores")
    @Expose
    private Integer cores;
    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("drives")
    @Expose
    private Integer drives;
    @SerializedName("isipmi")
    @Expose
    private Boolean isipmi;
    @SerializedName("hasraid")
    @Expose
    private Boolean hasraid;
    @SerializedName("ismanaged")
    @Expose
    private Boolean ismanaged;
    @SerializedName("storage")
    @Expose
    private Storage storage;
    @SerializedName("storage_total")
    @Expose
    private Integer storageTotal;
    @SerializedName("storage_total_ssd")
    @Expose
    private Integer storageTotalSsd;
    @SerializedName("storage_total_sata")
    @Expose
    private Integer storageTotalSata;
    @SerializedName("storage_total_ssd_mb")
    @Expose
    private Integer storageTotalSsdMb;
    @SerializedName("storage_total_ssd_gb")
    @Expose
    private Integer storageTotalSsdGb;
    @SerializedName("storage_total_ssd_tb")
    @Expose
    private Float storageTotalSsdTb;
    @SerializedName("storage_total_sata_mb")
    @Expose
    private Integer storageTotalSataMb;
    @SerializedName("storage_total_sata_gb")
    @Expose
    private Integer storageTotalSataGb;
    @SerializedName("storage_total_sata_tb")
    @Expose
    private Integer storageTotalSataTb;
    @SerializedName("storage_total_mb")
    @Expose
    private Integer storageTotalMb;
    @SerializedName("storage_total_gb")
    @Expose
    private Integer storageTotalGb;
    @SerializedName("storage_total_tb")
    @Expose
    private Float storageTotalTb;
    @SerializedName("hascontrolpanel")
    @Expose
    private Boolean hascontrolpanel;
    @SerializedName("cpu1")
    @Expose
    private Cpu1 cpu1;
    @SerializedName("cpu2")
    @Expose
    private Cpu2 cpu2;
    @SerializedName("ram")
    @Expose
    private Ram ram;
    @SerializedName("uplink")
    @Expose
    private Uplink uplink;
    @SerializedName("ipv4")
    @Expose
    private Ipv4 ipv4;
    @SerializedName("ipv6")
    @Expose
    private Ipv6 ipv6;
    @SerializedName("bandwidth")
    @Expose
    private Bandwidth bandwidth;
    @SerializedName("location")
    @Expose
    private Locationn location;
    @SerializedName("service_type_std")
    @Expose
    private String serviceTypeStd;
    @SerializedName("service_type")
    @Expose
    private String serviceType;
    @SerializedName("hypervisor")
    @Expose
    private String hypervisor;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("orderid")
    @Expose
    private String orderid;
    @SerializedName("packageid")
    @Expose
    private String packageid;
    @SerializedName("regdate")
    @Expose
    private String regdate;
    @SerializedName("service_status")
    @Expose
    private String serviceStatus;
    @SerializedName("billingcycle")
    @Expose
    private String billingcycle;
    @SerializedName("nextduedate")
    @Expose
    private String nextduedate;
    @SerializedName("nextinvoicedate")
    @Expose
    private String nextinvoicedate;
    @SerializedName("status")
    @Expose
    private ServiceSummaryLocationStatus status;
    @SerializedName("mainip")
    @Expose
    private Mainip mainip;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("bwPercent")
    @Expose
    private Integer bwPercent;

    public Integer getCpus() {
        return cpus;
    }

    public void setCpus(Integer cpus) {
        this.cpus = cpus;
    }

    public Integer getCores() {
        return cores;
    }

    public void setCores(Integer cores) {
        this.cores = cores;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getDrives() {
        return drives;
    }

    public void setDrives(Integer drives) {
        this.drives = drives;
    }

    public Boolean getIsipmi() {
        return isipmi;
    }

    public void setIsipmi(Boolean isipmi) {
        this.isipmi = isipmi;
    }

    public Boolean getHasraid() {
        return hasraid;
    }

    public void setHasraid(Boolean hasraid) {
        this.hasraid = hasraid;
    }

    public Boolean getIsmanaged() {
        return ismanaged;
    }

    public void setIsmanaged(Boolean ismanaged) {
        this.ismanaged = ismanaged;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Integer getStorageTotal() {
        return storageTotal;
    }

    public void setStorageTotal(Integer storageTotal) {
        this.storageTotal = storageTotal;
    }

    public Integer getStorageTotalSsd() {
        return storageTotalSsd;
    }

    public void setStorageTotalSsd(Integer storageTotalSsd) {
        this.storageTotalSsd = storageTotalSsd;
    }

    public Integer getStorageTotalSata() {
        return storageTotalSata;
    }

    public void setStorageTotalSata(Integer storageTotalSata) {
        this.storageTotalSata = storageTotalSata;
    }

    public Integer getStorageTotalSsdMb() {
        return storageTotalSsdMb;
    }

    public void setStorageTotalSsdMb(Integer storageTotalSsdMb) {
        this.storageTotalSsdMb = storageTotalSsdMb;
    }

    public Integer getStorageTotalSsdGb() {
        return storageTotalSsdGb;
    }

    public void setStorageTotalSsdGb(Integer storageTotalSsdGb) {
        this.storageTotalSsdGb = storageTotalSsdGb;
    }

    public Float getStorageTotalSsdTb() {
        return storageTotalSsdTb;
    }

    public void setStorageTotalSsdTb(Float storageTotalSsdTb) {
        this.storageTotalSsdTb = storageTotalSsdTb;
    }

    public Integer getStorageTotalSataMb() {
        return storageTotalSataMb;
    }

    public void setStorageTotalSataMb(Integer storageTotalSataMb) {
        this.storageTotalSataMb = storageTotalSataMb;
    }

    public Integer getStorageTotalSataGb() {
        return storageTotalSataGb;
    }

    public void setStorageTotalSataGb(Integer storageTotalSataGb) {
        this.storageTotalSataGb = storageTotalSataGb;
    }

    public Integer getStorageTotalSataTb() {
        return storageTotalSataTb;
    }

    public void setStorageTotalSataTb(Integer storageTotalSataTb) {
        this.storageTotalSataTb = storageTotalSataTb;
    }

    public Integer getStorageTotalMb() {
        return storageTotalMb;
    }

    public void setStorageTotalMb(Integer storageTotalMb) {
        this.storageTotalMb = storageTotalMb;
    }

    public Integer getStorageTotalGb() {
        return storageTotalGb;
    }

    public void setStorageTotalGb(Integer storageTotalGb) {
        this.storageTotalGb = storageTotalGb;
    }

    public Float getStorageTotalTb() {
        return storageTotalTb;
    }

    public void setStorageTotalTb(Float storageTotalTb) {
        this.storageTotalTb = storageTotalTb;
    }

    public Boolean getHascontrolpanel() {
        return hascontrolpanel;
    }

    public void setHascontrolpanel(Boolean hascontrolpanel) {
        this.hascontrolpanel = hascontrolpanel;
    }

    public Cpu1 getCpu1() {
        return cpu1;
    }

    public void setCpu1(Cpu1 cpu1) {
        this.cpu1 = cpu1;
    }

    public Cpu2 getCpu2() {
        return cpu2;
    }

    public void setCpu2(Cpu2 cpu2) {
        this.cpu2 = cpu2;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public Uplink getUplink() {
        return uplink;
    }

    public void setUplink(Uplink uplink) {
        this.uplink = uplink;
    }

    public Ipv4 getIpv4() {
        return ipv4;
    }

    public void setIpv4(Ipv4 ipv4) {
        this.ipv4 = ipv4;
    }

    public Ipv6 getIpv6() {
        return ipv6;
    }

    public void setIpv6(Ipv6 ipv6) {
        this.ipv6 = ipv6;
    }

    public Bandwidth getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Bandwidth bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Locationn getLocation() {
        return location;
    }

    public void setLocation(Locationn location) {
        this.location = location;
    }

    public String getServiceTypeStd() {
        return serviceTypeStd;
    }

    public void setServiceTypeStd(String serviceTypeStd) {
        this.serviceTypeStd = serviceTypeStd;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getHypervisor() {
        return hypervisor;
    }

    public void setHypervisor(String hypervisor) {
        this.hypervisor = hypervisor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getPackageid() {
        return packageid;
    }

    public void setPackageid(String packageid) {
        this.packageid = packageid;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getBillingcycle() {
        return billingcycle;
    }

    public void setBillingcycle(String billingcycle) {
        this.billingcycle = billingcycle;
    }

    public String getNextduedate() {
        return nextduedate;
    }

    public void setNextduedate(String nextduedate) {
        this.nextduedate = nextduedate;
    }

    public String getNextinvoicedate() {
        return nextinvoicedate;
    }

    public void setNextinvoicedate(String nextinvoicedate) {
        this.nextinvoicedate = nextinvoicedate;
    }

    public ServiceSummaryLocationStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceSummaryLocationStatus status) {
        this.status = status;
    }

    public Mainip getMainip() {
        return mainip;
    }

    public void setMainip(Mainip mainip) {
        this.mainip = mainip;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getBwPercent() {
        return bwPercent;
    }

    public void setBwPercent(Integer bwPercent) {
        this.bwPercent = bwPercent;
    }

}
