package com.example.pengpeng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pengpeng.db.Zhuanjiaxitong;
import com.example.pengpeng.db.Zuowu;

public class ZhuanjiaManageActivity extends AppCompatActivity {
    private Button add_zhuanjia;
    private EditText huanwenMax_Text;
    private EditText huanwenMin_Text;
    private EditText huanshiMax_Text;
    private EditText huanshiMin_Text;
    private EditText tuwenMax_Text;
    private EditText tuwenMin_Text;
    private EditText tushiMax_Text;
    private EditText tushiMin_Text;
    private EditText guangzhaoMax_Text;
    private EditText guangzhaoMin_Text;
    private EditText eryanghuatanMax_Text;
    private EditText eryanghuatanMin_Text;
    private EditText zuowu_et;
    private EditText shiqi_et;
    private EditText zuowunumb_et;
    private EditText bingchonghai_et;
    private Button add_zuowu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanjia_manage);

        huanwenMax_Text=(EditText)findViewById(R.id.huanwenMax_et);
        huanwenMin_Text=(EditText)findViewById(R.id.huanwenMin_et);
        huanshiMax_Text=(EditText)findViewById(R.id.huanshiMax_et);
        huanshiMin_Text=(EditText)findViewById(R.id.huanshiMin_et);
        tuwenMax_Text=(EditText)findViewById(R.id.tuwenMax_et);
        tuwenMin_Text=(EditText)findViewById(R.id.tuwenMin_et);
        tushiMax_Text=(EditText)findViewById(R.id.tushiMax_et);
        tushiMin_Text=(EditText)findViewById(R.id.tushiMin_et);
        guangzhaoMax_Text=(EditText)findViewById(R.id.guangzhaoMax_et);
        guangzhaoMin_Text=(EditText)findViewById(R.id.guangzhaoMin_et);
        eryanghuatanMax_Text=(EditText)findViewById(R.id.eryanghuatanMax_et);
        eryanghuatanMin_Text=(EditText)findViewById(R.id.eryanghuatanMin_et);
        add_zhuanjia=(Button)findViewById(R.id.add_zhuanjia);
        zuowu_et=(EditText)findViewById(R.id.zuowu_et);
        shiqi_et=(EditText)findViewById(R.id.shiqi_et);
        zuowunumb_et=(EditText)findViewById(R.id.zuowunumb_et);
        bingchonghai_et=(EditText)findViewById(R.id.bingchonghai_et);
        add_zuowu=(Button)findViewById(R.id.add_zuowu);
        add_zuowu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Zuowu zuowu=new Zuowu();
                zuowu.setZuowu(zuowu_et.getText().toString());
                zuowu.setZuowuNumb(zuowunumb_et.getText().toString());
                zuowu.save();
            }
        });
        add_zhuanjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Zhuanjiaxitong zhuanjiaxitong=new Zhuanjiaxitong();
                zhuanjiaxitong.setZuowuNumb(zuowunumb_et.getText().toString());
                zhuanjiaxitong.setZuowu(zuowu_et.getText().toString());
                zhuanjiaxitong.setShiqi(shiqi_et.getText().toString());
                zhuanjiaxitong.setHuanwenMax(huanwenMax_Text.getText().toString());
                zhuanjiaxitong.setHuanwenMin(huanwenMin_Text.getText().toString());
                zhuanjiaxitong.setHuanshiMax(huanshiMax_Text.getText().toString());
                zhuanjiaxitong.setHuanshiMin(huanshiMin_Text.getText().toString());
                zhuanjiaxitong.setTuwenMax(tuwenMax_Text.getText().toString());
                zhuanjiaxitong.setTuwenMin(tuwenMin_Text.getText().toString());
                zhuanjiaxitong.setTushiMax(tushiMax_Text.getText().toString());
                zhuanjiaxitong.setTushiMin(tushiMin_Text.getText().toString());
                zhuanjiaxitong.setGuangzhaoMax(guangzhaoMax_Text.getText().toString());
                zhuanjiaxitong.setGuangzhaoMin(guangzhaoMin_Text.getText().toString());
                zhuanjiaxitong.setEryanghuatanMax(eryanghuatanMax_Text.getText().toString());
                zhuanjiaxitong.setEryanghuatanMin(eryanghuatanMin_Text.getText().toString());
                zhuanjiaxitong.setBingchonghai(bingchonghai_et.getText().toString());
                zhuanjiaxitong.save();
            }
        });
    }
}
