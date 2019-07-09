package com.example.community.goods_commend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.TitleBean;
import com.example.community.adapter.CommendTitleAdapter;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class GoodsCommendPresenter extends BasePresenter<GoodsCommendView> {
    public GoodsCommendPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTitle() {
        final List<TitleBean> titleList = new ArrayList<>();
        titleList.add(new TitleBean("全部", true));
        titleList.add(new TitleBean("淘宝", false));
        titleList.add(new TitleBean("京东", false));
        titleList.add(new TitleBean("拼多多", false));

        final CommendTitleAdapter titleAdapter = new CommendTitleAdapter(mContext, titleList, R.layout.rv_commend_title);

        titleAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                for (int i = 0; i < titleList.size(); i++) {
                    titleList.get(i).setCheck(i == position);
                }
                titleAdapter.notifyDataSetChanged();

            }
        });

        if (getView() != null) {
            getView().loadTitle(titleAdapter);
        }
    }
}
