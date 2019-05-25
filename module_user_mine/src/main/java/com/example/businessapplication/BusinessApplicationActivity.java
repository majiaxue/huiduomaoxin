package com.example.businessapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商家申请
 */
@Route(path = "/module_user_mine/BusinessApplicationActivity")
public class BusinessApplicationActivity extends BaseActivity<BusinessApplicationView, BusinessApplicationPresenter> implements BusinessApplicationView {


    @BindView(R2.id.business_application_back)
    ImageView businessApplicationBack;
    @BindView(R2.id.business_application_icon)
    SimpleDraweeView businessApplicationIcon;
    @BindView(R2.id.business_application_shop_name)
    EditText businessApplicationShopName;
    @BindView(R2.id.business_application_shop_classify_text)
    TextView businessApplicationShopClassifyText;
    @BindView(R2.id.business_application_shop_classify)
    LinearLayout businessApplicationShopClassify;
    @BindView(R2.id.business_application_name)
    EditText businessApplicationName;
    @BindView(R2.id.business_application_phone)
    EditText businessApplicationPhone;
    @BindView(R2.id.business_application_shop_address_text)
    TextView businessApplicationShopAddressText;
    @BindView(R2.id.business_application_shop_address)
    LinearLayout businessApplicationShopAddress;
    @BindView(R2.id.business_application_detail_address)
    EditText businessApplicationDetailAddress;
    @BindView(R2.id.business_application_front_photo)
    SimpleDraweeView businessApplicationFrontPhoto;
    @BindView(R2.id.business_application_verso_photo)
    SimpleDraweeView businessApplicationVersoPhoto;
    @BindView(R2.id.business_application_business_license)
    SimpleDraweeView businessApplicationBusinessLicense;
    @BindView(R2.id.business_application_food_safety_permit)
    SimpleDraweeView businessApplicationFoodSafetyPermit;
    @BindView(R2.id.business_application_submit)
    TextView businessApplicationSubmit;
    @BindView(R2.id.business_application_agreement)
    TextView businessApplicationAgreement;

    private final int TAKE_PHOTO_CODE = 0x111;
    private final int PHOTO_ALBUM_CODE = 0x222;
    private final int CROP_CODE = 0x333;

    @Override
    public int getLayoutId() {
        return R.layout.activity_business_application;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        //返回
        businessApplicationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击提交
        businessApplicationSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BusinessApplicationActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
            }
        });
        //身份证正面
        businessApplicationFrontPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow(1);
            }
        });
        //身份证反面
        businessApplicationVersoPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow(2);
            }
        });
        //营业执照
        businessApplicationBusinessLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow(3);
            }
        });
        //食品安全许可证
        businessApplicationFoodSafetyPermit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow(4);
            }
        });


    }

    @Override
    public BusinessApplicationView createView() {
        return this;
    }

    @Override
    public BusinessApplicationPresenter createPresenter() {
        return new BusinessApplicationPresenter(this);
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
    public void cropPhoto(Intent intent, Uri uri) {
        startActivityForResult(intent, CROP_CODE);

    }

    @Override
    public void selectPhoto(Uri uri) {

    }

    @Override
    public void showHeader(String bitmap) {

    }

    @Override
    public void clearSuccess() {

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
}
