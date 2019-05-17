package com.example.home.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class TopBannerBean extends SimpleBannerInfo {

    private int url;

    public TopBannerBean(int url) {
        this.url = url;
    }

    @Override
    public Object getXBannerUrl() {
        return url;
    }
}
