package com.example.businessapplication.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.PopupGoodsClassBean;
import com.example.bean.PopupXSGoodsClassBean;
import com.example.module_user_mine.R;

import java.util.List;

public class PopupXSGoodsClassifyAdapter extends MyRecyclerAdapter<PopupXSGoodsClassBean> {

    public PopupXSGoodsClassifyAdapter(Context context, List<PopupXSGoodsClassBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, PopupXSGoodsClassBean data, int position) {
        if (data.isCheck()) {
            holder.setImageResource(R.id.popup_item_goods_classify_check, R.drawable.icon_xuanzhong);
        } else {
            holder.setImageResource(R.id.popup_item_goods_classify_check, R.drawable.icon_weixuanzhong);
        }
        holder.setText(R.id.popup_item_goods_classify_text, data.getName());
    }
}
