package com.example.pengpeng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pengpeng.db.Xueyuan;
import com.example.pengpeng.db.Zhuanye;

public class ZhuanyeManagerActivity extends AppCompatActivity {
private EditText xueyuan_et;
    private EditText zhuanye_et;
    private Button add_xueyuan;
    private Button add_zhuanye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanye_manager);
        //初始化控件
        xueyuan_et=(EditText)findViewById(R.id.xueyuan_et);
        zhuanye_et=(EditText)findViewById(R.id.zhuanye_et);
        add_xueyuan=(Button)findViewById(R.id.add_xueyuan);
        add_zhuanye=(Button)findViewById(R.id.add_zhuanye);

        add_xueyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xueyuan=xueyuan_et.getText().toString();
                Xueyuan xueyuan1=new Xueyuan();
                xueyuan1.setXueyuan(xueyuan);
                xueyuan1.save();
            }
        });
        add_zhuanye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xueyuan=xueyuan_et.getText().toString();
                String zhuanye=zhuanye_et.getText().toString();
                Zhuanye zhuanye1=new Zhuanye();
                zhuanye1.setXueyuan(xueyuan);
                zhuanye1.setZhuanye(zhuanye);
                zhuanye1.save();
            }
        });
    }
}
