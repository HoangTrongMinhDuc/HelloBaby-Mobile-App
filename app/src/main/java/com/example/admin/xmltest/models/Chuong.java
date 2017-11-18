package com.example.admin.xmltest.models;

import java.util.List;

/**
 * Created by HTML5 on 18/11/2017.
 */

public class Chuong {
    String titleOfChapter;
    float numOfChapter;
    List<String> listLink;

    public Chuong(){

    }

    public Chuong(String titleOfChapter, float numOfChapter, List<String> listLink) {
        this.titleOfChapter = titleOfChapter;
        this.numOfChapter = numOfChapter;
        this.listLink = listLink;
    }

    public String getTitleOfChapter() {
        return titleOfChapter;
    }

    public void setTitleOfChapter(String titleOfChapter) {
        this.titleOfChapter = titleOfChapter;
    }

    public float getNumOfChapter() {
        return numOfChapter;
    }

    public void setNumOfChapter(float numOfChapter) {
        this.numOfChapter = numOfChapter;
    }

    public List<String> getListLink() {
        return listLink;
    }

    public void setListLink(List<String> listLink) {
        this.listLink = listLink;
    }
}
