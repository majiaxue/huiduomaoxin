package com.example.goods_detail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.UserGoodsDetail;
import com.example.entity.ChooseGoodsBean;
import com.example.user_store.R;
import com.example.utils.OnFlowSelectListener;
import com.example.view.flowLayout.FlowLayout;
import com.example.view.flowLayout.TagAdapter;

import java.util.List;

public class ColorFlowLayoutAdapter extends TagAdapter<UserGoodsDetail.StoInfoBean.RecordsBean> {
    private Context context;
    private OnFlowSelectListener listener;

    public ColorFlowLayoutAdapter(List<UserGoodsDetail.StoInfoBean.RecordsBean> datas) {
        super(datas);
    }

    public ColorFlowLayoutAdapter(List<UserGoodsDetail.StoInfoBean.RecordsBean> datas, Context context, OnFlowSelectListener listener) {
        super(datas);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public View getView(FlowLayout parent, int position, UserGoodsDetail.StoInfoBean.RecordsBean chooseGoodsBean) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_choose_goods_color, parent, false);
        ImageView img = inflate.findViewById(R.id.flow_goods_color_img);
        TextView txt = inflate.findViewById(R.id.flow_goods_color_content);
        Glide.with(context).load(chooseGoodsBean.getList().get(0).getPic()).into(img);
        txt.setText(chooseGoodsBean.getSkuName());
        return inflate;
    }

    @Override
    public void onSelected(int position, View view) {
        TextView txt = view.findViewById(R.id.flow_goods_color_content);
        txt.setTextColor(Color.parseColor("#ffffff"));
        listener.setOnFlowSelect(position);
    }

    @Override
    public void unSelected(int position, View view) {
        TextView txt = view.findViewById(R.id.flow_goods_color_content);
        txt.setTextColor(Color.parseColor("#333333"));
    }
}
