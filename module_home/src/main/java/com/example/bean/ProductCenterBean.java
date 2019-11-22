package com.example.bean;

import java.io.Serializable;

public class ProductCenterBean implements Serializable {
    private Long id;
    /**
     * logo
     */
    private String logo;
    /**
     * 标题
     */
    private String title;
    /**
     * 简介
     */
    private String message;
    /**
     * 价格
     */
    private double price;
    /**
     * 详情
     */
    private String info;
    /**
     * 轮播图
     */
    private String pic;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 权重
     */
    private Integer sort;
    /**
     * 状态（0：关闭 1：开启）
     */
    private Integer status;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 联系人
     */
    private String name;
    /**
     * 分类ID
     */
    private Long categoryId;
    /**
     * 测试名
     */
    private String testName;
    /**
     * 测试地址
     */
    private String testAddress;
    /**
     * 测试账号
     */
    private String testAccount;
    /**
     * 测试密码
     */
    private String testPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestAddress() {
        return testAddress;
    }

    public void setTestAddress(String testAddress) {
        this.testAddress = testAddress;
    }

    public String getTestAccount() {
        return testAccount;
    }

    public void setTestAccount(String testAccount) {
        this.testAccount = testAccount;
    }

    public String getTestPassword() {
        return testPassword;
    }

    public void setTestPassword(String testPassword) {
        this.testPassword = testPassword;
    }

    @Override
    public String toString() {
        return "ProductCenterBean{" +
                "id=" + id +
                ", logo='" + logo + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", price=" + price +
                ", info='" + info + '\'' +
                ", pic='" + pic + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", testName='" + testName + '\'' +
                ", testAddress='" + testAddress + '\'' +
                ", testAccount='" + testAccount + '\'' +
                ", testPassword='" + testPassword + '\'' +
                '}';
    }
}
