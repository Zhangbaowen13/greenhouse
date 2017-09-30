package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class Shebei extends DataSupport {
    private String shebeiName;
    private int id;
    private boolean isopen;
    private int greenhouseId;


    public String getShebeiName() {
        return shebeiName;
    }

    public void setShebeiName(String shebeiName) {
        this.shebeiName = shebeiName;
    }

    public int getGreenhouseId() {
        return greenhouseId;
    }

    public void setGreenhouseId(int greenhouseId) {
        this.greenhouseId = greenhouseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isopen() {return isopen;}

    public void setIsopen(boolean isopen) {this.isopen = isopen;}
}
