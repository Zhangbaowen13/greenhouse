package com.example.pengpeng;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2017/10/7 0007.
 */

public class AlarmCloseShebeiReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.e("AlarmReceiver","您的设备已定时关闭~");
    }
}
