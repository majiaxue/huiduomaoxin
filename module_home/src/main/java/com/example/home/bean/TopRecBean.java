package com.example.home.bean;

import android.media.Image;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class TopRecBean {

    String name;
    int image;

    public TopRecBean(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
