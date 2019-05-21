package com.example.entity;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class RecBean {
    private int image;
    private String name;
    private String reduce_price;
    private String preferential_price;
    private String original_price;
    private String number;

    public RecBean(int image, String name, String reduce_price, String preferential_price, String original_price, String number) {
        this.image = image;
        this.name = name;
        this.reduce_price = reduce_price;
        this.preferential_price = preferential_price;
        this.original_price = original_price;
        this.number = number;
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

    public String getReduce_price() {
        return reduce_price;
    }

    public void setReduce_price(String reduce_price) {
        this.reduce_price = reduce_price;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
