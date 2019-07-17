package com.example.Universallist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.LogUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.view.CustomHeader;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 全能列表
 */
@Route(path = "/module_home/UniversalListActivity")
public class UniversalListActivity extends BaseActivity<UniversalListView, UniversalListPresenter> implements UniversalListView {

    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.universal_list_rec)
    RecyclerView universalListRec;
    @BindView(R2.id.universal_list_smart_refresh)
    SmartRefreshLayout universalListSmartRefresh;

    @Autowired(name = "position")
    int position;

    private int page = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_universal_list;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        ProcessDialogUtil.showProcessDialog(this);
        LogUtil.e("从哪个地方近的" + position);
        if (position == 4) {
            includeTitle.setText("爆款推荐");
            presenter.hotRecommend(universalListRec,page);
        } else if (position == 1) {
            includeTitle.setText("淘抢购");
        } else if (position == 2) {
            includeTitle.setText("9.9包邮");
        } else if (position == 3) {
            includeTitle.setText("聚划算");
        }
        presenter.universalList(universalListRec, position, page);

        universalListSmartRefresh.setRefreshHeader(new MaterialHeader(this));
        universalListSmartRefresh.setRefreshFooter(new ClassicsFooter(this));

        universalListSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                if (position == 4){
                    presenter.hotRecommend(universalListRec,page);
                }else{
                    presenter.universalList(universalListRec, position, page);
                }
            }
        });

        universalListSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (position == 4){
                    presenter.hotRecommend(universalListRec,page);
                }else{
                    presenter.universalList(universalListRec, position, page);
                }
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
    public UniversalListView createView() {
        return this;
    }

    @Override
    public UniversalListPresenter createPresenter() {
        return new UniversalListPresenter(this);
    }

    @Override
    public void finishRefresh() {
        universalListSmartRefresh.finishRefresh();
        universalListSmartRefresh.finishLoadMore();
    }
}
