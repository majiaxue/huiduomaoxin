package com.example.community.goods_commend;

import com.example.community.adapter.CommendTitleAdapter;
import com.example.community.adapter.GoodsCommendAdapter;
import com.example.mvp.IView;

public interface GoodsCommendView extends IView {

    void loadTitle(CommendTitleAdapter adapter);

    void loadContent(GoodsCommendAdapter adapter);

    void changeType();

    void loadFinish();
}
