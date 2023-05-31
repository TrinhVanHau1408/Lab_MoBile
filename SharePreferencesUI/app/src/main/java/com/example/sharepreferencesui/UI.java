package com.example.sharepreferencesui;

public class UI {

    private String backgroundColor;
    private int textSize;

    public UI(String backgroundColor, int textSize) {
        this.backgroundColor= backgroundColor;
        this.textSize = textSize;
    }
    public String getBackgroundColor() {
        return backgroundColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
}
