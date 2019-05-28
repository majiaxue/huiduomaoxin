package com.example.refundparticulars;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 退款详情
 */
@Route(path = "/module_user_mine/RefundParticularsActivity")
public class RefundParticularsActivity extends BaseActivity<RefundParticularsView, RefundParticularsPresenter> implements RefundParticularsView {


    @BindView(R2.id.refund_particulars_back)
    ImageView refundParticularsBack;
    @BindView(R2.id.refund_particulars_status)
    TextView refundParticularsStatus;
    @BindView(R2.id.refund_particulars_time)
    TextView refundParticularsTime;
    @BindView(R2.id.refund_particulars_name)
    TextView refundParticularsName;
    @BindView(R2.id.refund_particulars_phone)
    TextView refundParticularsPhone;
    @BindView(R2.id.refund_particulars_address)
    TextView refundParticularsAddress;
    @BindView(R2.id.refund_particulars_total_money)
    TextView refundParticularsTotalMoney;
    @BindView(R2.id.refund_particulars_price)
    TextView refundParticularsPrice;
    @BindView(R2.id.refund_succeed_total)
    LinearLayout refundSucceedTotal;
    @BindView(R2.id.refund_particulars_path)
    TextView refundParticularsPath;
    @BindView(R2.id.refund_particulars_price1)
    TextView refundParticularsPrice1;
    @BindView(R2.id.refund_succeed_path)
    LinearLayout refundSucceedPath;
    @BindView(R2.id.waiting_refund)
    LinearLayout waitingRefund;
    @BindView(R2.id.refund_particulars_image)
    SimpleDraweeView refundParticularsImage;
    @BindView(R2.id.refund_particulars_goods_name)
    TextView refundParticularsGoodsName;
    @BindView(R2.id.refund_particulars_count)
    TextView refundParticularsCount;
    @BindView(R2.id.alteration_rec_colour)
    TextView alterationRecColour;
    @BindView(R2.id.alteration_rec_size)
    TextView alterationRecSize;
    @BindView(R2.id.refund_particulars_reason)
    TextView refundParticularsReason;
    @BindView(R2.id.refund_particulars_amount)
    TextView refundParticularsAmount;
    @BindView(R2.id.refund_particulars_time_application)
    TextView refundParticularsTimeApplication;
    @BindView(R2.id.refund_particulars_contact_seller)
    LinearLayout refundParticularsContactSeller;
    @BindView(R2.id.refund_particulars_dial)
    LinearLayout refundParticularsDial;
    @BindView(R2.id.refund_particulars_consult_customer_service)
    LinearLayout refundParticularsConsultCustomerService;

    @Override
    public int getLayoutId() {
        return R.layout.activity_refund_particulars;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        //联系卖家
        refundParticularsContactSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("18818814558");
            }
        });
        //咨询客服
        refundParticularsConsultCustomerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("40083312345");
            }
        });
    }

    @Override
    public RefundParticularsView createView() {
        return this;
    }

    @Override
    public RefundParticularsPresenter createPresenter() {
        return new RefundParticularsPresenter(this);
    }

    //调起电话
    private void call(String call) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + call));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //开启系统拨号器
        startActivity(intent);

    }

}
