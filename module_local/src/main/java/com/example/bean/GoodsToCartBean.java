package com.example.bean;

public class GoodsToCartBean {
    private String localSellerId;
    private String localGoodsId;
    private String userCode;
    private String num;

    public GoodsToCartBean(String localSellerId, String localGoodsId, String userCode, String num) {
        this.localSellerId = localSellerId;
        this.localGoodsId = localGoodsId;
        this.userCode = userCode;
        this.num = num;
    }

    public String getLocalSellerId() {
        return localSellerId;
    }

    public void setLocalSellerId(String localSellerId) {
        this.localSellerId = localSellerId;
    }

    public String getLocalGoodsId() {
        return localGoodsId;
    }

    public void setLocalGoodsId(String localGoodsId) {
        this.localGoodsId = localGoodsId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "GoodsToCartBean{" +
                "localSellerId='" + localSellerId + '\'' +
                ", localGoodsId='" + localGoodsId + '\'' +
                ", userCode='" + userCode + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
