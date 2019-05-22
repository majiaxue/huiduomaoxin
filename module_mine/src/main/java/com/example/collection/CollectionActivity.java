package com.example.collection;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

@Route(path = "/mine/collection")
public class CollectionActivity extends BaseActivity<CollectionView, CollectionPresenter> implements CollectionView {
    @BindView(R2.id.collection_back)
    ImageView collectionBack;
    @BindView(R2.id.collection_editor)
    TextView collectionEditor;
    @BindView(R2.id.collection_rv)
    RecyclerView collectionRv;
    @BindView(R2.id.collection_all_check)
    ImageView collectionAllCheck;
    @BindView(R2.id.collection_delete)
    TextView collectionDelete;
    @BindView(R2.id.collection_bottom)
    LinearLayout collectionBottom;

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        collectionRv.setLayoutManager(layoutManager);
        presenter.loadData();
    }

    @Override
    public void initClick() {
        collectionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collectionEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.edit();
            }
        });
    }

    @Override
    public void toEdit() {
        collectionEditor.setText("完成");
        collectionBottom.setVisibility(View.VISIBLE);
    }

    @Override
    public void toFinish() {
        collectionEditor.setText("编辑");
        collectionBottom.setVisibility(View.GONE);
    }

    @Override
    public CollectionView createView() {
        return this;
    }

    @Override
    public CollectionPresenter createPresenter() {
        return new CollectionPresenter(this);
    }
}
