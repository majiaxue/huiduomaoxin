package com.example.group_fans;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.group_fans.adapter.GroupFansRvAdapter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.KeyboardStateObserver;
import com.example.utils.LogUtil;
import com.example.utils.SpaceItemDecoration;
import com.example.utils.TxtUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

@Route(path = "/mine/groupfans")
public class GroupFansActivity extends BaseActivity<GroupFansView, GroupFansPresenter> implements GroupFansView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.group_fans_edit)
    EditText groupFansEdit;
    @BindView(R2.id.group_fans_search)
    TextView groupFansSearch;
    @BindView(R2.id.group_fans_zhitui)
    TextView groupFansZhitui;
    @BindView(R2.id.group_fans_xinzeng)
    TextView groupFansXinzeng;
    @BindView(R2.id.group_fans_tuijianren)
    TextView groupFansTuijianren;
    @BindView(R2.id.group_fans_jianbian)
    TextView groupFansJianbian;
    @BindView(R2.id.group_fans_rv)
    RecyclerView groupFansRv;
    @BindView(R2.id.group_fans_refresh)
    SmartRefreshLayout groupFansRefresh;

    @Override
    public int getLayoutId() {
        return R.layout.activity_group_fans;
    }

    @Override
    public void initData() {
        includeTitle.setText("团队粉丝");
        presenter.loadData();

        KeyboardStateObserver.getKeyboardStateObserver(this).setKeyboardVisibilityListener(new KeyboardStateObserver.OnKeyboardVisibilityListener() {
            @Override
            public void onKeyboardShow() {

            }

            @Override
            public void onKeyboardHide() {
                groupFansEdit.clearFocus();
            }
        });

        //设置 Header 为 官方主题 样式
        groupFansRefresh.setRefreshHeader(new MaterialHeader(this));
        //设置 Footer 为 默认 样式
        groupFansRefresh.setRefreshFooter(new ClassicsFooter(this));

        //********************设置上拉刷新下拉加载
        groupFansRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                groupFansRefresh.finishRefresh();
            }
        });
        groupFansRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                groupFansRefresh.finishLoadMore();
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

        groupFansEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    groupFansSearch.setVisibility(View.VISIBLE);
                } else {
                    groupFansSearch.setVisibility(View.INVISIBLE);
                }
            }
        });

        groupFansEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        groupFansSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupFansEdit.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (presenter.isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }

        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);

    }

    @Override
    public void loadUI(GroupFansRvAdapter adapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        groupFansRv.setLayoutManager(layoutManager);
        groupFansRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_7)));
        groupFansRv.setAdapter(adapter);
    }

    @Override
    public GroupFansView createView() {
        return this;
    }

    @Override
    public GroupFansPresenter createPresenter() {
        return new GroupFansPresenter(this);
    }
}
