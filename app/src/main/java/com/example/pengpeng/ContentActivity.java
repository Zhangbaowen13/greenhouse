package com.example.pengpeng;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pengpeng.db.DataNow;
import com.example.pengpeng.db.Datashow;
import com.example.pengpeng.db.Datatype;
import com.example.pengpeng.db.Xueyuan;
import com.example.pengpeng.db.Zhuanjiaxitong;
import com.example.pengpeng.db.Zuowu;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import static com.example.pengpeng.R.id.juli_LinearLayout;
import static com.example.pengpeng.R.id.ycgreenhouseid_tv;
import static com.example.pengpeng.R.id.zhuanjiaxitong_LinearLayout;
import static java.security.AccessController.getContext;

public class ContentActivity extends AppCompatActivity {
    private final long SPLASH_LENGTH=700;
    Handler handler=new Handler();
    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefresh;
    FragmentManager fm;

    private TextView huanwenMin_textview;
    private TextView huanwenMax_textview;
    private TextView huanshiMin_textview;
    private TextView huanshiMax_textview;
    private TextView guangzhaoMin_textview;
    private TextView guangzhaoMax_textview;
    private TextView eryanghuatanMin_textview;
    private TextView eryanghuatanMax_textview;
    private TextView tuwenMin_textview;
    private TextView tuwenMax_textview;
    private TextView tushiMin_textview;
    private TextView tushiMax_textview;
    private TextView zuowu_textview;
    private TextView shiqi_textview;
    private TextView choose_textview;

    private List<String> dataList=new ArrayList<>();
    private List<String> dataList1=new ArrayList<>();
    private List<String> dataList2=new ArrayList<>();
    private List<Zhuanjiaxitong> zhuanjiaxitongList;
    private List<Zuowu> zuowuList;
    private List<Datatype> lishishujuList;
    private ArrayAdapter<String> adapter;
    private Spinner zuowuName;
    private Spinner shiqi;
    private Spinner lishishuju;
    private View view4;
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
        String isnew=String.valueOf(datashow.getIsnew());
        intent.putExtra("IsNew",isnew);
        intent.putExtra("UpdateTime",datashow.getUpdatetime());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        View view= LayoutInflater.from(ContentActivity.this).inflate(R.layout.zhuanjiaxitong_dialog,null);
        View v= LayoutInflater.from(ContentActivity.this).inflate(R.layout.zhuanjiaxitong_dialog1,null);
        View v1= LayoutInflater.from(ContentActivity.this).inflate(R.layout.lishishuju,null);
       // choose_textview=(TextView)v1.findViewById(R.id.choose_yinsu);
       // lishishuju=(Spinner) findViewById(R.id.shujuleixing_spinner);
      //  Button chaxun=(Button)findViewById(R.id.lishidata_bt);

       // lishishujuadapter();

        zuowuName=(Spinner)view.findViewById(R.id.zuowu_spinner);
        shiqi=(Spinner)view.findViewById(R.id.shiqi_spinner);
        LayoutInflater inflater = getLayoutInflater();
        view4=inflater.inflate(R.layout.zhuanjiaxitong_dialog,(ViewGroup)findViewById(zhuanjiaxitong_LinearLayout));
        //向fragment传值
        fm = getSupportFragmentManager();
        FragmentTransaction shiwu = fm.beginTransaction();
        String userId=getIntent().getStringExtra("user_id");
        String Greenhouseid=getIntent().getStringExtra("Greenhouse_Id");

        Bundle bundle = new Bundle();
        bundle.putString("greenhouseID",Greenhouseid);
        bundle.putString("userID",userId);
        ContentFragment contentFragment = new ContentFragment();
        contentFragment.setArguments(bundle);
        shiwu.replace(R.id.content_Framelayout, contentFragment);
        shiwu.commit();
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
                choose_zuowu_dialog();

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
        String Greenhouseid1=getIntent().getStringExtra("Greenhouse_Id");
        String Picture=getIntent().getStringExtra("Picture");
        //String userId=getIntent().getStringExtra("user_id");
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
        datashow.setGreenhouseId(Greenhouseid1);
        datashow.setIsnew(Isnew);
        datashow.setUpdatetime(Updatetime);
        datashow.setUserId(userId);

        ContentFragment contentFragment1=(ContentFragment)getSupportFragmentManager().findFragmentById(R.id.content_fragment);
        contentFragment1.refresh(datashow);

/*//查询历史数据
        chaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContentActivity.this,lishishuju.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.back:
                //获取屏幕分辨率

                DisplayMetrics dm = getResources().getDisplayMetrics();
                int screenWidth = dm.widthPixels;
                if(screenWidth>=900){
                  //  intent = new Intent(ContentActivity.this, Greenhouselist2Activity.class);
                   // intent.putExtra("dizhi","ContentActivity");
                }else{Intent intent=new Intent(ContentActivity.this,GreenhouselistActivity.class);
                String userId=getIntent().getStringExtra("user_id");
                intent.putExtra("user_id",userId);
                startActivity(intent);}
                finish();
                break;

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;

    }
    //专家系统对话框dialog
    public void zhuanjiaxitong_dialog(){
        LayoutInflater inflater = getLayoutInflater();
        view4=inflater.inflate(R.layout.zhuanjiaxitong_dialog,(ViewGroup)findViewById(zhuanjiaxitong_LinearLayout));
        new AlertDialog.Builder(this).setTitle("专家系统：").setView(view4)
                .setPositiveButton("查询", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }) .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ContentActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
    //专家系统选择对话框
    private void choose_zuowu_dialog(){
        LayoutInflater inflater =getLayoutInflater();
        View v= LayoutInflater.from(ContentActivity.this).inflate(R.layout.zhuanjiaxitong_dialog,null);
        zuowuName=(Spinner)v.findViewById(R.id.zuowu_spinner) ;
        shiqi=(Spinner)v.findViewById(R.id.shiqi_spinner);
        Zhuanjiaadapter();
        new AlertDialog.Builder(this).setTitle("专家系统：").setView(v)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String zuowu=zuowuName.getSelectedItem().toString();
                        String shiQi=shiqi.getSelectedItem().toString();
                        List<Zhuanjiaxitong> zhuanjiaxitongs= DataSupport.where("zuowu=? and shiqi=?",zuowu,shiQi).find(Zhuanjiaxitong.class);
                        if(zhuanjiaxitongs.size()>0){
                            zhuanjiaxitong_dialog(zhuanjiaxitongs);
                        }else {
                            Toast.makeText(ContentActivity.this,"抱歉，暂无该作物数据。",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    //专家系统对话框
    private void zhuanjiaxitong_dialog( List<Zhuanjiaxitong> zhuanjiaxitongs){
        LayoutInflater inflater = getLayoutInflater();
        View v= LayoutInflater.from(ContentActivity.this).inflate(R.layout.zhuanjiaxitong_dialog1,null);
        huanwenMin_textview=(TextView)v.findViewById(R.id.huanwenMin_tv);
        huanwenMax_textview=(TextView)v.findViewById(R.id.huanwenMax_tv);
        huanshiMin_textview=(TextView)v.findViewById(R.id.huanshiMin_tv);
        huanshiMax_textview=(TextView)v.findViewById(R.id.huanshiMax_tv);
        guangzhaoMin_textview=(TextView)v.findViewById(R.id.guangzhaoMin_tv);
        guangzhaoMax_textview=(TextView) v.findViewById(R.id.guangzhaoMax_tv);
        eryanghuatanMin_textview=(TextView)v.findViewById(R.id.eryanghuaTanMin_tv);
        eryanghuatanMax_textview=(TextView)v. findViewById(R.id.eryanghuaTanMax_tv);
        tuwenMin_textview=(TextView)v.findViewById(R.id.tuwenMin_tv);
        tuwenMax_textview=(TextView)v.findViewById(R.id.tuwenMax_tv);
        tushiMin_textview=(TextView)v.findViewById(R.id.tuShiMin_tv);
        tushiMax_textview=(TextView)v. findViewById(R.id.tuShiMax_tv);
        zuowu_textview=(TextView)v.findViewById(R.id.zuowu_tv);
        shiqi_textview=(TextView)v.findViewById(R.id.shiqi_tv);
        if(zhuanjiaxitongs.size()>0){
            for(Zhuanjiaxitong zhuanjiaxitong:zhuanjiaxitongs){
                zuowu_textview.setText(zhuanjiaxitong.getZuowu());
                shiqi_textview.setText(zhuanjiaxitong.getShiqi());
                huanwenMin_textview.setText(zhuanjiaxitong.getHuanwenMin());
                huanwenMax_textview.setText(zhuanjiaxitong.getHuanwenMax());
                huanshiMin_textview.setText(zhuanjiaxitong.getHuanshiMin());
                huanshiMax_textview.setText(zhuanjiaxitong.getHuanshiMax());
                guangzhaoMin_textview.setText(zhuanjiaxitong.getGuangzhaoMin());
                guangzhaoMax_textview.setText(zhuanjiaxitong.getGuangzhaoMax());
                eryanghuatanMin_textview.setText(zhuanjiaxitong.getEryanghuatanMin());
                eryanghuatanMax_textview.setText(zhuanjiaxitong.getEryanghuatanMax());
                tuwenMin_textview.setText(zhuanjiaxitong.getTuwenMin());
                tuwenMax_textview.setText(zhuanjiaxitong.getTuwenMax());
                tushiMin_textview.setText(zhuanjiaxitong.getTushiMin());
                tushiMax_textview.setText(zhuanjiaxitong.getTushiMax());
            }
        }
        new AlertDialog.Builder(ContentActivity.this).setTitle("专家系统：").setView(v)
                .setPositiveButton("使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ContentActivity.this,"设置成功",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }
    private void Zhuanjiaadapter(){
        //配置专家系统spinner适配器
        zuowuList=DataSupport.findAll(Zuowu.class);
        if(zuowuList.size()!=0){
            dataList.clear();
            for(Zuowu zuowu:zuowuList){
                dataList.add(zuowu.getZuowu());
            }
        }
        adapter=new ArrayAdapter<String>(ContentActivity.this,R.layout.support_simple_spinner_dropdown_item,dataList);
        zuowuName.setAdapter(adapter);
        zuowuName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //配置时期spinner适配器
                String zuowuName=zuowuList.get(position).getZuowu();
                List<Zhuanjiaxitong> zhuanjiaxitongs=DataSupport.where("zuowu=?",zuowuName).find(Zhuanjiaxitong.class);
                if(zhuanjiaxitongs.size()>0){
                    dataList1.clear();
                    for(Zhuanjiaxitong zhuanjiaxitong:zhuanjiaxitongs){
                        dataList1.add(zhuanjiaxitong.getShiqi());
                    }
                    adapter=new ArrayAdapter<String>(ContentActivity.this,R.layout.support_simple_spinner_dropdown_item,dataList1);
                    shiqi.setAdapter(adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void lishishujuadapter(){
        //配置历史数据spinner适配器
        lishishujuList=DataSupport.findAll(Datatype.class);
        if(lishishujuList.size()!=0){
            dataList2.clear();
            for(Datatype datatype:lishishujuList){
                dataList2.add(datatype.getDataname());
            }
        }
        adapter=new ArrayAdapter<String>(ContentActivity.this,R.layout.support_simple_spinner_dropdown_item,dataList2);
        lishishuju.setAdapter(adapter);
        lishishuju.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choose_textview.setText(lishishuju.getSelectedItem().toString());
                Toast.makeText(ContentActivity.this,lishishuju.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
