package com.example.admin.xmltest.Math;

public class Toanctncbe10 {

    private String id;
    private String question;
    private String result;
    private  int status;

    public Toanctncbe10() {
    }

    public Toanctncbe10(String id, String question, String result) {
        this.id = id;
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
