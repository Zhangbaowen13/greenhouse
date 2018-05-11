package com.example.pengpeng;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pengpeng.db.Zhuanjiaxitong;

import org.litepal.crud.DataSupport;

import java.util.List;

public class GreenhouseEditorActivity extends AppCompatActivity {
private FloatingActionButton addshebei_fab;
    FragmentManager fm;
    private ImageView beijing_Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greenhouse_editor);
        beijing_Image=(ImageView)findViewById(R.id.beijing_image);
addshebei_fab=(FloatingActionButton)findViewById(R.id.addshebei_fab);

        //向fragment传值
        fm = getSupportFragmentManager();
        FragmentTransaction shiwu = fm.beginTransaction();
        String usergroupID=getIntent().getStringExtra("UserGroup_Id");
        Bundle bundle = new Bundle();
        bundle.putString("UserGroup_Id",usergroupID);
        GreenhouseEditorFragment greenhouseEditorFragment = new GreenhouseEditorFragment();
        greenhouseEditorFragment.setArguments(bundle);
        shiwu.replace(R.id.greenhouse_editor_Framelayout, greenhouseEditorFragment);
        shiwu.commit();
        //Toolbar导航栏
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.greenhouse_editor_collapsing_toolbar);
        collapsingToolbar.setTitle("温室管理方案");
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //添加背景图片
        beijing_Image.setImageResource(R.mipmap.daohangbeijing);
        //悬浮按钮修改密码
        addshebei_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_yinsu_dialog();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String dizhi=getIntent().getStringExtra("dizhi");

        switch(item.getItemId()){

            case android.R.id.home:
               /* if(dizhi.equals("GreenhouselistActivity")){
                    Intent intent=new Intent(GreenhouseEditorActivity.this,GreenhouselistActivity.class);
                    String userID=getIntent().getStringExtra("user_id");
                    intent.putExtra("user_id",userID);
                    startActivity(intent);
                    finish();}else if(dizhi.equals("ContentActivity")){
                    finish();}*/
               finish();
            default:
        }
        return super.onOptionsItemSelected(item);
    }
    //添加管理因素对话框
    private void add_yinsu_dialog(){
        LayoutInflater inflater =getLayoutInflater();
        View v= LayoutInflater.from(GreenhouseEditorActivity.this).inflate(R.layout.greenhouseadd_dialog,null);


        new AlertDialog.Builder(this).setTitle("添加管理：").setView(v)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(GreenhouseEditorActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
}
