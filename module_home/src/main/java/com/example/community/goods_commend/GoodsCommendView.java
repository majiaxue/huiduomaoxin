package com.example.community.goods_commend;

import com.example.community.adapter.CommendTitleAdapter;
import com.example.mvp.IView;

import java.util.List;

public interface GoodsCommendView extends IView {

    void loadTitle(CommendTitleAdapter adapter);
}
