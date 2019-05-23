package com.example.entity;

public class SaleHotBean {
    private int imgUrl;
    private String name;
    private String newPrice;
    private String oldPrice;

    public SaleHotBean(int imgUrl, String name, String newPrice, String oldPrice) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    @Override
    public String toString() {
        return "SaleHotBean{" +
                "imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", newPrice='" + newPrice + '\'' +
                ", oldPrice='" + oldPrice + '\'' +
                '}';
    }
}
