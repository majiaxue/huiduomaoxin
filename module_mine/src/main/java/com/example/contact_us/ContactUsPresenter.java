package com.example.contact_us;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class ContactUsPresenter extends BasePresenter<ContactUsView> {
    public ContactUsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
