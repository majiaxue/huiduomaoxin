package com.example.logisticsinformation;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 物流信息
 */
public class LogisticsInformationActivity extends BaseActivity<LogisticsInformationView, LogisticsInformationPresenter> implements LogisticsInformationView {


    @BindView(R2.id.logistics_information_back)
    ImageView logisticsInformationBack;
    @BindView(R2.id.logistics_information_image)
    SimpleDraweeView logisticsInformationImage;
    @BindView(R2.id.logistics_information_status)
    TextView logisticsInformationStatus;
    @BindView(R2.id.logistics_information_type)
    TextView logisticsInformationType;
    @BindView(R2.id.logistics_information_official)
    TextView logisticsInformationOfficial;
    @BindView(R2.id.logistics_information_message_rec)
    RecyclerView logisticsInformationMessageRec;
    @BindView(R2.id.logistics_information_rec)
    RecyclerView logisticsInformationRec;

    @Override
    public int getLayoutId() {
        return R.layout.activity_logistics_information;
    }

    @Override
    public void initData() {
        //物流信息
        presenter.logisticsInformationMessageRec(logisticsInformationMessageRec);
        //推荐
        presenter.logisticsInformationRec(logisticsInformationRec);
    }

    @Override
    public void initClick() {
        logisticsInformationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

}
