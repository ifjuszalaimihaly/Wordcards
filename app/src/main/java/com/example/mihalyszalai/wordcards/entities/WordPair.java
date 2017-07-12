package com.example.mihalyszalai.wordcards.entities;

import java.io.Serializable;

/**
 * Created by Mihaly Szalai on 2016. 12. 04..
 */

public class WordPair implements Serializable{

    private String nativelanguage;
    private String foreignlanguage;

    public WordPair(String nativelanguage, String foreignlanguage) {
        this.nativelanguage = nativelanguage;
        this.foreignlanguage = foreignlanguage;
    }

    public String getNativelanguage() {
        return nativelanguage;
    }

    public void setNativelanguage(String nativelanguage) {
        this.nativelanguage = nativelanguage;
    }

    public String getForeignlanguage() {
        return foreignlanguage;
    }

    public void setForeignlanguage(String foreignlanguage) {
        this.foreignlanguage = foreignlanguage;
    }
}
