package com.example.refundparticulars;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.alteration.bean.AlterationBean;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

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
    @BindView(R2.id.refund_particulars_productAttr)
    TextView refundParticularsProductAttr;
    @BindView(R2.id.refund_particulars_money)
    TextView refundParticularsMoney;
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

    @Autowired(name = "returnId")
    String returnId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_refund_particulars;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        presenter.initView(returnId);
    }

    @Override
    public void initClick() {
        refundParticularsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    @Override
    public void initView(List<AlterationBean> list) {

        if (list.get(0).getStatus() == 0) {
            refundParticularsStatus.setText("等待商家处理");
            refundSucceedTotal.setVisibility(View.GONE);
            refundSucceedPath.setVisibility(View.GONE);
            waitingRefund.setVisibility(View.VISIBLE);
        } else if (list.get(0).getStatus() == 1) {
            refundParticularsStatus.setText("处理中");
        } else if (list.get(0).getStatus() == 2) {
            refundParticularsStatus.setText("退款成功");
            refundSucceedTotal.setVisibility(View.VISIBLE);
            refundSucceedPath.setVisibility(View.VISIBLE);
            waitingRefund.setVisibility(View.GONE);
        } else {
            refundParticularsStatus.setText("商家已拒绝");

        }
        refundParticularsTime.setText(list.get(0).getReceiveTime());
        refundParticularsName.setText(list.get(0).getReturnName());
        refundParticularsPhone.setText(list.get(0).getReturnPhone());
        refundParticularsAddress.setText(list.get(0).getReceiverRegion() + list.get(0).getReceiverCity() + list.get(0).getReceiverProvince() + list.get(0).getOrderAddress());
        refundParticularsPrice.setText(list.get(0).getProductPrice());
        refundParticularsPath.setText(list.get(0).getPayWay());
        refundParticularsPrice1.setText(list.get(0).getProductPrice());
        refundParticularsImage.setImageURI(list.get(0).getProductPic());
        refundParticularsGoodsName.setText(list.get(0).getProductName());
        refundParticularsCount.setText(list.get(0).getProductCount());
        refundParticularsProductAttr.setText(list.get(0).getProductAttr());
        refundParticularsMoney.setText(list.get(0).getProductPrice());
        refundParticularsReason.setText(list.get(0).getReason());
        refundParticularsAmount.setText(list.get(0).getProductPrice());
        refundParticularsTimeApplication.setText(list.get(0).getCreateTime());
    }
}
