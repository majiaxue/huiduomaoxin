package com.example.bind_wechat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

/**
 * 绑定微信
 */
public class BindWeChatActivity extends BaseActivity<BindWeChatView, BindWeChatPresenter> implements BindWeChatView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.bind_wechat_btn)
    TextView bindWechatBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_wechat;
    }

    @Override
    public void initData() {
        includeTitle.setText(getResources().getString(R.string.bind_wechat));
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bindWechatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BindWeChatActivity.this, "开发中...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public BindWeChatView createView() {
        return this;
    }

    @Override
    public BindWeChatPresenter createPresenter() {
        return new BindWeChatPresenter(this);
    }
}
