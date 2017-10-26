package com.example.admin.xmltest.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HTML5 on 26/10/2017.
 */

public class Category implements Serializable {
    private String type;
    private String nameType;
    private List<Video> videos;

    public Category(){

    }

    public Category(String type, String nameType) {
        this.type = type;
        this.nameType = nameType;
    }

    public Category(String type, String nameType, List<Video> videos) {
        this.type = type;
        this.nameType = nameType;
        this.videos = videos;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
