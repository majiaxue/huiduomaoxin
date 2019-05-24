package com.example.entity;

public class CouponBean {
    private String money;
    private String demand;
    private String time;
    private boolean isHas;

    public CouponBean(String money, String demand, String time, boolean isHas) {
        this.money = money;
        this.demand = demand;
        this.time = time;
        this.isHas = isHas;
    }

    public void setHas(boolean has) {
        isHas = has;
    }

    public String getMoney() {
        return money;
    }

    public String getDemand() {
        return demand;
    }

    public String getTime() {
        return time;
    }

    public boolean isHas() {
        return isHas;
    }

    @Override
    public String toString() {
        return "CouponBean{" +
                "money='" + money + '\'' +
                ", demand='" + demand + '\'' +
                ", time='" + time + '\'' +
                ", isHas=" + isHas +
                '}';
    }
}
