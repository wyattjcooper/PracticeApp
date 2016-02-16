package com.example.wyattcooper.practiceapp;

/**
 * Created by WyattCooper on 2/15/16.
 */
public class entry {
    private String date;
    private String name;
    private String text;
    public entry() {}
    public entry(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
    public String getName() {
        return name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
