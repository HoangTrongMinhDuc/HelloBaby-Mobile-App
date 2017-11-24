package com.example.admin.xmltest.models;

/**
 * Created by admin on 11/23/2017.
 */

public class ButtonNumberCall {
    String linkPhoto;
    String phone;

    public ButtonNumberCall(String linkPhoto, String phone) {
        this.linkPhoto = linkPhoto;
        this.phone = phone;
    }

    public ButtonNumberCall() {
    }

    public String getLinkPhoto() {
        return linkPhoto;
    }

    public void setLinkPhoto(String linkPhoto) {
        this.linkPhoto = linkPhoto;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
