package com.example.bean;

import java.util.List;

public class RegionBean {

    private String letter;
    private List<String> regionList;

    public RegionBean() {
    }

    public RegionBean(String letter, List<String> regionList) {
        this.letter = letter;
        this.regionList = regionList;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public List<String> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<String> regionList) {
        this.regionList = regionList;
    }
}
