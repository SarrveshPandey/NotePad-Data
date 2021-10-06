package com.mobileapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Storage {

    @SerializedName("drive1")
    @Expose
    private Drive1 drive1;

    public Drive1 getDrive1() {
        return drive1;
    }

    public void setDrive1(Drive1 drive1) {
        this.drive1 = drive1;
    }
}
