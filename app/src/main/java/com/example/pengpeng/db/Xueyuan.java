package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/10/6 0006.
 */

public class Xueyuan extends DataSupport{
    private int id;
    private String xueyuan;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getXueyuan() {
        return xueyuan;
    }

    public void setXueyuan(String xueyuan) {
        this.xueyuan = xueyuan;
    }
}
