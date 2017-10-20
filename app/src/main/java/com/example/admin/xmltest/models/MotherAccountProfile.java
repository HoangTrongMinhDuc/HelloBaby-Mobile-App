package com.example.admin.xmltest.models;

/**
 * Created by admin on 10/20/2017.
 */

public class MotherAccountProfile {
    String userName;
    String realName;
    String phone;
    String sonPhone;
    String id="";

    public MotherAccountProfile(String id, String userName, String realName, String phone, String sonPhone) {
        this.userName = userName;
        this.realName = realName;
        this.phone = phone;
        this.sonPhone = sonPhone;
        this.id = id;
    }

    public MotherAccountProfile() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSonPhone() {
        return sonPhone;
    }

    public void setSonPhone(String sonPhone) {
        this.sonPhone = sonPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
