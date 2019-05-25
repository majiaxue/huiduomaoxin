package com.example.goods_detail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.entity.ChooseGoodsBean;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.example.utils.OnFlowSelectListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

public class SizeFlowLayoutAdapter extends TagAdapter<ChooseGoodsBean.GoodsSize> {
    private OnFlowSelectListener listener;
    private LayoutInflater inflater;
    private List<ChooseGoodsBean.GoodsSize> dataList;
    //记录上次选中的item
    private int flag;
    private int nullItem;

    public SizeFlowLayoutAdapter(List<ChooseGoodsBean.GoodsSize> datas) {
        super(datas);
    }

    public SizeFlowLayoutAdapter(List<ChooseGoodsBean.GoodsSize> datas, Context context, OnFlowSelectListener listener) {
        super(datas);
        dataList = datas;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(FlowLayout parent, int position, ChooseGoodsBean.GoodsSize goodsSize) {
        TextView txt = (TextView) inflater.inflate(R.layout.pop_choose_goods_size, parent, false);
        txt.setText(goodsSize.getSize());
        if (goodsSize.getCount() <= 0) {
            txt.setTextColor(Color.parseColor("#f5f5f5"));
            txt.setEnabled(false);
        } else {
            txt.setTextColor(Color.parseColor("#333333"));
            txt.setEnabled(true);
        }

        return txt;
    }

    @Override
    public void onSelected(int position, View view) {
        if (dataList.get(position).getCount() > 0) {
            view.setBackgroundResource(R.drawable.goods_5_99fd3c15);
            flag = position;
            nullItem = position;
            listener.setOnFlowSelect(position);
        } else {

            nullItem = position;
        }
        LogUtil.e("xuanzhong--->flag:" + flag + "=====nullitem:" + nullItem);
    }

    @Override
    public void unSelected(int position, View view) {
        LogUtil.e("未选中--->flag:" + flag + "=====nullitem:" + nullItem);

            view.setBackgroundResource(R.drawable.goods_5_e5e5e5);

    }
}
