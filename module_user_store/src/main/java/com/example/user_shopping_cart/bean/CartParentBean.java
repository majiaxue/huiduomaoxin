package com.example.user_shopping_cart.bean;

/**
 * Created by cuihaohao on 2019/5/24
 * Describe:
 */
public class CartParentBean {
    private boolean isCheck;
    private String name;

    public CartParentBean(boolean isCheck, String name) {
        this.isCheck = isCheck;
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
