package com.example.bean;

public class ChooseInsideBean {
    private Object content;
    private String picUrl;
    private double price;
    private boolean isCheck;

    public ChooseInsideBean(Object content, String picUrl, boolean isCheck, double price) {
        this.content = content;
        this.picUrl = picUrl;
        this.isCheck = isCheck;
        this.price = price;
    }

    public ChooseInsideBean(Object content, boolean isCheck, double price) {
        this.content = content;
        this.isCheck = isCheck;
        this.price = price;
    }

    public ChooseInsideBean(Object content, String picUrl, boolean isCheck) {
        this.content = content;
        this.picUrl = picUrl;
        this.isCheck = isCheck;
    }

    public ChooseInsideBean(Object content, boolean isCheck) {
        this.content = content;
        this.isCheck = isCheck;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "ChooseInsideBean{" +
                "content=" + content +
                ", picUrl='" + picUrl + '\'' +
                ", price=" + price +
                ", isCheck=" + isCheck +
                '}';
    }
}
