package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class City extends DataSupport {
    private int id;
    private String cityName;
    private int cityCode;
    private int provinceId;
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public int getCityCode() {return cityCode;}
    public void setCityCode(int cityCode) {this.cityCode = cityCode;}
    public String getCityName() {return cityName;}
    public void setCityName(String cityName) {this.cityName = cityName;}
    public int getProvinceId() {return provinceId;}
    public void setProvinceId(int provinceId) {this.provinceId = provinceId;}
}
