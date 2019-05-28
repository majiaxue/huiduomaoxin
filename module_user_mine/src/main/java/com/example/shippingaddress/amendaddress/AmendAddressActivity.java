package com.example.shippingaddress.amendaddress;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.PopUtils;
import com.example.view.SelfDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改地址
 */
@Route(path = "/module_user_mine/AmendAddressActivity")
public class AmendAddressActivity extends BaseActivity<AmendAddressView, AmendAddressPresenter> implements AmendAddressView {


    @BindView(R2.id.amend_address_image_back)
    ImageView amendAddressImageBack;
    @BindView(R2.id.amend_address_delete)
    TextView amendAddressDelete;
    @BindView(R2.id.amend_address_name)
    EditText amendAddressName;
    @BindView(R2.id.amend_address_phone)
    EditText amendAddressPhone;
    @BindView(R2.id.amend_address_where)
    TextView amendAddressWhere;
    @BindView(R2.id.amend_address_detailed)
    EditText amendAddressDetailed;
    @BindView(R2.id.amend_address_home)
    RadioButton amendAddressHome;
    @BindView(R2.id.amend_address_company)
    RadioButton amendAddressCompany;
    @BindView(R2.id.amend_address_school)
    RadioButton amendAddressSchool;
    @BindView(R2.id.amend_address_radio)
    RadioGroup amendAddressRadio;
    @BindView(R2.id.amend_address_switch)
    Switch amendAddressSwitch;
    @BindView(R2.id.amend_address_save)
    TextView amendAddressSave;

    @Override
    public int getLayoutId() {
        return R.layout.activity_amend_address;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        amendAddressImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //删除
        amendAddressDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除地址
                final SelfDialog selfDialog = new SelfDialog(AmendAddressActivity.this);
                selfDialog.setTitle("提示");
                selfDialog.setMessage("您确定要删除此地址吗？");
                selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        Toast.makeText(AmendAddressActivity.this, "还不能取消关注", Toast.LENGTH_SHORT).show();
                        selfDialog.dismiss();
                        PopUtils.setTransparency(AmendAddressActivity.this,1f);
                    }
                });
                selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        selfDialog.dismiss();
                        PopUtils.setTransparency(AmendAddressActivity.this,1f);
                    }
                });
                PopUtils.setTransparency(AmendAddressActivity.this,0.3f);
                selfDialog.show();
            }
        });
    }

    @Override
    public AmendAddressView createView() {
        return this;
    }

    @Override
    public AmendAddressPresenter createPresenter() {
        return new AmendAddressPresenter(this);
    }

}
