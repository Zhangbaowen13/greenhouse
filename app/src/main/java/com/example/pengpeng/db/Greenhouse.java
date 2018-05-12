package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class Greenhouse extends DataSupport {
    private String greenhouseName;
    private int id;
    private String greenhouseNumb;
    private String provinceName;
    private String cityName;
    private String countyName;
    private String xiangxiaddress;

    public String getGreenhouseName() {
        return greenhouseName;
    }

    public void setGreenhouseName(String greenhouseName) {
        this.greenhouseName = greenhouseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGreenhouseNumb() {
        return greenhouseNumb;
    }

    public void setGreenhouseNumb(String greenhouseNumb) {
        this.greenhouseNumb = greenhouseNumb;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getXiangxiaddress() {
        return xiangxiaddress;
    }

    public void setXiangxiaddress(String xiangxiaddress) {
        this.xiangxiaddress = xiangxiaddress;
    }
}
