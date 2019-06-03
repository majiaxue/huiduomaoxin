package com.example.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.io.Serializable;
import java.util.List;

public class BannerBean extends SimpleBannerInfo implements Serializable {
    private int id;
    private String name;
    private int type;
    private String picUrl;
    private Object picBackUrl;
    private Object startTime;
    private Object endTime;
    private int status;
    private Object clickCount;
    private Object orderCount;
    private String url;
    private String note;
    private int sort;
    private Object pid;

    public BannerBean() {
    }

    public BannerBean(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Object getPicBackUrl() {
        return picBackUrl;
    }

    public void setPicBackUrl(Object picBackUrl) {
        this.picBackUrl = picBackUrl;
    }

    public Object getStartTime() {
        return startTime;
    }

    public void setStartTime(Object startTime) {
        this.startTime = startTime;
    }

    public Object getEndTime() {
        return endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getClickCount() {
        return clickCount;
    }

    public void setClickCount(Object clickCount) {
        this.clickCount = clickCount;
    }

    public Object getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Object orderCount) {
        this.orderCount = orderCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Object getPid() {
        return pid;
    }

    public void setPid(Object pid) {
        this.pid = pid;
    }

    @Override
    public String getXBannerUrl() {
        return picUrl;
    }

}
