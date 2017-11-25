package com.example.admin.xmltest.Vietnamese.Vidu;

/**
 * Created by PC on 11/25/2017.
 */

public class Image {
    private String id;
    private String link;

    public Image() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Image(String id, String link) {
        this.id = id;
        this.link = link;

    }
}
