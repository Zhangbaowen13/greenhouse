package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class Guangzhao extends DataSupport {
    private int id;
    private int chuanganqiId;
    public String guangzhaoData;
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

    public String getGuangzhaoData() {
        return guangzhaoData;
    }

    public void setGuangzhaoData(String guangzhaoData) {
        this.guangzhaoData = guangzhaoData;
    }

    public String getDataname() {
        return dataname;
    }

    public void setDataname(String dataname) {
        this.dataname = dataname;
    }
}
