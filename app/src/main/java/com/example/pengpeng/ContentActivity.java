package com.example.pengpeng;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pengpeng.db.Datashow;

public class ContentActivity extends AppCompatActivity {
    private final long SPLASH_LENGTH=700;
    Handler handler=new Handler();
    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefresh;
    public static void actionStart(Context context, Datashow datashow)
    {
        Intent intent=new Intent(context,ContentActivity.class);
        intent.putExtra("Huan_Wen",datashow.getHuanwen());
        intent.putExtra("Huan_Shi",datashow.getHuanshi());
        intent.putExtra("Guang_Zhao",datashow.getGuangzhao());
        intent.putExtra("ErYang_HuaTan",datashow.getEryanghuatan());
        intent.putExtra("Tu_Wen",datashow.getTuwen());
        intent.putExtra("Tu_Shi",datashow.getTushi());
        intent.putExtra("Greenhouse_Id",datashow.getGreenhouseId());
        intent.putExtra("user_id",datashow.getUserId());
        String picture=String.valueOf(datashow.getPicture());
        intent.putExtra("Picture",picture);
        String isnew=String.valueOf(datashow.isnew());
        intent.putExtra("IsNew",isnew);
        intent.putExtra("UpdateTime",datashow.getUpdatetime());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        //下拉刷新
        swipeRefresh=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh_Content);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefresh.setRefreshing(false);
                    }
                },SPLASH_LENGTH);
            }
        });
        //悬浮按钮
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"专家系统",Snackbar.LENGTH_SHORT).setAction("朕准了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ContentActivity.this,"假装打开了专家系统23333~",Toast.LENGTH_SHORT).show();
                    }
                })  .show();
            }
        });

        //Toolbar导航栏
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.person);
        }
        //navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.nav_weather:
                        Intent intent3=new Intent(ContentActivity.this,AreaActivity.class);
                        intent3.putExtra("dizhi","ContentActivity");
                        startActivity(intent3);
                        break;
                    case R.id.nav_personal:
                        Intent intent=new Intent(ContentActivity.this,PersonalActivity.class);
                        String userID=getIntent().getStringExtra("user_id");
                        intent.putExtra("user_id",userID);
                        intent.putExtra("dizhi","ContentActivity");
                        startActivity(intent);
                        break;
                    case R.id.nav_greenhouse:
                        Intent intent6=new Intent(ContentActivity.this,GreenhouseActivity.class);
                        String userID1=getIntent().getStringExtra("user_id");
                        intent6.putExtra("user_id",userID1);
                        intent6.putExtra("dizhi","ContentActivity");
                        startActivity(intent6);
                        break;
                    case R.id.nav_shift:
                        Intent intent1=new Intent(ContentActivity.this,LoginActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_exit:
                        Intent intent2=new Intent(ContentActivity.this,LoginActivity.class);
                        startActivity(intent2);
                        break;
                    default:
                }
                return true;
            }
        });

        Datashow datashow=new Datashow();
        String Huanwen=getIntent().getStringExtra("Huan_Wen");
        String Huanshi=getIntent().getStringExtra("Huan_Shi");
        String Guangzhao=getIntent().getStringExtra("Guang_Zhao");
        String Eryanghuatan=getIntent().getStringExtra("ErYang_HuaTan");
        String Tuwen=getIntent().getStringExtra("Tu_Wen");
        String Tushi=getIntent().getStringExtra("Tu_Shi");
        String Greenhouseid=getIntent().getStringExtra("Greenhouse_Id");
        String Picture=getIntent().getStringExtra("Picture");
        String userId=getIntent().getStringExtra("user_id");
        try {
            int picture = Integer.parseInt(Picture);
            datashow.setPicture(picture);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String Isnew=getIntent().getStringExtra("IsNew");
        boolean isnew=false;
        if(Isnew.equals("true")){isnew=true;}
        String Updatetime=getIntent().getStringExtra("UpdateTime");
        datashow.setHuanwen(Huanwen);
        datashow.setHuanshi(Huanshi);
        datashow.setGuangzhao(Guangzhao);
        datashow.setEryanghuatan(Eryanghuatan);
        datashow.setTuwen(Tuwen);
        datashow.setTushi(Tushi);
        datashow.setGreenhouseId(Greenhouseid);
        datashow.setIsnew(isnew);
        datashow.setUpdatetime(Updatetime);
        datashow.setUserId(userId);

        ContentFragment contentFragment=(ContentFragment)getSupportFragmentManager().findFragmentById(R.id.content_fragment);
        contentFragment.refresh(datashow);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent=new Intent(ContentActivity.this,GreenhouselistActivity.class);
        switch(item.getItemId()){
            case R.id.back:
                String userId=getIntent().getStringExtra("user_id");
                intent.putExtra("user_id",userId);
                startActivity(intent);
                finish();
                break;
            case R.id.zhuanyeban:
                Toast.makeText(this,"假装打开了专业版233333~",Toast.LENGTH_SHORT).show();
                break;
            case R.id.jiandanban:
                Toast.makeText(this,"这就是简单版啊笨蛋！",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;

    }
}
