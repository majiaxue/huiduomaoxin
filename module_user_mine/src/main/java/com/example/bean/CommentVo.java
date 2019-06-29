package com.example.bean;

import java.sql.Time;
import java.util.List;

/**
 * Created by cuihaohao on 2019/6/17
 * Describe:
 */
public class CommentVo {
    /**
     *
     */
    private Long id;
    /**
     * 用户昵称
     */
    private String memberNickName;
    /**
     * 评论用户头像
     */
    private String memberIcon;
    /**
     * 评价星数：0->5
     */
    private Integer star;
    /**
     * 内容
     */
    private String content;
    /**
     * 上传图片地址，以逗号隔开
     */
    private List<String> picList;
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 购买时的商品属性
     */
    private String productAttribute;
    /**
     * 评价的ip
     */
    private String memberIp;
    /**
     * 是否显示
     */
    private int showStatus;
    /**
     * 评论数
     */
    private int collectCount;
    /**
     * 评论浏览数
     */
    private int readCount;
    /**
     * 回复数
     */
    private int replayCount;
    /**
     * 评论时间
     */
    private String createTime;
    /**
     * 商家ID
     */
    private Long sellerId;
    /**
     * 描述星数：0->5
     */
    private int sellerDescribe;
    /**
     * 物流星数：0->5
     */
    private int sellerLogistics;
    /**
     * 服务星数：0->5
     */
    private int sellerServer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public String getMemberIcon() {
        return memberIcon;
    }

    public void setMemberIcon(String memberIcon) {
        this.memberIcon = memberIcon;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAttribute() {
        return productAttribute;
    }

    public void setProductAttribute(String productAttribute) {
        this.productAttribute = productAttribute;
    }

    public String getMemberIp() {
        return memberIp;
    }

    public void setMemberIp(String memberIp) {
        this.memberIp = memberIp;
    }

    public int getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(int showStatus) {
        this.showStatus = showStatus;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getReplayCount() {
        return replayCount;
    }

    public void setReplayCount(int replayCount) {
        this.replayCount = replayCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public int getSellerDescribe() {
        return sellerDescribe;
    }

    public void setSellerDescribe(int sellerDescribe) {
        this.sellerDescribe = sellerDescribe;
    }

    public int getSellerLogistics() {
        return sellerLogistics;
    }

    public void setSellerLogistics(int sellerLogistics) {
        this.sellerLogistics = sellerLogistics;
    }

    public int getSellerServer() {
        return sellerServer;
    }

    public void setSellerServer(int sellerServer) {
        this.sellerServer = sellerServer;
    }
}
