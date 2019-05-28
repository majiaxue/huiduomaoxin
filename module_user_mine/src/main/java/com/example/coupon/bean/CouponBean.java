package com.example.coupon.bean;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public class CouponBean {
    private String shopName;
    private int image;
    private String qian;
    private String totalUsageAmount;
    private String validTime;

    public CouponBean(String shopName, int image, String qian, String totalUsageAmount, String validTime) {
        this.shopName = shopName;
        this.image = image;
        this.qian = qian;
        this.totalUsageAmount = totalUsageAmount;
        this.validTime = validTime;
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

    public String getQian() {
        return qian;
    }

    public void setQian(String qian) {
        this.qian = qian;
    }

    public String getTotalUsageAmount() {
        return totalUsageAmount;
    }

    public void setTotalUsageAmount(String totalUsageAmount) {
        this.totalUsageAmount = totalUsageAmount;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }
}
