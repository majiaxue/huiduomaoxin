package com.example.productdetail;

import com.example.mvp.IView;
import com.example.productdetail.adapter.ProductAccountAdapter;

public interface ProductDetailView extends IView {
    void loadRv(ProductAccountAdapter adapter);
}
