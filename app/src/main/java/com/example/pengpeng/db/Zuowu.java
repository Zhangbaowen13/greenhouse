package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/10/8 0008.
 */

public class Zuowu extends DataSupport {
    private int id;
    private String zuowu;
    private String zuowuNumb;

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
}
