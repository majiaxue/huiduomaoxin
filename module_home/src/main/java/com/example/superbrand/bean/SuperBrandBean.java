package com.example.superbrand.bean;

/**
 * Created by cuihaohao on 2019/6/5
 * Describe:
 */
public class SuperBrandBean {

    private int image;
    private String name;
    private String rebate;

    public SuperBrandBean(int image, String name, String rebate) {
        this.image = image;
        this.name = name;
        this.rebate = rebate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
