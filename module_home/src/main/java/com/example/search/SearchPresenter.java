package com.example.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.CommonResource;
import com.example.dbflow.DBflowUtil;
import com.example.dbflow.SearchHistoryBean;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/22
 * Describe:
 */
public class SearchPresenter extends BasePresenter<SearchView> {

    public SearchPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void getHistory(FlowLayout searchFlowLayout, final int position) {
        final List<SearchHistoryBean> list = DBflowUtil.getInstance().query(CommonResource.HISTORY_TBK);
        for (int i = 0; i < list.size(); i++) {
            TextView searchTextView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.search_text_view, searchFlowLayout, false);
            searchTextView.setText(list.get(i).getContent());
            final int finalI = i;
            searchTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchEdit(list.get(finalI).getContent(), position);
                }
            });
            searchFlowLayout.addView(searchTextView);
        }
    }

    public void searchEdit(String content, int position) {
        if (!"".equals(content) && content != null) {
            DBflowUtil.getInstance().insert(content, CommonResource.HISTORY_TBK);
        }
        ARouter.getInstance().build("/module_classify/ClassificationDetailsActivity").withInt("position", position).withString("searchContent", content).navigation();
    }
}
