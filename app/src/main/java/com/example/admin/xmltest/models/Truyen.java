package com.example.admin.xmltest.models;

import java.util.List;

/**
 * Created by HTML5 on 18/11/2017.
 */

public class Truyen {
    String name;
    String description;
    String category;
    List<Chuong> chuongs;

    public Truyen(){

    }

    public Truyen(String name, String description, String category, List<Chuong> chuongs) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.chuongs = chuongs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Chuong> getChuongs() {
        return chuongs;
    }

    public void setChuongs(List<Chuong> chuongs) {
        this.chuongs = chuongs;
    }
}
