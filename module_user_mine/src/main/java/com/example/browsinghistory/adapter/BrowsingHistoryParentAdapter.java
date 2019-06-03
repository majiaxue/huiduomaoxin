package com.example.browsinghistory.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.browsinghistory.bean.BrowsingHistoryChildBean;
import com.example.browsinghistory.bean.BrowsingHistoryBean;
import com.example.module_user_mine.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class BrowsingHistoryParentAdapter extends MyRecyclerAdapter<BrowsingHistoryBean.ParentBean> {

    private boolean isParentCompile;
    private BrowsingHistoryChildAdapter browsingHistoryChildAdapter;
    private List<BrowsingHistoryBean.ParentBean> parentList;
    private boolean allCheck = true;
    private ImageView parentCheck;
    private List<BrowsingHistoryBean.ParentBean.ChildBean> childList = new ArrayList<>();


    public BrowsingHistoryParentAdapter(Context context, List<BrowsingHistoryBean.ParentBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    public BrowsingHistoryParentAdapter(Context context, List<BrowsingHistoryBean.ParentBean> mList, int mLayoutId, boolean parentCompile) {
        super(context, mList, mLayoutId);
        this.isParentCompile = parentCompile;
        parentList = mList;
    }

    @Override
    public void convert(RecyclerViewHolder holder, final BrowsingHistoryBean.ParentBean data, final int position) {
        childList.addAll(data.getChildList());
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        browsingHistoryParentRec.setLayoutManager(linearLayoutManager);
        browsingHistoryChildAdapter = new BrowsingHistoryChildAdapter(context, data.getChildList(), R.layout.item_browsing_history_child, false);
        browsingHistoryChildAdapter.setChildCompile(isParentCompile);
        browsingHistoryParentRec.setAdapter(browsingHistoryChildAdapter);
        browsingHistoryChildAdapter.setViewTwoOnClickListener(new ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(final View view1, View view2, final int childPosition) {
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //选中
                        Toast.makeText(context, "childPosition:" + position + childPosition, Toast.LENGTH_SHORT).show();
                        allCheck = true;
                        isAllCheck(position, childPosition);
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


    }


    public void isAllCheck(int position, int childPosition) {

        Log.d("77777", "position: --->" + position);
        Log.d("77777", "childPosition: ----->" + childPosition);

        if (parentList.get(position).getChildList().get(childPosition).isCheck()) {
            parentList.get(position).getChildList().get(childPosition).setCheck(false);
        } else {
            parentList.get(position).getChildList().get(childPosition).setCheck(true);
        }

//        browsingHistoryChildAdapter.notifyDataSetChanged();

        for (int i = 0; i < parentList.get(position).getChildList().size(); i++) {
            if (!parentList.get(position).getChildList().get(i).isCheck()) {
                allCheck = false;
            }
        }

        parentList.get(position).setCheck(allCheck);

        notifyDataSetChanged();
    }


    public void checkAll(int position, boolean status) {
        if (status) {
            for (int i = 0; i < parentList.get(position).getChildList().size(); i++) {
                parentList.get(position).getChildList().get(i).setCheck(false);
            }
        } else {
            for (int i = 0; i < parentList.get(position).getChildList().size(); i++) {
                parentList.get(position).getChildList().get(i).setCheck(true);
            }
        }

        browsingHistoryChildAdapter.notifyDataSetChanged();
    }


    public void setCompile(boolean compile) {
        isParentCompile = compile;
        notifyDataSetChanged();
    }
}
