package com.example.upgrade.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_mine.R;

import java.util.List;

public class PopUpAdapter extends MyRecyclerAdapter<String> {
    private int[] imgArr = {R.drawable.icon_zimai, R.drawable.icon_fenxiangyong, R.drawable.icon_zhijiehuiyuan};

    public PopUpAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setImageResource(R.id.rv_pop_quanyi_img, imgArr[position])
                .setText(R.id.rv_pop_quanyi_txt, data);
    }
}
