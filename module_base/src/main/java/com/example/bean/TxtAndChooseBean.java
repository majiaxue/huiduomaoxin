package com.example.bean;

public class TxtAndChooseBean {
    private String title;
    private boolean isChoose;

    public TxtAndChooseBean(String title, boolean isChoose) {
        this.title = title;
        this.isChoose = isChoose;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    @Override
    public String toString() {
        return "TxtAndChooseBean{" +
                "title='" + title + '\'' +
                ", isChoose=" + isChoose +
                '}';
    }
}
