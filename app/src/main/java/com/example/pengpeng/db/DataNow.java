package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

import java.security.Timestamp;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class DataNow extends DataSupport {
    private String chuanganqiId;
    public String shuju;
    public String dataname;
    private String updatetime;
    private int id;
    private String greenhouseId;
    private int picture;
    private String isnew;

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getChuanganqiId() {
        return chuanganqiId;
    }

    public void setChuanganqiId(String chuanganqiId) {
        this.chuanganqiId = chuanganqiId;
    }

    public String getDataname() {
        return dataname;
    }

    public void setDataname(String dataname) {
        this.dataname = dataname;
    }

    public String getShuju() {
        return shuju;
    }

    public void setShuju(String shuju) {
        this.shuju = shuju;
    }

    public String getIsnew() {
        return isnew;
    }

    public void setIsnew(String isnew) {
        this.isnew = isnew;
    }

    public String getGreenhouseId() {
        return greenhouseId;
    }

    public void setGreenhouseId(String greenhouseId) {
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
