package com.example.logisticsinformation;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.logisticsinformation.bean.LogisticsInforMationBean;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.LogUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 物流信息
 */
@Route(path = "/module_user_mine/LogisticsInformationActivity")
public class LogisticsInformationActivity extends BaseActivity<LogisticsInformationView, LogisticsInformationPresenter> implements LogisticsInformationView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.logistics_information_image)
    SimpleDraweeView logisticsInformationImage;
    @BindView(R2.id.logistics_information_status)
    TextView logisticsInformationStatus;
    @BindView(R2.id.logistics_information_type)
    TextView logisticsInformationType;
    @BindView(R2.id.logistics_information_official)
    LinearLayout logisticsInformationOfficial;
    @BindView(R2.id.logistics_information_message_rec)
    RecyclerView logisticsInformationMessageRec;
    @BindView(R2.id.logistics_information_rec)
    RecyclerView logisticsInformationRec;
    @BindView(R2.id.logistics_information_express_type)
    TextView logisticsInformationExpressType;
    @BindView(R2.id.logistics_information_copy)
    ImageView logisticsInformationCopy;
    @BindView(R2.id.logistics_information_linear)
    LinearLayout logisticsInformationLinear;
    @BindView(R2.id.logistics_information_official_phone)
    TextView logisticsInformationOfficialPhone;

    @Autowired(name = "orderSn")
    String orderSn;
    @Autowired(name = "goodsImage")
    String goodsImage;


    @Override
    public int getLayoutId() {
        return R.layout.activity_logistics_information;
    }

    @Override
    public void initData() {
        includeTitle.setText("物流信息");
        ARouter.getInstance().inject(this);

        logisticsInformationImage.setImageURI(goodsImage);

        //物流信息
        presenter.logisticsInformationMessageRec(logisticsInformationMessageRec, orderSn);
        //推荐
        presenter.logisticsInformationRec(logisticsInformationRec);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        logisticsInformationOfficial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(logisticsInformationOfficialPhone.getText().toString());
            }
        });
        logisticsInformationCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clip = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                clip.setText(logisticsInformationType.getText().toString()); // 复制
//                clip.getText(); // 粘贴
                Toast.makeText(LogisticsInformationActivity.this, "已复制到粘贴板", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public LogisticsInformationView createView() {
        return this;
    }

    @Override
    public LogisticsInformationPresenter createPresenter() {
        return new LogisticsInformationPresenter(this);
    }

    @Override
    public void traces(LogisticsInforMationBean inforMationBeanList, int size) {
        logisticsInformationType.setText(inforMationBeanList.getLogisticCode());
        logisticsInformationExpressType.setText(inforMationBeanList.getShipperCode());
        LogUtil.e("公司----->" + inforMationBeanList.getShipperCode());
        String acceptStation = inforMationBeanList.getTraces().get(size - 1).getAcceptStation();
        LogUtil.e("信息" + acceptStation);
        if (acceptStation.indexOf("已签收") != -1) {
            logisticsInformationStatus.setText("已签收");
        } else {
            logisticsInformationStatus.setText("运输中");
        }

    }

    //调起电话
    private void call(String call) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + call));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //开启系统拨号器
        startActivity(intent);
    }
}
