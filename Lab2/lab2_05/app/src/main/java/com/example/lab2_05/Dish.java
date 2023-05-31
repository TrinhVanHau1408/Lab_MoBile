package com.example.lab2_05;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class Dish{

    private String name;
    private Thumbnail thumbNail;
    private Boolean isPromotion;

    public Dish () {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Thumbnail getThumbnail() {
        return thumbNail;
    }

    public void setThumbNail(Thumbnail thumbNail) {
        this.thumbNail = thumbNail;
    }

    public Boolean getPromotion() {
        return isPromotion;
    }

    public void setPromotion(Boolean promotion) {
        isPromotion = promotion;
    }

}
