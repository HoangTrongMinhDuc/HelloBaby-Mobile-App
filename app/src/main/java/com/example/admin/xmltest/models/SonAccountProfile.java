package com.example.admin.xmltest.models;

/**
 * Created by admin on 10/20/2017.
 */

public class SonAccountProfile {
    String userName;
    String realName;
    String phone;
    String id="";

    public SonAccountProfile(String id, String userName, String realName, String phone) {
        this.userName = userName;
        this.realName = realName;
        this.phone = phone;
        this.id = id;
    }

    public SonAccountProfile() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
