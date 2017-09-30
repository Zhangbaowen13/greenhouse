package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class Huanshi extends DataSupport {
    private int id;
    private int chuanganqiId;
    public String huanshiData;
    public String dataname;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChuanganqiId() {
        return chuanganqiId;
    }

    public void setChuanganqiId(int chuanganqiId) {
        this.chuanganqiId = chuanganqiId;
    }

    public String getHuanshiData() {
        return huanshiData;
    }

    public void setHuanshiData(String huanshiData) {
        this.huanshiData = huanshiData;
    }

    public String getDataname() {
        return dataname;
    }

    public void setDataname(String dataname) {
        this.dataname = dataname;
    }
}
