package com.example.bean;

public class ConfirmOrderInsideBean {
    /**
     * 运费
     */
    private double freightAmount;
    /**
     * 优惠券ID
     */
    private Long couponId;

    /**
     * 优惠券分解金额
     */
    private Long couponAmount;

    private Long sellerId;

    public double getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(double freightAmount) {
        this.freightAmount = freightAmount;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Long couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "OrderRequestItem{" +
                "freightAmount=" + freightAmount +
                ", couponId=" + couponId +
                ", couponAmount=" + couponAmount +
                ", sellerId=" + sellerId +
                '}';
    }
}
