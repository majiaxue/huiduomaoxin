package com.example.mineorder.stayappraise.bean;

/**
 * Created by cuihaohao on 2019/5/30
 * Describe:
 */
public class StayAppraiseParentBean {
    private String shopName;
    private String status;

    public StayAppraiseParentBean(String shopName, String status) {
        this.shopName = shopName;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
