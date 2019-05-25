package com.example.upgrade;

import android.content.Context;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.entity.UpgradeBean;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.upgrade.adapter.UpgradeAdapter;
import com.example.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class UpgradePresenter extends BasePresenter<UpgradeView> {
    public UpgradePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        List<UpgradeBean> dataList = new ArrayList<>();
        dataList.add(new UpgradeBean("铜牌", "铜牌权益说明描述", "200", "10", "10", "888"));
        dataList.add(new UpgradeBean("银牌", "银牌权益说明描述", "500", "20", "20", "1888"));
        dataList.add(new UpgradeBean("金牌", "金牌权益说明描述", "1000", "50", "50", "3888"));
        UpgradeAdapter adapter = new UpgradeAdapter(mContext, dataList, R.layout.rv_upgrade);
        if (getView() != null) {
            getView().loadUI(adapter);
        }

        adapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtil.e("开发中。。。。");
                    }
                });
            }
        });
    }


}
