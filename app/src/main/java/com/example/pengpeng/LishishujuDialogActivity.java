package com.example.pengpeng;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LishishujuDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lishishuju_dialog);
        final ImageView lishishuju = (ImageView) findViewById(R.id.lc_lishishuju);
        RadioButton rbHuanwen = (RadioButton) findViewById(R.id.rb_huanwen);
        rbHuanwen.setChecked(true);
        Button shangyiye = (Button) findViewById(R.id.shangye);
        Button xiayiye = (Button) findViewById(R.id.xiaye);
        final TextView ycSrc = (TextView) findViewById(R.id.ycsrc);
        ycSrc.setText("11");
        Toolbar toolbar = (Toolbar) findViewById(R.id.lishishuju_toolbar);
        setSupportActionBar(toolbar);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        ViewGroup.LayoutParams lp = lishishuju.getLayoutParams();
        lp.width = screenWidth;
        lp.height = ActionBar.LayoutParams.WRAP_CONTENT;
        lishishuju.setLayoutParams(lp);

        lishishuju.setMaxWidth(screenWidth * 2/3);
        // lishishuju.setMaxHeight(screenWidth * 5);
        shangyiye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ycSrc.getText().toString().equals("11")) {
                    lishishuju.setImageResource(R.mipmap.lishishuju2);
                    ycSrc.setText("12");
                } else if (ycSrc.getText().toString().equals("12")) {
                    lishishuju.setImageResource(R.mipmap.lishishuju3);
                    ycSrc.setText("13");
                } else if (ycSrc.getText().toString().equals("13")) {
                    lishishuju.setImageResource(R.mipmap.lishishuju4);
                    ycSrc.setText("14");
                } else if (ycSrc.getText().toString().equals("14")) {
                    lishishuju.setImageResource(R.mipmap.lishishuju5);
                    ycSrc.setText("15");
                } else if (ycSrc.getText().toString().equals("15")) {
                    lishishuju.setImageResource(R.mipmap.lishishuju6);
                    ycSrc.setText("16");
                } else if (ycSrc.getText().toString().equals("16")) {

                } else if (ycSrc.getText().toString().equals("21")) {
                    lishishuju.setImageResource(R.mipmap.huanshishuju2);
                    ycSrc.setText("22");
                } else if (ycSrc.getText().toString().equals("22")) {
                    lishishuju.setImageResource(R.mipmap.huanshishuju3);
                    ycSrc.setText("23");
                } else if (ycSrc.getText().toString().equals("23")) {
                    lishishuju.setImageResource(R.mipmap.huanshishuju4);
                    ycSrc.setText("24");
                } else if (ycSrc.getText().toString().equals("24")) {
                    lishishuju.setImageResource(R.mipmap.huanshishuju5);
                    ycSrc.setText("25");
                } else if (ycSrc.getText().toString().equals("25")) {
                    lishishuju.setImageResource(R.mipmap.huanshishuju6);
                    ycSrc.setText("26");
                } else if (ycSrc.getText().toString().equals("26")) {

                } else if (ycSrc.getText().toString().equals("31")) {
                    lishishuju.setImageResource(R.mipmap.guangzhaoshuju2);
                    ycSrc.setText("32");
                } else if (ycSrc.getText().toString().equals("32")) {
                    lishishuju.setImageResource(R.mipmap.guangzhaoshuju3);
                    ycSrc.setText("33");
                } else if (ycSrc.getText().toString().equals("33")) {
                    lishishuju.setImageResource(R.mipmap.guangzhaoshuju4);
                    ycSrc.setText("34");
                } else if (ycSrc.getText().toString().equals("34")) {
                    lishishuju.setImageResource(R.mipmap.guangzhaoshuju5);
                    ycSrc.setText("35");
                } else if (ycSrc.getText().toString().equals("35")) {
                    lishishuju.setImageResource(R.mipmap.guangzhaoshuju6);
                    ycSrc.setText("36");
                } else if (ycSrc.getText().toString().equals("36")) {

                } else if (ycSrc.getText().toString().equals("41")) {
                    lishishuju.setImageResource(R.mipmap.co2shuju2);
                    ycSrc.setText("42");
                } else if (ycSrc.getText().toString().equals("42")) {
                    lishishuju.setImageResource(R.mipmap.co2shuju3);
                    ycSrc.setText("43");
                } else if (ycSrc.getText().toString().equals("43")) {
                    lishishuju.setImageResource(R.mipmap.co2shuju4);
                    ycSrc.setText("44");
                } else if (ycSrc.getText().toString().equals("44")) {
                    lishishuju.setImageResource(R.mipmap.co2shuju5);
                    ycSrc.setText("45");
                } else if (ycSrc.getText().toString().equals("45")) {
                    lishishuju.setImageResource(R.mipmap.co2shuju6);
                    ycSrc.setText("46");
                } else if (ycSrc.getText().toString().equals("46")) {

                }
            }
        });
        xiayiye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ycSrc.getText().toString().equals("12")) {
                    lishishuju.setImageResource(R.mipmap.lishishuju1);
                    ycSrc.setText("11");
                } else if (ycSrc.getText().toString().equals("13")) {
                    lishishuju.setImageResource(R.mipmap.lishishuju2);
                    ycSrc.setText("12");
                } else if (ycSrc.getText().toString().equals("14")) {
                    lishishuju.setImageResource(R.mipmap.lishishuju3);
                    ycSrc.setText("13");
                } else if (ycSrc.getText().toString().equals("15")) {
                    lishishuju.setImageResource(R.mipmap.lishishuju4);
                    ycSrc.setText("14");
                } else if (ycSrc.getText().toString().equals("16")) {
                    lishishuju.setImageResource(R.mipmap.lishishuju5);
                    ycSrc.setText("15");
                } else if (ycSrc.getText().toString().equals("11")) {

                } else if (ycSrc.getText().toString().equals("22")) {
                    lishishuju.setImageResource(R.mipmap.huanshishuju1);
                    ycSrc.setText("21");
                } else if (ycSrc.getText().toString().equals("23")) {
                    lishishuju.setImageResource(R.mipmap.huanshishuju2);
                    ycSrc.setText("22");
                } else if (ycSrc.getText().toString().equals("24")) {
                    lishishuju.setImageResource(R.mipmap.huanshishuju3);
                    ycSrc.setText("23");
                } else if (ycSrc.getText().toString().equals("25")) {
                    lishishuju.setImageResource(R.mipmap.huanshishuju4);
                    ycSrc.setText("24");
                } else if (ycSrc.getText().toString().equals("26")) {
                    lishishuju.setImageResource(R.mipmap.huanshishuju5);
                    ycSrc.setText("25");
                } else if (ycSrc.getText().toString().equals("21")) {

                } else if (ycSrc.getText().toString().equals("32")) {
                    lishishuju.setImageResource(R.mipmap.guangzhaoshuju1);
                    ycSrc.setText("31");
                } else if (ycSrc.getText().toString().equals("33")) {
                    lishishuju.setImageResource(R.mipmap.guangzhaoshuju2);
                    ycSrc.setText("32");
                } else if (ycSrc.getText().toString().equals("34")) {
                    lishishuju.setImageResource(R.mipmap.guangzhaoshuju3);
                    ycSrc.setText("33");
                } else if (ycSrc.getText().toString().equals("35")) {
                    lishishuju.setImageResource(R.mipmap.guangzhaoshuju4);
                    ycSrc.setText("34");
                } else if (ycSrc.getText().toString().equals("36")) {
                    lishishuju.setImageResource(R.mipmap.guangzhaoshuju5);
                    ycSrc.setText("35");
                } else if (ycSrc.getText().toString().equals("31")) {

                } else if (ycSrc.getText().toString().equals("42")) {
                    lishishuju.setImageResource(R.mipmap.co2shuju1);
                    ycSrc.setText("41");
                } else if (ycSrc.getText().toString().equals("43")) {
                    lishishuju.setImageResource(R.mipmap.co2shuju2);
                    ycSrc.setText("42");
                } else if (ycSrc.getText().toString().equals("44")) {
                    lishishuju.setImageResource(R.mipmap.co2shuju3);
                    ycSrc.setText("43");
                } else if (ycSrc.getText().toString().equals("45")) {
                    lishishuju.setImageResource(R.mipmap.co2shuju4);
                    ycSrc.setText("44");
                } else if (ycSrc.getText().toString().equals("46")) {
                    lishishuju.setImageResource(R.mipmap.co2shuju5);
                    ycSrc.setText("45");
                } else if (ycSrc.getText().toString().equals("41")) {

                }
            }
        });
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg_all);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId) {
                    case R.id.rb_huanwen:
                        lishishuju.setImageResource(R.mipmap.lishishuju1);
                        ycSrc.setText("11");
                        break;
                    case R.id.rb_huanshi:
                        lishishuju.setImageResource(R.mipmap.huanshishuju1);
                        ycSrc.setText("21");
                        break;
                    case R.id.rb_guangzhao:
                        lishishuju.setImageResource(R.mipmap.guangzhaoshuju1);
                        ycSrc.setText("31");
                        break;
                    case R.id.rb_eryanghuatan:
                        lishishuju.setImageResource(R.mipmap.co2shuju1);
                        ycSrc.setText("41");
                        break;
                    case R.id.rb_tuwen:
                        Toast.makeText(LishishujuDialogActivity.this, "抱歉，数据不足。", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_tushi:
                        Toast.makeText(LishishujuDialogActivity.this, "抱歉，数据不足。", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.back:
                finish();
                break;

            case android.R.id.home:
                finish();
                break;
            default:
        }
        return true;

    }
}