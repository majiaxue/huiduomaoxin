package com.example.goods_detail.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.ChooseGoodsBean;
import com.example.user_store.R;
import com.example.utils.OnFlowSelectListener;
import com.example.view.flowLayout.TagFlowLayout;

import java.util.List;

public class PopChooseAdapter extends MyRecyclerAdapter<ChooseGoodsBean> {
    public PopChooseAdapter(Context context, List<ChooseGoodsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ChooseGoodsBean data, final int position) {
        holder.setText(R.id.pop_choose_rv_title, data.getType());

        TagFlowLayout flowLayout = holder.getView(R.id.pop_choose_rv_flow);
        PopFlowLayoutAdapter flowLayoutAdapter = new PopFlowLayoutAdapter(data.getList(), context, new OnFlowSelectListener() {
            @Override
            public void setOnFlowSelect(int index) {
                if (popChooseListener != null) {
                    popChooseListener.onPopChoose(position, index);
                }
            }
        });
        flowLayout.setAdapter(flowLayoutAdapter);

    }
}
