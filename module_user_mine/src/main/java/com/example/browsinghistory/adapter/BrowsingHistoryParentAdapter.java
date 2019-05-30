package com.example.browsinghistory.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
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

    private boolean isParentCompile;
    private BrowsingHistoryChildAdapter browsingHistoryChildAdapter;
    private List<BrowsingHistoryChildBean> childList;
    private List<BrowsingHistoryParentBean> parentList;
    private boolean allCheck = true;
    private ImageView parentCheck;

    public BrowsingHistoryParentAdapter(Context context, List<BrowsingHistoryParentBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    public BrowsingHistoryParentAdapter(Context context, List<BrowsingHistoryParentBean> mList, int mLayoutId, boolean parentCompile) {
        super(context, mList, mLayoutId);
        this.isParentCompile = parentCompile;
        parentList = mList;
    }

    @Override
    public void convert(RecyclerViewHolder holder, BrowsingHistoryParentBean data, final int position) {
        holder.setText(R.id.browsing_history_parent_time, data.getTime());
        parentCheck = holder.getView(R.id.browsing_history_parent_check);
        if (isParentCompile) {
            parentCheck.setVisibility(View.VISIBLE);
        } else {
            parentCheck.setVisibility(View.GONE);
        }

        if (data.isCheck()) {
            parentCheck.setImageResource(R.drawable.icon_xuanzhong);
        } else {
            parentCheck.setImageResource(R.drawable.icon_weixuanzhong);
        }


        viewOnClickListener.ViewOnClick(holder.getView(R.id.browsing_history_parent_check), position);

        final RecyclerView browsingHistoryParentRec = holder.getView(R.id.browsing_history_parent_rec);
        childList = new ArrayList<>();
        childList.add(new BrowsingHistoryChildBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店", false));
        childList.add(new BrowsingHistoryChildBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店", false));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        browsingHistoryParentRec.setLayoutManager(linearLayoutManager);
        browsingHistoryChildAdapter = new BrowsingHistoryChildAdapter(context, childList, R.layout.item_browsing_history_child, false);
        browsingHistoryParentRec.setAdapter(browsingHistoryChildAdapter);

        browsingHistoryChildAdapter.setViewTwoOnClickListener(new ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(final View view1, View view2, final int childPosition) {
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //选中
                        Toast.makeText(context, "childPosition:" + position+ childPosition, Toast.LENGTH_SHORT).show();
                        isAllCheck(childPosition);
                    }
                });

                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "childPosition:" + childPosition, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        browsingHistoryChildAdapter.setChildCompile(isParentCompile);
    }


    public void isAllCheck(int childPosition) {

        if (childList.get(childPosition).isCheck()) {
            childList.get(childPosition).setCheck(false);
        } else {
            childList.get(childPosition).setCheck(true);
        }

        browsingHistoryChildAdapter.notifyDataSetChanged();

//        for (int i = 0; i < childList.size(); i++) {
//            if (!childList.get(i).isCheck()){
//                allCheck = false;
//            }
//        }

//        return allCheck;

    }


    public void checkAll(boolean status) {

        if (status) {
            for (int i = 0; i < childList.size(); i++) {
                childList.get(i).setCheck(false);
            }
        } else {
            for (int i = 0; i < childList.size(); i++) {
                childList.get(i).setCheck(true);
            }
        }

        browsingHistoryChildAdapter.notifyDataSetChanged();
    }


    public void setCompile(boolean compile) {
        isParentCompile = compile;
        notifyDataSetChanged();
    }
}
