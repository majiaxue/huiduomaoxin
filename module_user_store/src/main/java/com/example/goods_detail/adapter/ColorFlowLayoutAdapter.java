package com.example.goods_detail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.ChooseGoodsBean;
import com.example.user_store.R;
import com.example.utils.OnFlowSelectListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

public class ColorFlowLayoutAdapter extends TagAdapter<ChooseGoodsBean> {
    private Context context;
    private OnFlowSelectListener listener;

    public ColorFlowLayoutAdapter(List<ChooseGoodsBean> datas) {
        super(datas);
    }

    public ColorFlowLayoutAdapter(List<ChooseGoodsBean> datas, Context context, OnFlowSelectListener listener) {
        super(datas);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public View getView(FlowLayout parent, int position, ChooseGoodsBean chooseGoodsBean) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_choose_goods_color, parent, false);
        ImageView img = inflate.findViewById(R.id.flow_goods_color_img);
        TextView txt = inflate.findViewById(R.id.flow_goods_color_content);
        img.setImageResource(chooseGoodsBean.getImg());
        txt.setText(chooseGoodsBean.getName());
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
