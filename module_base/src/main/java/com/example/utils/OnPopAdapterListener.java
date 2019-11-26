package com.example.utils;

import android.widget.PopupWindow;

import com.example.adapter.PopCouponAdapter;

public interface OnPopAdapterListener {
    void setOnAdapterListener(final PopupWindow popupWindow, final PopCouponAdapter adapter);
}
