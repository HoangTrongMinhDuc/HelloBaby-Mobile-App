package com.example.admin.xmltest.Vietnamese.Exercise;

/**
 * Created by PC on 11/25/2017.
 */

public class text {
    private String link;
    private String question;
    private String result;
    private int status ;

    public text(String link, String question, String result) {
        this.link = link;
        this.question = question;
        this.result = result;
        this.status = 0;
    }

    public text() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {

        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

