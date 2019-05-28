package com.example.browsinghistory;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

/**
 * 浏览历史
 */
@Route(path = "/module_user_mine/BrowsingHistoryActivity")
public class BrowsingHistoryActivity extends BaseActivity<BrowsingHistoryView, BrowsingHistoryPresenter> implements BrowsingHistoryView {


    @BindView(R2.id.browsing_history_back)
    ImageView browsingHistoryBack;
    @BindView(R2.id.browsing_history_state)
    TextView browsingHistoryState;
    @BindView(R2.id.browsing_history_rec)
    RecyclerView browsingHistoryRec;
    @BindView(R2.id.browsing_history_check_all)
    ImageView browsingHistoryCheckAll;
    @BindView(R2.id.browsing_history_delete)
    TextView browsingHistoryDelete;
    @BindView(R2.id.browsing_history_bottom)
    LinearLayout browsingHistoryBottom;


    @Override
    public int getLayoutId() {
        return R.layout.activity_browsing_history;
    }

    @Override
    public void initData() {
        presenter.browsingHistoryRec(browsingHistoryRec);
    }

    @Override
    public void initClick() {
        browsingHistoryBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public BrowsingHistoryView createView() {
        return this;
    }

    @Override
    public BrowsingHistoryPresenter createPresenter() {
        return new BrowsingHistoryPresenter(this);
    }

}
