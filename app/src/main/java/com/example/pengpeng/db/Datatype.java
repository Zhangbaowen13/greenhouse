package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/12/7 0007.
 */

public class Datatype extends DataSupport {
    private int id;
    public String dataname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataname() {
        return dataname;
    }

    public void setDataname(String dataname) {
        this.dataname = dataname;
    }
}
