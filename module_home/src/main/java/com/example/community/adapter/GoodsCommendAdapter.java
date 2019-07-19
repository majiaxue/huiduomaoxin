package com.example.community.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.CommunityLocalBean;
import com.example.common.CommonResource;
import com.example.community.goods_commend.GoodsCommendPresenter;
import com.example.module_home.R;
import com.example.utils.ArithUtil;
import com.example.utils.MyTimeUtil;
import com.example.utils.SPUtil;
import com.example.utils.TBUtil;

import java.util.List;

public class GoodsCommendAdapter extends MyRecyclerAdapter<CommunityLocalBean> {
    public GoodsCommendAdapter(Context context, List<CommunityLocalBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, final CommunityLocalBean data, int position) {
        double v = Double.valueOf(data.getTkrates()) / 100 * Double.valueOf(data.getItemprice()) * SPUtil.getFloatValue(CommonResource.BACKBL);
        holder.setText(R.id.rv_goods_commend_name, data.getItemtitle())
                .setImageUrl(R.id.rv_goods_commend_head, data.getSellerIcon())
                .setText(R.id.rv_goods_commend_content, data.getCopyContent() == null ? "" : data.getCopyContent().replaceAll("&lt;br&gt;", "\n"))
                .setText(R.id.rv_goods_commend_profit, "分享赚￥" + ArithUtil.exact(v, 2) + "元");

        if (data.getTime().indexOf("-") != -1) {
            holder.setText(R.id.rv_goods_commend_time, data.getTime());
        } else {
            holder.setText(R.id.rv_goods_commend_time, MyTimeUtil.date2StringLong(data.getTime()));
        }

        List<String> pics = data.getPics();
        RecyclerView rv = holder.getView(R.id.rv_goods_commend_img);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        rv.setLayoutManager(layoutManager);
        GoodsCommendInsideAdapter adapter = new GoodsCommendInsideAdapter(context, pics, R.layout.rv_goods_commend_inside);
        rv.setAdapter(adapter);

        adapter.setOnItemClick(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                if (GoodsCommendPresenter.type == 0) {
                    new TBUtil().openTbWithGoodsId(context, data.getId());
                }
            }
        });

        if (viewOnClickListener != null) {
            viewOnClickListener.ViewOnClick(holder.getView(R.id.rv_goods_commend_share), position);
        }
    }
}
