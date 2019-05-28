package com.example.browsinghistory.bean;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class BrowsingHistoryParentBean {
    private boolean isCheck;
    private String time;

    public BrowsingHistoryParentBean(boolean isCheck, String time) {
        this.isCheck = isCheck;
        this.time = time;
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
}
