package com.example.search;

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
    private String[] mVals = new String[]{"渔夫帽", "渔夫帽蓝色", "渔夫帽湖蓝色 ", "渔夫帽蓝色",
            "渔夫帽湖蓝色", "渔夫帽湖蓝色"};
    private TextView searchTextView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {
        for (int i = 0; i < mVals.length; i++) {
            searchTextView = (TextView) LayoutInflater.from(this).inflate(R.layout.search_text_view, searchFlowLayout, false);
            searchTextView.setText(mVals[i]);
            final int finalI = i;
            searchTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SearchActivity.this, mVals[finalI], Toast.LENGTH_SHORT).show();
                }
            });
            searchFlowLayout.addView(searchTextView);
        }
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
                String s = searchEdit.getText().toString();
                searchTextView = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.search_text_view, searchFlowLayout, false);
                searchTextView.setText(s);
                searchFlowLayout.addView(searchTextView);
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
