package com.example.admin.xmltest.Math;



public class ToanDemIdenX {
    private String id;
    private String link;
    private String question;
    private String result;
    private  int status ;

    public ToanDemIdenX() {
    }

    public ToanDemIdenX(String id, String link, String question, String result) {
        this.id = id;
        this.link = link;
        this.question = question;
        this.result = result;
        this.status=0;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
