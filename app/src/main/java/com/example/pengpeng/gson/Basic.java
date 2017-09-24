package com.example.pengpeng.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/9/23 0023.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;
    public Update update;
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
