package com.example.shippingaddress.bean;

/**
 * Created by cuihaohao on 2019/5/23
 * Describe:
 */
public class ShippingAddressBean {
    private String name;
    private String phone;
    private String site;
    private boolean isCheck;

    public ShippingAddressBean(String name, String phone, String site, boolean isCheck) {
        this.name = name;
        this.phone = phone;
        this.site = site;
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
