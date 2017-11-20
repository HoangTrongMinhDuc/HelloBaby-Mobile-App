package com.example.admin.xmltest.Education;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.xmltest.R;

public class Toanctncbe10 {

    private String id;
    private String question;
    private String result;

    public Toanctncbe10() {
    }

    public Toanctncbe10(String id, String question, String result) {
        this.id = id;
        this.question = question;
        this.result = result;
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

}
