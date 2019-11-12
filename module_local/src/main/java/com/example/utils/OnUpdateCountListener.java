package com.example.utils;

import com.example.bean.LocalCartBean;

import java.util.List;

public interface OnUpdateCountListener {
    void shopCart(List<LocalCartBean.InsideCart> localShopcarList);
}
