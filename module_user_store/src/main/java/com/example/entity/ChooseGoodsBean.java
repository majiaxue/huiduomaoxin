package com.example.entity;

import java.util.List;

public class ChooseGoodsBean {
    private String imgUrl;
    private String name;
    private List<GoodsSize> size;
    private int img;

    public ChooseGoodsBean(String name, List<GoodsSize> size, int img) {
        this.name = name;
        this.size = size;
        this.img = img;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public List<GoodsSize> getSize() {
        return size;
    }

    public int getImg() {
        return img;
    }

    @Override
    public String toString() {
        return "ChooseGoodsBean{" +
                "imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", img=" + img +
                '}';
    }

    public static class GoodsSize {
        private String size;
        private int count;

        public GoodsSize(String size, int count) {
            this.size = size;
            this.count = count;
        }

        public String getSize() {
            return size;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "GoodsSize{" +
                    "size='" + size + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
