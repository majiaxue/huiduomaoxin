package com.example.order_confirm;

import com.example.bean.AddressBean;
import com.example.bean.OrderConfirmBean;
import com.example.mvp.IView;

public interface OrderConfirmView extends IView {

    void noAddress();

    void loadAddress(AddressBean addressBean);

    void reviseCount(OrderConfirmBean orderConfirmBean);
}
