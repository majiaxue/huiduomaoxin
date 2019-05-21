package com.example.entity;

public class GroupFansBean {
    private String imgUrl;
    private String name;
    private String time;

    public GroupFansBean(String imgUrl, String name, String time) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.time = time;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "GroupFansBean{" +
                "imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
