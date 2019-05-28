package com.example.mineorder.bean;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class MineOrderBean {

    private String shop;
    private int image;
    private String status;
    private String name;
    private String message;
    private String price;
    private String count;
    private String total;

    public MineOrderBean(String shop, int image, String status, String name, String message, String price, String count, String total) {
        this.shop = shop;
        this.image = image;
        this.status = status;
        this.name = name;
        this.message = message;
        this.price = price;
        this.count = count;
        this.total = total;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
