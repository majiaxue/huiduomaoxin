package com.example.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderConfirmBean implements Serializable {
    /**
     * 对应的商店Id
     */
    private String sellerId;
    /**
     * 对应的商店名字
     */
    private String sellerName;
    /**
     * 对应的商品skuId
     */
    private String ProductSkuId;
    /**
     * 收货人手机号
     */
    private String receiverPhone;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 区
     */
    private String receiverRegion;
    /**
     * 城市
     */
    private String receiverCity;
    /**
     * 省份
     */
    private String receiverProvince;
    /**
     * 订单收货地址
     */
    private String orderAddress;


    /**
     * 运费
     */
    private BigDecimal freightAmount;

    /**
     * 订单留言
     */
    private String remark;

    /**
     * 运费模板ID
     */
    private Long feightTemplateId;

    /**
     * 优惠券ID
     */
    private String couponId;
    /**
     * 优惠券抵扣金额
     */
    private BigDecimal couponAmount;

    /**
     * 购买数量
     */
    private Integer quantity;


    /**
     * 属性1
     */
    private String sp1;
    /**
     * 属性2
     */
    private String sp2;


    /**
     * 订单来源：0->PC订单；1->app订单
     */
    private Integer sourceType;
    /**
     * 商品图片
     */
    private String pic;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品价格
     */
    private double price;
    /**
     * 库存
     */
    private Long stock;

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getProductSkuId() {
        return ProductSkuId;
    }

    public void setProductSkuId(String productSkuId) {
        ProductSkuId = productSkuId;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverRegion() {
        return receiverRegion;
    }

    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getFeightTemplateId() {
        return feightTemplateId;
    }

    public void setFeightTemplateId(Long feightTemplateId) {
        this.feightTemplateId = feightTemplateId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSp1() {
        return sp1;
    }

    public void setSp1(String sp1) {
        this.sp1 = sp1;
    }

    public String getSp2() {
        return sp2;
    }

    public void setSp2(String sp2) {
        this.sp2 = sp2;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public String toString() {
        return "OrderConfirmBean{" +
                "sellerId='" + sellerId + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", ProductSkuId='" + ProductSkuId + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverRegion='" + receiverRegion + '\'' +
                ", receiverCity='" + receiverCity + '\'' +
                ", receiverProvince='" + receiverProvince + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", freightAmount=" + freightAmount +
                ", remark='" + remark + '\'' +
                ", feightTemplateId=" + feightTemplateId +
                ", couponId='" + couponId + '\'' +
                ", couponAmount=" + couponAmount +
                ", quantity=" + quantity +
                ", sp1='" + sp1 + '\'' +
                ", sp2='" + sp2 + '\'' +
                ", sourceType=" + sourceType +
                ", pic='" + pic + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
