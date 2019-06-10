package com.example.net;

import java.io.Serializable;

public class orderAddress implements Serializable {
    private long sellerId;

    public orderAddress(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "orderAddress{" +
                "sellerId='" + sellerId + '\'' +
                '}';
    }
}
