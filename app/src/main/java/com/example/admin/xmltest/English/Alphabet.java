package com.example.admin.xmltest.English;

/**
 * Created by USER on 11/23/2017.
 */

public class Alphabet {
    private String content;
    private String spell;

    public Alphabet(String content, String spell) {
        this.content = content;
        this.spell = spell;
    }

    public Alphabet() {
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
