package com.example.local_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;

import butterknife.BindView;

public class LocalListActivity extends BaseActivity<LocalListView, LocalListPresenter> implements LocalListView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.local_list_text1)
    TextView localListText1;
    @BindView(R2.id.local_list_synthesize_bottom)
    ImageView localListSynthesizeBottom;
    @BindView(R2.id.local_list_synthesize)
    RelativeLayout localListSynthesize;
    @BindView(R2.id.local_list_text2)
    TextView localListText2;
    @BindView(R2.id.local_list_distance_top)
    ImageView localListDistanceTop;
    @BindView(R2.id.local_list_distance_bottom)
    ImageView localListDistanceBottom;
    @BindView(R2.id.local_list_distance)
    RelativeLayout localListDistance;
    @BindView(R2.id.local_list_text3)
    TextView localListText3;
    @BindView(R2.id.local_list_score_top)
    ImageView localListScoreTop;
    @BindView(R2.id.local_list_score_bottom)
    ImageView localListScoreBottom;
    @BindView(R2.id.local_list_score)
    RelativeLayout localListScore;
    @BindView(R2.id.local_list_rv_shop)
    RecyclerView localListRvShop;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_list;
    }

    @Override
    public void initData() {

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
    public LocalListView createView() {
        return this;
    }

    @Override
    public LocalListPresenter createPresenter() {
        return new LocalListPresenter(this);
    }
}
