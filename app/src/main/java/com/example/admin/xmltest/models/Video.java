package com.example.admin.xmltest.models;

import java.io.Serializable;

/**
 * Created by HTML5 on 26/10/2017.
 */

public class Video implements Serializable {
    private String id;
    private String title;

    public Video(){

    }

    public Video(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
