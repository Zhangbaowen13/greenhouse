package com.example.pengpeng;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.idescout.sql.SqlScoutServer;

public class MainActivity extends AppCompatActivity {
private Button chooseAreaButton;
    private final long SPLASH_LENGTH=2000;
    Handler handler=new Handler();
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqlScoutServer.create(this, getPackageName());
        prefs = getSharedPreferences("phone", Context.MODE_PRIVATE);
        final Intent intent=new Intent();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //if (prefs.getBoolean("firststart", true)) {
                    editor = prefs.edit();
                    //将登录标志位设置为false，下次登录时不在显示首次登录界面
                    editor.putBoolean("firststart", false);
                    editor.commit();
                    intent.setClass(MainActivity.this,LoginActivity.class);
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
               /* }else{
                    intent.setClass(MainActivity.this,AreaActivity.class);
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
                }*/
            }
        },SPLASH_LENGTH);

        chooseAreaButton=(Button) findViewById(R.id.tiaozhuan_bt);
        chooseAreaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (prefs.getBoolean("firststart", true)) {
                    editor = prefs.edit();
                    //将登录标志位设置为false，下次登录时不在显示首次登录界面
                    editor.putBoolean("firststart", false);
                    editor.commit();
                    intent.setClass(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
               /* }else{
                    intent.setClass(MainActivity.this,AreaActivity.class);
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
              } */
        }
        });
    }
}
