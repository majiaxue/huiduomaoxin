package com.example.goods_detail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.ChooseInsideBean;
import com.example.bean.UserGoodsDetail;
import com.example.user_store.R;
import com.example.utils.OnFlowSelectListener;
import com.example.view.flowLayout.FlowLayout;
import com.example.view.flowLayout.TagAdapter;

import java.util.List;

public class PopFlowLayoutAdapter extends TagAdapter<ChooseInsideBean> {
    private Context context;
    private OnFlowSelectListener listener;

    public PopFlowLayoutAdapter(List<ChooseInsideBean> datas) {
        super(datas);
    }

    public PopFlowLayoutAdapter(List<ChooseInsideBean> datas, Context context, OnFlowSelectListener listener) {
        super(datas);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public View getView(FlowLayout parent, int position, final ChooseInsideBean chooseGoodsBean) {
        final View inflate = LayoutInflater.from(context).inflate(R.layout.pop_choose_goods_color, parent, false);
        ImageView img = inflate.findViewById(R.id.flow_goods_color_img);
        TextView txt = inflate.findViewById(R.id.flow_goods_color_content);
        if (chooseGoodsBean.getPicUrl() == null || "".equals(chooseGoodsBean.getPicUrl())) {
            img.setVisibility(View.GONE);
        } else {
            img.setVisibility(View.VISIBLE);
            Glide.with(context).load(chooseGoodsBean.getPicUrl()).into(img);
        }
        txt.setText(chooseGoodsBean.getContent() + "");

        if (flowClickListener != null) {
            flowClickListener.onFlowClickListener(inflate, position);
        }
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
