package com.example.goods_detail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.bean.UserGoodsDetail;
import com.example.user_store.R;
import com.example.utils.OnFlowClickListener;
import com.example.utils.OnFlowSelectListener;
import com.example.view.flowLayout.FlowLayout;
import com.example.view.flowLayout.TagAdapter;

import java.util.List;

public class SizeFlowLayoutAdapter extends TagAdapter<UserGoodsDetail.StoInfoBean.RecordsBean.ListBean> {
    private TextView price;
    private LayoutInflater inflater;
    private OnFlowSelectListener listener;
    private List<UserGoodsDetail.StoInfoBean.RecordsBean.ListBean> data;

    public SizeFlowLayoutAdapter(List<UserGoodsDetail.StoInfoBean.RecordsBean.ListBean> datas) {
        super(datas);
    }

    public SizeFlowLayoutAdapter(List<UserGoodsDetail.StoInfoBean.RecordsBean.ListBean> datas, Context context, TextView price, OnFlowSelectListener listener) {
        super(datas);
        this.price = price;
        data = datas;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(FlowLayout parent, int position, UserGoodsDetail.StoInfoBean.RecordsBean.ListBean goodsSize) {
        TextView txt = (TextView) inflater.inflate(R.layout.pop_choose_goods_size, parent, false);
        txt.setText(goodsSize.getSp2());
//        if (goodsSize.getCount() <= 0) {
//            txt.setTextColor(Color.parseColor("#f5f5f5"));
//        } else {
        txt.setTextColor(Color.parseColor("#333333"));
//        }

        return txt;
    }

    @Override
    public void onSelected(int position, View view) {
        view.setBackgroundResource(R.drawable.goods_5_99fd3c15);
        price.setText("￥" + data.get(position).getPrice());
        listener.setOnFlowSelect(position);
    }

    @Override
    public void unSelected(int position, View view) {
        view.setBackgroundResource(R.drawable.goods_5_e5e5e5);
    }
}
