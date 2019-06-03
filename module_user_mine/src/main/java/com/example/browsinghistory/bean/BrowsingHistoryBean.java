package com.example.browsinghistory.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class BrowsingHistoryBean {

    private List<ParentBean> data;

    public List<ParentBean> getData() {
        return data;
    }

    public void setData(List<ParentBean> data) {
        this.data = data;
    }

    public BrowsingHistoryBean(List<ParentBean> data) {
        this.data = data;
    }

    public static class ParentBean {
        private boolean isCheck;
        private String time;
        private List<ChildBean> childList;

        public ParentBean(boolean isCheck, String time, List<ChildBean> childList) {
            this.isCheck = isCheck;
            this.time = time;
            this.childList = childList;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<ChildBean> getChildList() {
            return childList;
        }

        public void setChildList(List<ChildBean> childList) {
            this.childList = childList;
        }

        public static  class ChildBean{
            private int image;
            private String name;
            private String price;
            private String payment_amount;
            private String good_reputation;
            private String shop;
            private boolean isCheck;

            public ChildBean(int image, String name, String price, String payment_amount, String good_reputation, String shop, boolean isCheck) {
                this.image = image;
                this.name = name;
                this.price = price;
                this.payment_amount = payment_amount;
                this.good_reputation = good_reputation;
                this.shop = shop;
                this.isCheck = isCheck;
            }

            public int getImage() {
                return image;
            }

            public void setImage(int image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPayment_amount() {
                return payment_amount;
            }

            public void setPayment_amount(String payment_amount) {
                this.payment_amount = payment_amount;
            }

            public String getGood_reputation() {
                return good_reputation;
            }

            public void setGood_reputation(String good_reputation) {
                this.good_reputation = good_reputation;
            }

            public String getShop() {
                return shop;
            }

            public void setShop(String shop) {
                this.shop = shop;
            }

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }
        }
    }
}
