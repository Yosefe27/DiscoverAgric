package com.bluecode.weledger.models;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class MainActivityModel {
    String nameType;
    int imageView;


    public MainActivityModel(String nameType, int imageView) {
        this.nameType = nameType;
        this.imageView = imageView;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }
}
