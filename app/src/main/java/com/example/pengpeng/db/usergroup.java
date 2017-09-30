package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class UserGroup extends DataSupport {
    private int id;
    private int userId;
    private String groupnumber;
    private int greenhouseId;
    private boolean iszuzhang;
    private int fanganId;


    public int getFanganId() {
        return fanganId;
    }

    public void setFanganId(int fanganId) {
        this.fanganId = fanganId;
    }

    public int getGreenhouseId() {
        return greenhouseId;
    }

    public void setGreenhouseId(int greenhouseId) {
        this.greenhouseId = greenhouseId;
    }

    public String getGroupnumber() {
        return groupnumber;
    }

    public void setGroupnumber(String groupnumber) {
        this.groupnumber = groupnumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean iszuzhang() {
        return iszuzhang;
    }

    public void setIszuzhang(boolean iszuzhang) {
        this.iszuzhang = iszuzhang;
    }
}
