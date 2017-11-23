package com.example.admin.xmltest.English;

/**
 * Created by USER on 11/23/2017.
 */

public class Number {
    private String num;
    private String content;
    private String spell;

    public Number() {
    }

    public Number(String num, String content, String spell) {
        this.num = num;
        this.content = content;
        this.spell = spell;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }
}
