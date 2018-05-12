package com.example.pengpeng.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class User extends DataSupport {
    private int id;
    private String phone;
    private String xuehao;
    private String userName;
    private String password;
    private String leibie;
    private String xueyuan;
    private String xuezhi;
    private  String zhuanye;
    private String daoshi;
    public String getXuehao() {
        return xuehao;
    }
    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getLeibie() {
        return leibie;
    }
    public void setLeibie(String leibie) {
        this.leibie = leibie;
    }
    public String getXueyuan() {
        return xueyuan;
    }
    public void setXueyuan(String xueyuan) {
        this.xueyuan = xueyuan;
    }
    public String getXuezhi() {
        return xuezhi;
    }
    public void setXuezhi(String xuezhi) {
        this.xuezhi = xuezhi;
    }
    public String getZhuanye() {
        return zhuanye;
    }
    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
    }
    public String getDaoshi() {
        return daoshi;
    }
    public void setDaoshi(String daoshi) {
        this.daoshi = daoshi;
    }
}
