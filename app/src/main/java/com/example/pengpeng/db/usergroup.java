package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class UserGroup extends DataSupport {
    private int id;
    private String userId;
    private String groupnumber;
    private String greenhouseId;
    private String iszuzhang;
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
    public String getTushiMax() {
        return tushiMax;
    }
    public String getTuwenMin() {
        return tuwenMin;
    }
    public void setTushiMax(String tushiMax) {
        this.tushiMax = tushiMax;
    }
    public void setTuwenMin(String tuwenMin) {
        this.tuwenMin = tuwenMin;
    }
    public String getHuanwenMax() {
        return huanwenMax;
    }
    public String getHuanwenMin() {
        return huanwenMin;
    }
    public String getHuanshiMax() {
        return huanshiMax;
    }
    public String getHuanshiMin() {
        return huanshiMin;
    }
    public void setHuanwenMax(String huanwenMax) {
        this.huanwenMax = huanwenMax;
    }
    public void setHuanwenMin(String huanwenMin) {
        this.huanwenMin = huanwenMin;
    }
    public void setHuanshiMax(String huanshiMax) {
        this.huanshiMax = huanshiMax;
    }
    public void setHuanshiMin(String huanshiMin) {
        this.huanshiMin = huanshiMin;
    }
    public String getGuangzhaoMax() {
        return guangzhaoMax;
    }
    public String getGuangzhaoMin() {
        return guangzhaoMin;
    }
    public void setGuangzhaoMax(String guangzhaoMax) {
        this.guangzhaoMax = guangzhaoMax;
    }
    public void setGuangzhaoMin(String guangzhaoMin) {
        this.guangzhaoMin = guangzhaoMin;
    }
    public String getEryanghuatanMax() {
        return eryanghuatanMax;
    }
    public String getEryanghuatanMin() {
        return eryanghuatanMin;
    }
    public void setEryanghuatanMax(String eryanghuatanMax) {
        this.eryanghuatanMax = eryanghuatanMax;
    }
    public void setEryanghuatanMin(String eryanghuatanMin) {
        this.eryanghuatanMin = eryanghuatanMin;
    }
    public String getTuwenMax() {
        return tuwenMax;
    }
    public String getTushiMin() {
        return tushiMin;
    }
    public void setTushiMin(String tushiMin) {
        this.tushiMin = tushiMin;
    }
    public void setTuwenMax(String tuwenMax) {
        this.tuwenMax = tuwenMax;
    }
    public String getGreenhouseId() {
        return greenhouseId;}
    public void setGreenhouseId(String greenhouseId) {
        this.greenhouseId = greenhouseId;
    }
    public String getGroupnumber() {
        return groupnumber;
    }
    public void setGroupnumber(String groupnumber) {
        this.groupnumber = groupnumber;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {this.userId = userId;}
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getIszuzhang() {
        return iszuzhang;
    }
    public void setIszuzhang(String iszuzhang) {
        this.iszuzhang = iszuzhang;
    }
}
