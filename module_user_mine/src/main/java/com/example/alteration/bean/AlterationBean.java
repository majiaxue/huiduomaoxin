package com.example.alteration.bean;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class AlterationBean {
    private String shopName;
    private int image;
    private String name;
    private String colour;
    private String size;
    private String count;
    private String alterationType;
    private String alterationStatus;

    public AlterationBean(String shopName, int image, String name, String colour, String size, String count, String alterationType, String alterationStatus) {
        this.shopName = shopName;
        this.image = image;
        this.name = name;
        this.colour = colour;
        this.size = size;
        this.count = count;
        this.alterationType = alterationType;
        this.alterationStatus = alterationStatus;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAlterationType() {
        return alterationType;
    }

    public void setAlterationType(String alterationType) {
        this.alterationType = alterationType;
    }

    public String getAlterationStatus() {
        return alterationStatus;
    }

    public void setAlterationStatus(String alterationStatus) {
        this.alterationStatus = alterationStatus;
    }
}
