package com.example.pengpeng;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pengpeng.db.User;

import org.litepal.crud.DataSupport;

import java.util.List;

public class GreenhouseActivity extends AppCompatActivity {
    private FloatingActionButton addgreenhouse_fab;
    private ImageView beijing_Image;
    FragmentManager fm;
    @SuppressLint("NewApi")@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greenhouse);
        addgreenhouse_fab=(FloatingActionButton)findViewById(R.id.addgreenhouse_fab);
        beijing_Image=(ImageView)findViewById(R.id.beijing_image);
        //向fragment传值
        fm = getSupportFragmentManager();
        FragmentTransaction shiwu = fm.beginTransaction();
        String userID=getIntent().getStringExtra("user_id");
        String dizhi=getIntent().getStringExtra("dizhi");
        Bundle bundle = new Bundle();
        bundle.putString("userID",userID);
        bundle.putString("dizhi",dizhi);
        ListManageFragment listManageFragment = new ListManageFragment();
        listManageFragment.setArguments(bundle);
        shiwu.replace(R.id.list_manage_Framelayout, listManageFragment);
        shiwu.commit();
        //添加背景图片
        beijing_Image.setImageResource(R.mipmap.daohangbeijing);
        //悬浮按钮添加温室
        addgreenhouse_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GreenhouseActivity.this,"添加温室",Toast.LENGTH_SHORT).show();
            }
        });
        //Toolbar导航栏
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.greenhouse_collapsing_toolbar);
        collapsingToolbar.setTitle("温室管理");
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String dizhi=getIntent().getStringExtra("dizhi");

        switch(item.getItemId()){
            case android.R.id.home:
                if(dizhi.equals("GreenhouselistActivity")){
                    Intent intent=new Intent(GreenhouseActivity.this,GreenhouselistActivity.class);
                    String userID=getIntent().getStringExtra("user_id");
                    intent.putExtra("user_id",userID);
                    startActivity(intent);
                    finish();}else if(dizhi.equals("ContentActivity")){
                    finish();}
            default:
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressLint("NewApi")
    public void click(View v)
    {
        String userID=getIntent().getStringExtra("user_id");
        String dizhi=getIntent().getStringExtra("dizhi");
        Bundle bundle = new Bundle();
        bundle.putString("userID",userID);
        bundle.putString("dizhi",dizhi);
        ListManageFragment listManageFragment = new ListManageFragment();
        listManageFragment.setArguments(bundle);
        FragmentTransaction shiwu =fm.beginTransaction();
        shiwu.replace(R.id.list_manage_Framelayout, listManageFragment);
        shiwu.commit();}
}
