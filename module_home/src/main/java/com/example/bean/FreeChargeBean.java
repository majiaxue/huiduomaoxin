package com.example.bean;

public class FreeChargeBean {

    /**
     * id : 5
     * activityCode : null
     * goodsUrl : https://mobile.yangkeduo.com/duo_coupon_landing.html?goods_id=21507593295&pid=8714264_70974453&customParameters=296548168642854912&cpsSign=CC_190713_8714264_70974453_4ddc65455607c669b72f26dddcc18c17&duoduo_type=2
     * goodsName : 测试
     * goodsPrice : 100
     * goodsBackPrice : 99
     * goodsPic : http://192.168.1.17:9000/goods/de1c2c3c9ec84702ad823a8a0798404e.png
     * startTime : null
     * endTime : 2019-07-13 17:48:38
     * orderNum : 10
     * orderResidueNum : null
     * isOpen : 0
     * platform : 0
     * threshold1 : 新用户参与
     * threshold2 : null
     * threshold3 : null
     * threshold4 : null
     * createTime : 2019-07-13 17:03:55
     * activityType : 0
     */

    private String id;
    private String activityCode;
    private String goodsUrl;
    private String goodsName;
    private String goodsPrice;
    private String goodsOriginalPrice;
    private String goodsBackPrice;
    private String goodsPic;
    private String startTime;
    private String endTime;
    private int orderNum;
    private int orderResidueNum;
    private String isOpen;
    private String platform;
    private String threshold1;
    private String threshold2;
    private String threshold3;
    private String threshold4;
    private String createTime;
    private String activityType;

    public String getGoodsOriginalPrice() {
        return goodsOriginalPrice;
    }

    public void setGoodsOriginalPrice(String goodsOriginalPrice) {
        this.goodsOriginalPrice = goodsOriginalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsBackPrice() {
        return goodsBackPrice;
    }

    public void setGoodsBackPrice(String goodsBackPrice) {
        this.goodsBackPrice = goodsBackPrice;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getOrderResidueNum() {
        return orderResidueNum;
    }

    public void setOrderResidueNum(int orderResidueNum) {
        this.orderResidueNum = orderResidueNum;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getThreshold1() {
        return threshold1;
    }

    public void setThreshold1(String threshold1) {
        this.threshold1 = threshold1;
    }

    public String getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(String threshold2) {
        this.threshold2 = threshold2;
    }

    public String getThreshold3() {
        return threshold3;
    }

    public void setThreshold3(String threshold3) {
        this.threshold3 = threshold3;
    }

    public String getThreshold4() {
        return threshold4;
    }

    public void setThreshold4(String threshold4) {
        this.threshold4 = threshold4;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}
