package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class Datashow extends DataSupport {
    private String huanwen;
    private String huanshi;
    private String guangzhao;
    private String eryanghuatan;
    private String tuwen;
    private String tushi;
    private String greenhouseId;
    private String userId;
    private int picture;
    private String isnew;
    private String updatetime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsnew() {
        return isnew;
    }

    public void setIsnew(String isnew) {
        this.isnew = isnew;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getEryanghuatan() {
        return eryanghuatan;
    }

    public void setEryanghuatan(String eryanghuatan) {
        this.eryanghuatan = eryanghuatan;
    }

    public String getGreenhouseId() {
        return greenhouseId;
    }

    public void setGreenhouseId(String greenhouseId) {
        this.greenhouseId = greenhouseId;
    }

    public String getGuangzhao() {
        return guangzhao;
    }

    public void setGuangzhao(String guangzhao) {
        this.guangzhao = guangzhao;
    }

    public String getHuanshi() {
        return huanshi;
    }

    public void setHuanshi(String huanshi) {
        this.huanshi = huanshi;
    }

    public String getHuanwen() {
        return huanwen;
    }

    public void setHuanwen(String huanwen) {
        this.huanwen = huanwen;
    }

    public String getTushi() {
        return tushi;
    }

    public void setTushi(String tushi) {
        this.tushi = tushi;
    }

    public String getTuwen() {
        return tuwen;
    }

    public void setTuwen(String tuwen) {
        this.tuwen = tuwen;
    }
}
