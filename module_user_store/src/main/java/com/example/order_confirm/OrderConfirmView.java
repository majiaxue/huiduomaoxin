package com.example.order_confirm;

import com.example.bean.OrderConfirmBean;
import com.example.bean.PostageBean;
import com.example.bean.ShippingAddressBean;
import com.example.mvp.IView;

public interface OrderConfirmView extends IView {

    void noAddress();

    void loadAddress(ShippingAddressBean addressBean);

    void loadPostage(PostageBean postageBean);
}
