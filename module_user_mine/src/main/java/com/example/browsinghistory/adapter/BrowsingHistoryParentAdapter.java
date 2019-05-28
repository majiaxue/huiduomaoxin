package com.example.browsinghistory.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.browsinghistory.bean.BrowsingHistoryChildBean;
import com.example.browsinghistory.bean.BrowsingHistoryParentBean;
import com.example.module_user_mine.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class BrowsingHistoryParentAdapter extends MyRecyclerAdapter<BrowsingHistoryParentBean> {

    public BrowsingHistoryParentAdapter(Context context, List<BrowsingHistoryParentBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, BrowsingHistoryParentBean data, int position) {
        holder.setText(R.id.browsing_history_parent_time,data.getTime());

        RecyclerView browsingHistoryParentRec = holder.getView(R.id.browsing_history_parent_rec);
        List<BrowsingHistoryChildBean> list = new ArrayList<>();
        list.add(new BrowsingHistoryChildBean(R.drawable.img_108,"2019夏季新款纯棉白色短袖女T恤个性字母简约......","￥39.90","12345人付款","97%好评","班迪卡旗舰店",false));
        list.add(new BrowsingHistoryChildBean(R.drawable.img_108,"2019夏季新款纯棉白色短袖女T恤个性字母简约......","￥39.90","12345人付款","97%好评","班迪卡旗舰店",false));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        browsingHistoryParentRec.setLayoutManager(linearLayoutManager);
        BrowsingHistoryChildAdapter browsingHistoryChildAdapter = new BrowsingHistoryChildAdapter(context, list, R.layout.item_browsing_history_child);
        browsingHistoryParentRec.setAdapter(browsingHistoryChildAdapter);
        browsingHistoryChildAdapter.setViewOnClickListener(new ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int position) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
