package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/10/8 0008.
 */

public class Zhuanjiaxitong extends DataSupport {
    private int id;
    private String zuowuNumb;
    private String zuowu;
    private String shiqi;
    private String huanwenMax;
    private String huanwenMin;
    private String huanshiMax;
    private String huanshiMin;
    private String guangzhaoMax;
    private String guangzhaoMin;
    private String eryanghuatanMax;
    private String eryanghuatanMin;
    private String tuwenMax;
    private String tuwenMin;
    private String tushiMax;
    private String tushiMin;
    private String bingchonghai;

    public String getZuowuNumb() {
        return zuowuNumb;
    }

    public void setZuowuNumb(String zuowuNumb) {
        this.zuowuNumb = zuowuNumb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZuowu() {
        return zuowu;
    }

    public void setZuowu(String zuowu) {
        this.zuowu = zuowu;
    }

    public String getShiqi() {
        return shiqi;
    }

    public void setShiqi(String shiqi) {
        this.shiqi = shiqi;
    }

    public void setHuanwenMax(String huanwenMax) {
        this.huanwenMax = huanwenMax;
    }

    public String getHuanwenMax() {
        return huanwenMax;
    }

    public void setHuanwenMin(String huanwenMin) {
        this.huanwenMin = huanwenMin;
    }

    public String getHuanwenMin() {
        return huanwenMin;
    }

    public String getHuanshiMax() {
        return huanshiMax;
    }

    public void setHuanshiMin(String huanshiMin) {
        this.huanshiMin = huanshiMin;
    }

    public void setHuanshiMax(String huanshiMax) {
        this.huanshiMax = huanshiMax;
    }

    public String getHuanshiMin() {
        return huanshiMin;
    }

    public String getGuangzhaoMax() {
        return guangzhaoMax;
    }

    public String getGuangzhaoMin() {
        return guangzhaoMin;
    }

    public void setGuangzhaoMin(String guangzhaoMin) {
        this.guangzhaoMin = guangzhaoMin;
    }

    public void setGuangzhaoMax(String guangzhaoMax) {
        this.guangzhaoMax = guangzhaoMax;
    }

    public void setEryanghuatanMin(String eryanghuatanMin) {this.eryanghuatanMin = eryanghuatanMin;}

    public void setEryanghuatanMax(String eryanghuatanMax) {this.eryanghuatanMax = eryanghuatanMax;}

    public String getEryanghuatanMin() {
        return eryanghuatanMin;
    }

    public String getEryanghuatanMax() {
        return eryanghuatanMax;
    }

    public void setTushiMin(String tushiMin) {
        this.tushiMin = tushiMin;
    }

    public String getTushiMin() {
        return tushiMin;
    }

    public String getTushiMax() {
        return tushiMax;
    }

    public void setTushiMax(String tushiMax) {
        this.tushiMax = tushiMax;
    }

    public String getTuwenMax() {
        return tuwenMax;
    }

    public void setTuwenMax(String tuwenMax) {
        this.tuwenMax = tuwenMax;
    }

    public String getTuwenMin() {
        return tuwenMin;
    }

    public void setTuwenMin(String tuwenMin) {
        this.tuwenMin = tuwenMin;
    }

    public String getBingchonghai() {
        return bingchonghai;
    }

    public void setBingchonghai(String bingchonghai) {
        this.bingchonghai = bingchonghai;
    }
}
