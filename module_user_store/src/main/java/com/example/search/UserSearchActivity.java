package com.example.search;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.view.FlowLayout;

import butterknife.BindView;

public class UserSearchActivity extends BaseActivity<UserSearchView, UserSearchPresenter> implements UserSearchView {
    @BindView(R2.id.user_search_back)
    ImageView searchBack;
    @BindView(R2.id.user_search_edit)
    EditText searchEdit;
    @BindView(R2.id.user_search_text)
    TextView searchText;
    @BindView(R2.id.user_search_delete)
    ImageView searchDelete;
    @BindView(R2.id.user_search_flow_layout)
    FlowLayout searchFlowLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_search;
    }

    @Override
    public void initData() {

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
                presenter.searchEdit(searchEdit.getText().toString());

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
    public UserSearchView createView() {
        return this;
    }

    @Override
    public UserSearchPresenter createPresenter() {
        return new UserSearchPresenter(this);
    }
}
