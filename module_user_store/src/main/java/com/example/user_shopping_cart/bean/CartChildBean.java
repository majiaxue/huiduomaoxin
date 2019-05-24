package com.example.user_shopping_cart.bean;

/**
 * Created by cuihaohao on 2019/5/24
 * Describe:
 */
public class CartChildBean {
    private boolean isCheck;
    private int image;
    private String name;
    private String colour;
    private String size;
    private String price;

    public CartChildBean(boolean isCheck, int image, String name, String colour, String size, String price) {
        this.isCheck = isCheck;
        this.image = image;
        this.name = name;
        this.colour = colour;
        this.size = size;
        this.price = price;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
