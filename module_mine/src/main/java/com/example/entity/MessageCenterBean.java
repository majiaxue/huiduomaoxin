package com.example.entity;

public class MessageCenterBean {
    private String flag;
    private String title;
    private String messageType;
    private String status;
    private String content;
    private String express;
    private String expressNum;
    private String imgUrl;

    public MessageCenterBean(String flag, String title, String messageType, String status, String content, String express, String expressNum, String imgUrl) {
        this.flag = flag;
        this.title = title;
        this.messageType = messageType;
        this.status = status;
        this.content = content;
        this.express = express;
        this.expressNum = expressNum;
        this.imgUrl = imgUrl;
    }

    public MessageCenterBean(String flag, String title, String messageType, String status, String content) {
        this.flag = flag;
        this.title = title;
        this.messageType = messageType;
        this.status = status;
        this.content = content;
    }

    public String getFlag() {
        return flag;
    }

    public String getTitle() {
        return title;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public String getExpress() {
        return express;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public String toString() {
        return "MessageCenterBean{" +
                "flag='" + flag + '\'' +
                ", title='" + title + '\'' +
                ", messageType='" + messageType + '\'' +
                ", status='" + status + '\'' +
                ", content='" + content + '\'' +
                ", express='" + express + '\'' +
                ", expressNum='" + expressNum + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
