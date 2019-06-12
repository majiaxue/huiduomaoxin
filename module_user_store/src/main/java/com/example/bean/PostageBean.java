package com.example.bean;

public class PostageBean {

    /**
     * total : 2
     * feight : 0
     * isPinkage : 0
     */

    private double total;
    private double feight;
    private Integer quantity;
    private int isPinkage;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getFeight() {
        return feight;
    }

    public void setFeight(double feight) {
        this.feight = feight;
    }

    public int getIsPinkage() {
        return isPinkage;
    }

    public void setIsPinkage(int isPinkage) {
        this.isPinkage = isPinkage;
    }

    @Override
    public String toString() {
        return "PostageBean{" +
                "total=" + total +
                ", feight=" + feight +
                ", quantity='" + quantity + '\'' +
                ", isPinkage=" + isPinkage +
                '}';
    }
}
