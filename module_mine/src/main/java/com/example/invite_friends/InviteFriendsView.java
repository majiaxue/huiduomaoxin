package com.example.invite_friends;

import com.example.bean.BannerBean;
import com.example.mvp.IView;

import java.util.List;

public interface InviteFriendsView extends IView {
    void loadBanner(List<BannerBean.RecordsBean> beanList);
}
