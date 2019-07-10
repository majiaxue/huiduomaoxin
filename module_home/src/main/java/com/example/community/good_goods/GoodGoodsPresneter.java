package com.example.community.good_goods;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.TitleBean;
import com.example.community.adapter.CommendTitleAdapter;
import com.example.community.adapter.GoodGoodsAdapter;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class GoodGoodsPresneter extends BasePresenter<GoodGoodsView> {

    private GoodGoodsAdapter goodsAdapter;

    public GoodGoodsPresneter(Context context) {
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

    public void loadData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("a");
        }
        goodsAdapter = new GoodGoodsAdapter(mContext, list, R.layout.rv_good_goods);
        goodsAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        copy(index);
                    }
                });
            }
        });

        if (getView() != null) {
            getView().loadContent(goodsAdapter);
        }
    }

    private void copy(int position) {
        //获取剪贴板管理器
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", "￥jfddfddghdhduuhgj￥jfcsdcjffdjfstfhgdfdfjgdggdgu");
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
        Toast.makeText(mContext, "复制成功", Toast.LENGTH_SHORT).show();
    }
}
