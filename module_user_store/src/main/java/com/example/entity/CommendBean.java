package com.example.entity;

public class CommendBean {
    private int imgUrl;
    private String name;
    private String price;
    private String count;
    private String shop;

    public CommendBean(int imgUrl, String name, String price, String count, String shop) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
        this.count = count;
        this.shop = shop;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getCount() {
        return count;
    }

    public String getShop() {
        return shop;
    }

    @Override
    public String toString() {
        return "CommendBean{" +
                "imgUrl=" + imgUrl +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", count='" + count + '\'' +
                ", shop='" + shop + '\'' +
                '}';
    }
}
