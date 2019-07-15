package com.example.community.good_goods;

import com.example.community.adapter.CommendTitleAdapter;
import com.example.community.adapter.GoodsCommendAdapter;
import com.example.mvp.IView;

public interface GoodGoodsView extends IView {
    void loadTitle(CommendTitleAdapter adapter);

    void loadContent(GoodsCommendAdapter adapter);

    void loadFinish();
}
