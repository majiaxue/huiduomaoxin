package com.example.entity;

import java.util.List;

public class AssessBean {
    private String imgUrl;
    private String name;
    private String content;
    private int xingji;
    private String time;
    private String size;
    private String color;
    private int zanCount;
    private int assessCount;
    private boolean isZan;
    private List<String> imgList;

    private int lookCount;

    public AssessBean(String imgUrl, String name, String content) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.content = content;
    }

    public AssessBean(String imgUrl, String name, String content, int xingji, String time, String size, String color, int zanCount, int assessCount, boolean isZan, List<String> imgList) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.content = content;
        this.xingji = xingji;
        this.time = time;
        this.size = size;
        this.color = color;
        this.zanCount = zanCount;
        this.assessCount = assessCount;
        this.isZan = isZan;
        this.imgList = imgList;
    }

    public AssessBean(String imgUrl, String name, String content, int xingji, String time, String size, String color, int zanCount, int assessCount, boolean isZan, List<String> imgList, int lookCount) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.content = content;
        this.xingji = xingji;
        this.time = time;
        this.size = size;
        this.color = color;
        this.zanCount = zanCount;
        this.assessCount = assessCount;
        this.isZan = isZan;
        this.imgList = imgList;
        this.lookCount = lookCount;
    }

    public boolean isZan() {
        return isZan;
    }

    public void setZan(boolean zan) {
        isZan = zan;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public int getXingji() {
        return xingji;
    }

    public String getTime() {
        return time;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setZanCount(int zanCount) {
        this.zanCount = zanCount;
    }

    public int getZanCount() {
        return zanCount;
    }

    public int getAssessCount() {
        return assessCount;
    }

    @Override
    public String toString() {
        return "AssessBean{" +
                "imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", xingji=" + xingji +
                ", time='" + time + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", zanCount=" + zanCount +
                ", assessCount=" + assessCount +
                ", isZan=" + isZan +
                ", imgList=" + imgList +
                '}';
    }

    public static class AssessInsideAssess {
        private String imgUrl;
        private String name;
        private String content;
        private String time;
        private int insideZanCount;
        private boolean insideIsZan;

        public AssessInsideAssess(String imgUrl, String name, String content, String time, int insideZanCount, boolean insideIsZan) {
            this.imgUrl = imgUrl;
            this.name = name;
            this.content = content;
            this.time = time;
            this.insideZanCount = insideZanCount;
            this.insideIsZan = insideIsZan;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public String getName() {
            return name;
        }

        public String getContent() {
            return content;
        }

        public String getTime() {
            return time;
        }

        public int getInsideZanCount() {
            return insideZanCount;
        }

        public boolean isInsideIsZan() {
            return insideIsZan;
        }

        public void setInsideIsZan(boolean insideIsZan) {
            this.insideIsZan = insideIsZan;
        }

        public void setInsideZanCount(int insideZanCount) {
            this.insideZanCount = insideZanCount;
        }

        @Override
        public String toString() {
            return "AssessInsideAssess{" +
                    "imgUrl='" + imgUrl + '\'' +
                    ", name='" + name + '\'' +
                    ", content='" + content + '\'' +
                    ", time='" + time + '\'' +
                    ", insideZanCount=" + insideZanCount +
                    ", insideIsZan=" + insideIsZan +
                    '}';
        }
    }
}
