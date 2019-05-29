package com.example.confirm_order.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.user_store.R;
import com.example.utils.SpaceItemDecoration;

import java.util.List;

public class ConfirmOrderAdapter extends MyRecyclerAdapter<List<Integer>> {
    public ConfirmOrderAdapter(Context context, List<List<Integer>> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, List<Integer> data, final int position) {
        RecyclerView rv = holder.getView(R.id.confirm_order_inside_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        if (rv.getItemDecorationCount() < 1) {
            rv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) context.getResources().getDimension(R.dimen.dp_10)));
        }
        ConfirmOrderInsideAdapter insideAdapter = new ConfirmOrderInsideAdapter(context, data, R.layout.rv_inside_confirm_order);
        rv.setAdapter(insideAdapter);

        insideAdapter.setViewTwoOnClickListener(new ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(View view1, View view2, int index) {
                if (twoViewClickListener != null) {
                    twoViewClickListener.twoViewClick(view1, view2, position, index);
                }
            }
        });

    }
}
