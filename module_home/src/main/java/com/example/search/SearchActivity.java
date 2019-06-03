package com.example.search;

import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.view.FlowLayout;

import butterknife.BindView;

/**
 * 搜索页面
 */
@Route(path = "/module_home/SearchActivity")
public class SearchActivity extends BaseActivity<SearchView, SearchPresenter> implements SearchView {

    @BindView(R2.id.search_back)
    ImageView searchBack;
    @BindView(R2.id.search_edit)
    EditText searchEdit;
    @BindView(R2.id.search_text)
    TextView searchText;
    @BindView(R2.id.search_delete)
    ImageView searchDelete;
    @BindView(R2.id.search_flow_layout)
    FlowLayout searchFlowLayout;
    @BindView(R2.id.search_tab)
    TabLayout searchTab;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {
        //初始化tablayout
        presenter.initTabLayout(searchTab);
        presenter.searchFlowLayout(searchFlowLayout);

    }

    @Override
    public void initClick() {
        searchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.searchEdit(searchEdit,searchFlowLayout);

            }
        });

        searchDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFlowLayout.removeAllViews();
            }
        });
    }

    @Override
    public SearchView createView() {
        return this;
    }

    @Override
    public SearchPresenter createPresenter() {
        return new SearchPresenter(this);
    }

}
