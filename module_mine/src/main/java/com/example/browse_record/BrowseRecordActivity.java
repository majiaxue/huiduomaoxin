package com.example.browse_record;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.browse_record.adapter.BrowseRecordAdapter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

@Route(path = "/mine/browserecord")
public class BrowseRecordActivity extends BaseActivity<BrowseRecordView, BrowseRecordPresenter> implements BrowseRecordView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.browse_record_rv)
    RecyclerView browseRecordRv;
    @BindView(R2.id.browse_record_refresh)
    SmartRefreshLayout browseRecordRefresh;

    @Override
    public int getLayoutId() {
        return R.layout.activity_browse_record;
    }

    @Override
    public void initData() {
        includeTitle.setText("浏览记录");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        browseRecordRv.setLayoutManager(layoutManager);
        presenter.loadData();

        //设置 Header 为 官方主题 样式
        browseRecordRefresh.setRefreshHeader(new MaterialHeader(this));
        //设置 Footer 为 默认 样式
        browseRecordRefresh.setRefreshFooter(new ClassicsFooter(this));

        //********************设置上拉刷新下拉加载
        browseRecordRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                refreshLayout.finishRefresh();
            }
        });
        browseRecordRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                refreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void loadUI(BrowseRecordAdapter adapter) {
        browseRecordRv.setAdapter(adapter);
    }

    @Override
    public BrowseRecordView createView() {
        return this;
    }

    @Override
    public BrowseRecordPresenter createPresenter() {
        return new BrowseRecordPresenter(this);
    }
}
