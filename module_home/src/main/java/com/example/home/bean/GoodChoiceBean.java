package com.example.home.bean;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class GoodChoiceBean {
    private int image;
    private String name;
    private String preferential_price;
    private String original_price;

    public GoodChoiceBean(int image, String name, String preferential_price, String original_price) {
        this.image = image;
        this.name = name;
        this.preferential_price = preferential_price;
        this.original_price = original_price;
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

    public String getPreferential_price() {
        return preferential_price;
    }

    public void setPreferential_price(String preferential_price) {
        this.preferential_price = preferential_price;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }
}
