package com.example.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

public class ZBannerBean extends SimpleBannerInfo {
    private int image;

    public ZBannerBean(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public Object getXBannerUrl() {
        return image;
    }
}
