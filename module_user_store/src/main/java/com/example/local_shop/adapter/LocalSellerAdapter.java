package com.example.local_shop.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.user_store.R;
import com.example.utils.SpaceItemDecoration;
import com.example.view.RatingBarView;

import java.util.ArrayList;
import java.util.List;

public class LocalSellerAdapter extends MyRecyclerAdapter<String> {
    private int[] imgArr = {R.drawable.img_501, R.drawable.img_502, R.drawable.img_503, R.drawable.img_501, R.drawable.img_502, R.drawable.img_503, R.drawable.img_501, R.drawable.img_502, R.drawable.img_503};

    public LocalSellerAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setImageResource(R.id.rv_local_seller_img, imgArr[position])
                .setText(R.id.rv_local_seller_name, data);
        RatingBarView star = holder.getView(R.id.rv_local_seller_star);
        star.setClickable(false);
        star.setStar(4, false);

        RecyclerView rv = holder.getView(R.id.rv_local_seller_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        rv.setLayoutManager(layoutManager);
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0, (int) context.getResources().getDimension(R.dimen.dp_10), 0, (int) context.getResources().getDimension(R.dimen.dp_10));
        rv.addItemDecoration(itemDecoration);
        List<String> list = new ArrayList<>();

        list.add("满20减5");
        list.add("满30减10");
        ManJianAdapter adapter = new ManJianAdapter(context, list, R.layout.rv_local_seller_inside);
        rv.setAdapter(adapter);
    }
}
