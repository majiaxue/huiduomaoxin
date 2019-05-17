package com.example.setting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.bind_wechat.BindWeChatActivity;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.replace_phone.ReplacePhoneActivity;
import com.example.update_password.UpdatePasswordActivity;
import com.example.utils.CacheUtil;

import butterknife.BindView;

/**
 * 设置
 */
@Route(path = "/mine/setting")
public class SettingActivity extends BaseActivity<SettingView, SettingPresenter> implements SettingView {
    @BindView(R2.id.setting_back)
    ImageView settingBack;
    @BindView(R2.id.setting_preserve)
    TextView settingPreserve;
    @BindView(R2.id.setting_header)
    ImageView settingHeader;
    @BindView(R2.id.setting_update_header)
    TextView settingUpdateHeader;
    @BindView(R2.id.setting_nick_name)
    EditText settingNickName;
    @BindView(R2.id.setting_personality_sign)
    EditText settingPersonalitySign;
    @BindView(R2.id.setting_update_password)
    LinearLayout settingUpdatePassword;
    @BindView(R2.id.setting_bind_wechat)
    LinearLayout settingBindWechat;
    @BindView(R2.id.setting_replace_phone)
    LinearLayout settingReplacePhone;
    @BindView(R2.id.setting_clear_cache)
    LinearLayout settingClearCache;
    @BindView(R2.id.setting_about_us)
    LinearLayout settingAboutUs;
    @BindView(R2.id.setting_logout)
    TextView settingLogout;
    @BindView(R2.id.setting_cache_txt)
    TextView settingCacheTxt;

    private String totalCache;

    private final int TAKE_PHOTO_CODE = 0x111;
    private final int PHOTO_ALBUM_CODE = 0x222;
    private final int CROP_CODE = 0x333;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initData() {
        totalCache = CacheUtil.getTotalCacheSize(this);
        settingCacheTxt.setText(totalCache);
    }

    @Override
    public void initClick() {
        settingUpdateHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateHeader();
            }
        });

        settingClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clearCache(totalCache);
            }
        });

        settingUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, UpdatePasswordActivity.class));
            }
        });

        settingBindWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, BindWeChatActivity.class));
            }
        });

        settingReplacePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, ReplacePhoneActivity.class));
            }
        });

        settingPreserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.preserve(settingNickName.getText().toString(), settingPersonalitySign.getText().toString());
            }
        });
    }

    @Override
    public void takePhoto(Intent intent) {
        startActivityForResult(intent, TAKE_PHOTO_CODE);
    }

    @Override
    public void photoAlbum(Intent intent) {
        startActivityForResult(intent, PHOTO_ALBUM_CODE);
    }

    @Override
    public void cropPhoto(Intent intent) {
        startActivityForResult(intent, CROP_CODE);
    }

    @Override
    public void showHeader(Bitmap bitmap) {
        Glide.with(this).load(bitmap).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(settingHeader);
    }

    @Override
    public void clearSuccess() {
        settingCacheTxt.setText(CacheUtil.getTotalCacheSize(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case TAKE_PHOTO_CODE:
                presenter.cropImage();
                break;
            case PHOTO_ALBUM_CODE:
                presenter.parseUri(data);
                break;
            case CROP_CODE:
                presenter.uploadPhoto();
                break;
        }
    }

    @Override
    public SettingView createView() {
        return this;
    }

    @Override
    public SettingPresenter createPresenter() {
        return new SettingPresenter(this);
    }
}
