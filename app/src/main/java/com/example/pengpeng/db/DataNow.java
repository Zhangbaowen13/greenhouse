package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

import java.security.Timestamp;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class DataNow extends DataSupport {
    private int huanwenId;
    private int huanshiId;
    private int guangzhaoId;
    private int eryanghuatanId;
    private int tuwenId;
    private int tushiId;
    private int id;
    private int greenhouseId;
    private int picture;
    private boolean isnew;

    public int getHuanwenId() {
        return huanwenId;
    }

    public void setHuanwenId(int huanwenId) {
        this.huanwenId = huanwenId;
    }

    public int getHuanshiId() {
        return huanshiId;
    }

    public void setHuanshiId(int huanshiId) {
        this.huanshiId = huanshiId;
    }

    public int getGuangzhaoId() {
        return guangzhaoId;
    }

    public void setGuangzhaoId(int guangzhaoId) {
        this.guangzhaoId = guangzhaoId;
    }

    public int getEryanghuatanId() {
        return eryanghuatanId;
    }

    public void setEryanghuatanId(int eryanghuatanId) {
        this.eryanghuatanId = eryanghuatanId;
    }

    public int getTuwenId() {
        return tuwenId;
    }

    public void setTuwenId(int tuwenId) {
        this.tuwenId = tuwenId;
    }

    public int getTushiId() {
        return tushiId;
    }

    public void setTushiId(int tushiId) {
        this.tushiId = tushiId;
    }

    public boolean isnew() {
        return isnew;
    }

    public void setIsnew(boolean isnew) {
        this.isnew = isnew;
    }

    public int getGreenhouseId() {
        return greenhouseId;
    }

    public void setGreenhouseId(int greenhouseId) {
        this.greenhouseId = greenhouseId;
    }

    public int getPicture() {return picture;}

    public void setPicture(int picture) {this.picture = picture;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
