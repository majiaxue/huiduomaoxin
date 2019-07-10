package com.example.local_shop.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.user_store.R;

import java.util.List;

public class LocalNavbarAdapter extends MyRecyclerAdapter<String> {
    private String[] titleArr = {"电影院", "美食饮品", "书店", "KTV", "运动健身", "生鲜超市", "生活服务", "健康关怀"};
    private int[] imgArr = {R.drawable.icon_xingzhuang8, R.drawable.icon_xingzhuang9, R.drawable.icon_xingzhuang10, R.drawable.icon_ktv, R.drawable.icon_xingzhuang13, R.drawable.icon_xingzhuang14, R.drawable.icon_xingzhuang15, R.drawable.icon_xingzhuang16};

    public LocalNavbarAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setImageResource(R.id.rv_local_navbar_img, imgArr[position])
                .setText(R.id.rv_local_navbar_txt, titleArr[position]);
    }
}
