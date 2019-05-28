package com.example.shippingaddress.address;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

/**
 * 新建收货地址
 */
@Route(path = "/module_user_mine/AddressActivity")
public class AddressActivity extends BaseActivity<AddressView, AddressPresenter> implements AddressView {

    @BindView(R2.id.address_image_back)
    ImageView addressImageBack;
    @BindView(R2.id.address_name)
    EditText addressName;
    @BindView(R2.id.address_phone)
    EditText addressPhone;
    @BindView(R2.id.address_where)
    TextView addressWhere;
    @BindView(R2.id.address_detailed)
    EditText addressDetailed;
    @BindView(R2.id.address_home)
    RadioButton addressHome;
    @BindView(R2.id.address_company)
    RadioButton addressCompany;
    @BindView(R2.id.address_school)
    RadioButton addressSchool;
    @BindView(R2.id.address_radio)
    RadioGroup addressRadio;
    @BindView(R2.id.address_switch)
    Switch addressSwitch;
    @BindView(R2.id.address_save)
    TextView addressSave;

    @Override
    public int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        addressImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public AddressView createView() {
        return this;
    }

    @Override
    public AddressPresenter createPresenter() {
        return new AddressPresenter(this);
    }

}
