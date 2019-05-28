package com.example.refund;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

/**
 * 退款申请
 */
@Route(path = "/module_user_mine/RefundActivity")
public class RefundActivity extends BaseActivity<RefundView, RefundPresenter> implements RefundView {


    @BindView(R2.id.refund_image_back)
    ImageView refundImageBack;
    @BindView(R2.id.refund_image)
    SimpleDraweeView refundImage;
    @BindView(R2.id.refund_goods_name)
    TextView refundGoodsName;
    @BindView(R2.id.refund_colour)
    TextView refundColour;
    @BindView(R2.id.refund_size)
    TextView refundSize;
    @BindView(R2.id.refund_cause_text)
    TextView refundCauseText;
    @BindView(R2.id.refund_cause)
    LinearLayout refundCause;
    @BindView(R2.id.refund_type_text)
    TextView refundTypeText;
    @BindView(R2.id.refund_type)
    LinearLayout refundType;
    @BindView(R2.id.refund_sum_text)
    TextView refundSumText;
    @BindView(R2.id.refund_explain_edit)
    EditText refundExplainEdit;
    @BindView(R2.id.refund_add_photo)
    SimpleDraweeView refundAddPhoto;
    @BindView(R2.id.refund_submit)
    TextView refundSubmit;
    private final int TAKE_PHOTO_CODE = 0x111;
    private final int PHOTO_ALBUM_CODE = 0x222;
    private final int CROP_CODE = 0x333;

    @Override
    public int getLayoutId() {
        return R.layout.activity_refund;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        //返回
        refundImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //退款原因
        refundCause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupCause(refundCauseText);
            }
        });
        //退款类型
        refundType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupType(refundTypeText);
            }
        });
        //添加照片
        refundAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow();
            }
        });
        //提交
        refundSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/RefundSucceedActivity").navigation();
            }
        });
    }

    @Override
    public RefundView createView() {
        return this;
    }

    @Override
    public RefundPresenter createPresenter() {
        return new RefundPresenter(this);
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
    public void selectPhoto(Uri uri) {
        refundAddPhoto.setImageURI(uri);
    }

    @Override
    public void showHeader(String base64) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case TAKE_PHOTO_CODE:
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
