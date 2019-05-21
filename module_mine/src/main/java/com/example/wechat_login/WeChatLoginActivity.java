package com.example.wechat_login;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.CountDownTimerUtil;

import butterknife.BindView;

/**
 * 微信登录
 */
public class WeChatLoginActivity extends BaseActivity<WeChatLoginView, WeChatLoginPresenter> implements WeChatLoginView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.wechat_phone)
    EditText wechatPhone;
    @BindView(R2.id.wechat_code)
    EditText wechatCode;
    @BindView(R2.id.wechat_get_code)
    TextView wechatGetCode;
    @BindView(R2.id.wechat_invite_code)
    EditText wechatInviteCode;
    @BindView(R2.id.wechat_btn)
    TextView wechatBtn;
    @BindView(R2.id.wechat_check)
    ImageView wechatCheck;
    @BindView(R2.id.wechat_user_agreement)
    TextView wechatUserAgreement;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wechat_login;
    }

    @Override
    public void initData() {
        includeTitle.setText(getResources().getString(R.string.wechat_login));
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        wechatCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.check();
            }
        });

        wechatGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getCodeNum();
            }
        });
    }

    @Override
    public void readed() {
        wechatCheck.setImageResource(R.drawable.icon_yiyuedu);
    }

    @Override
    public void noRead() {
        wechatCheck.setImageResource(R.drawable.icon_weiyuedu);
    }

    @Override
    public void getCodeSuccess() {
        wechatGetCode.setEnabled(false);
        wechatGetCode.setBackgroundResource(R.drawable.bg_get_code_clicked);
        CountDownTimerUtil.startTimer(this, wechatGetCode);
    }

    @Override
    public WeChatLoginView createView() {
        return this;
    }

    @Override
    public WeChatLoginPresenter createPresenter() {
        return new WeChatLoginPresenter(this);
    }
}
