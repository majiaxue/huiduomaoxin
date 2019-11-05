package com.example.bean;

public class LocalCartBean {

    /**
     * id : 30
     * localSellerId : 95
     * localGoodsId : 6
     * userCode : 330949981936549888
     * num : 1
     * createTime : 2019-09-18 13:54:56
     * updateTime : null
     */

    private String id;
    private String localSellerId;
    private String localGoodsId;
    private String userCode;
    private int num;
    private String createTime;
    private String updateTime;
    /**
     * 商品名
     */
    private String localGoodsName;

    /**
     * 规格
     */
    private String localGoodsSpecification;

    /**
     * 价格
     */
    private double price;

    /**
     * 图片
     */
    private String localGoodsPic;

    public LocalCartBean() {
    }

    public LocalCartBean(String localSellerId, String localGoodsId, String userCode, int num) {
        this.localSellerId = localSellerId;
        this.localGoodsId = localGoodsId;
        this.userCode = userCode;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getLocalGoodsName() {
        return localGoodsName;
    }

    public void setLocalGoodsName(String localGoodsName) {
        this.localGoodsName = localGoodsName;
    }

    public String getLocalGoodsSpecification() {
        return localGoodsSpecification;
    }

    public void setLocalGoodsSpecification(String localGoodsSpecification) {
        this.localGoodsSpecification = localGoodsSpecification;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocalGoodsPic() {
        return localGoodsPic;
    }

    public void setLocalGoodsPic(String localGoodsPic) {
        this.localGoodsPic = localGoodsPic;
    }

    @Override
    public String toString() {
        return "LocalCartBean{" +
                "id='" + id + '\'' +
                ", localSellerId='" + localSellerId + '\'' +
                ", localGoodsId='" + localGoodsId + '\'' +
                ", userCode='" + userCode + '\'' +
                ", num='" + num + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", localGoodsName='" + localGoodsName + '\'' +
                ", localGoodsSpecification='" + localGoodsSpecification + '\'' +
                ", price=" + price +
                ", localGoodsPic='" + localGoodsPic + '\'' +
                '}';
    }
}
