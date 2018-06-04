package com.example.pengpeng;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pengpeng.db.DataNow;
import com.example.pengpeng.db.Datashow;
import com.example.pengpeng.db.Datatype;
import com.example.pengpeng.db.Shebei;
import com.example.pengpeng.db.UserGroup;
import com.example.pengpeng.db.Zhuanjiaxitong;
import com.example.pengpeng.db.Zuowu;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.pengpeng.R.id.zhuanjiaxitong_LinearLayout;

public class Greenhouselist2Activity extends AppCompatActivity {

//contentactivity中声明的
    private final long SPLASH_LENGTH=700;
    Handler handler=new Handler();
    private UserGroup userGroup;
    private List<DataNow>dataNowList;
    private SwipeRefreshLayout swipeRefresh;
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
    private TimePicker choose_time_TimePicker;
    private PendingIntent sender;
    //greenhouselistactivity中声明的
    private DrawerLayout mDrawerLayout;
    FragmentManager fm;
    private int hour;
    private int Minute;
    private AlarmManager manager;
    //开始
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greenhouselist2);
        View view2=LayoutInflater.from(Greenhouselist2Activity.this).inflate(R.layout.time_choose,null);
        Intent intent = new Intent(Greenhouselist2Activity.this, AlarmCloseShebeiReceiver.class);
        sender = PendingIntent.getBroadcast(Greenhouselist2Activity.this, 0, intent, 0);
        choose_time_TimePicker=(TimePicker)view2.findViewById(R.id.choose_time_TP);
        choose_time_TimePicker.setIs24HourView(true);
        //greenhouselist向fragment传值

        //向fragment传值
        fm = getSupportFragmentManager();
        FragmentTransaction shiwu = fm.beginTransaction();
        String userID=getIntent().getStringExtra("user_id");
        //String dizhi=getIntent().getStringExtra("dizhi");
        Bundle bundle = new Bundle();
        bundle.putString("userID",userID);
       // bundle.putString("dizhi",dizhi);
        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(bundle);
        shiwu.replace(R.id.list_Framelayout, listFragment);
        shiwu.commit();

        //contentactivity功能
        View view= LayoutInflater.from(Greenhouselist2Activity.this).inflate(R.layout.zhuanjiaxitong_dialog,null);
        zuowuName=(Spinner)view.findViewById(R.id.zuowu_spinner);
        shiqi=(Spinner)view.findViewById(R.id.shiqi_spinner);
        LayoutInflater inflater = getLayoutInflater();
        view4=inflater.inflate(R.layout.zhuanjiaxitong_dialog,(ViewGroup)findViewById(zhuanjiaxitong_LinearLayout));
        String Greenhouseid=getIntent().getStringExtra("Greenhouse_Id");
        TextView ycGreenhouseid=(TextView)findViewById(R.id.ycgreenhouseid_tv);
        ycGreenhouseid.setText(Greenhouseid);
        //初始化控件
        TextView Huanwen=(TextView)findViewById(R.id.huanwen_tv);
        TextView Huanshi=(TextView)findViewById(R.id.huanshi_tv);
        TextView Guangzhao=(TextView)findViewById(R.id.guangzhao_tv);
        TextView Eryanghuatan=(TextView)findViewById(R.id.eryanghuatan_tv);
        TextView Tuwen=(TextView)findViewById(R.id.tuwen_tv);
        TextView Tushi=(TextView)findViewById(R.id.tushi_tv);
        TextView YcGreenhouseid=(TextView)findViewById(R.id.ycgreenhouseid_tv);

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
        Toolbar toolbar1=(Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar1);
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
                        String userID=getIntent().getStringExtra("user_id");
                        String Greenhouseid=getIntent().getStringExtra("Greenhouse_Id");
                        Intent intent3=new Intent(Greenhouselist2Activity.this,AreaActivity.class);
                        intent3.putExtra("dizhi","Greenhouselist2Activity");
                        intent3.putExtra("Greenhouse_Id", Greenhouseid);
                        intent3.putExtra("user_id",userID);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.nav_personal:
                        Intent intent=new Intent(Greenhouselist2Activity.this,PersonalActivity.class);
                        String userID1=getIntent().getStringExtra("user_id");
                        String Greenhouseid1=getIntent().getStringExtra("Greenhouse_Id");
                        intent.putExtra("user_id",userID1);
                        intent.putExtra("Greenhouse_Id", Greenhouseid1);
                        intent.putExtra("dizhi","Greenhouselist2Activity");
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_greenhouse:
                        Intent intent5=new Intent(Greenhouselist2Activity.this,GreenhouseActivity.class);
                        String userID2=getIntent().getStringExtra("user_id");
                        String Greenhouseid2=getIntent().getStringExtra("Greenhouse_Id");
                        intent5.putExtra("Greenhouse_Id", Greenhouseid2);
                        intent5.putExtra("user_id",userID2);
                        intent5.putExtra("dizhi","Greenhouselist2Activity");
                        startActivity(intent5);
                        break;
                    case R.id.nav_shift:
                        Intent intent1=new Intent(Greenhouselist2Activity.this,LoginActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_exit:
                        Intent intent2=new Intent(Greenhouselist2Activity.this,LoginActivity.class);
                        startActivity(intent2);
                        break;
                    default:
                }
                return true;
            }
        });
    }



    @SuppressLint("NewApi")
    public void click(View v)
    {
        String userID=getIntent().getStringExtra("user_id");
        Bundle bundle = new Bundle();
        bundle.putString("userID",userID);
        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(bundle);
        FragmentTransaction shiwu =fm.beginTransaction();
        shiwu.replace(R.id.list_Framelayout, listFragment);
        shiwu.commit();}
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar2,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.back:
                /*Intent intent=new Intent(Greenhouselist2Activity.this,GreenhouselistActivity.class);
                String userId=getIntent().getStringExtra("user_id");
                intent.putExtra("user_id",userId);
                startActivity(intent);*/
                finish();
                break;
            case R.id.fangda:
                String userID=getIntent().getStringExtra("user_id");

                TextView Huanwen=(TextView)findViewById(R.id.huanwen_tv);
                TextView Huanshi=(TextView)findViewById(R.id.huanshi_tv);
                TextView Guangzhao=(TextView)findViewById(R.id.guangzhao_tv);
                TextView Eryanghuatan=(TextView)findViewById(R.id.eryanghuatan_tv);
                TextView Tuwen=(TextView)findViewById(R.id.tuwen_tv);
                TextView Tushi=(TextView)findViewById(R.id.tushi_tv);
                TextView YcGreenhouseid=(TextView)findViewById(R.id.ycgreenhouseid_tv);
                String Greenhouseid=YcGreenhouseid.getText().toString();
                String huanwen=Huanwen.getText().toString();
                String huanshi=Huanshi.getText().toString();
                String guangzhao=Guangzhao.getText().toString();
                String eryanghuatan=Eryanghuatan.getText().toString();
                String tuwen=Tuwen.getText().toString();
                String tushi=Tushi.getText().toString();
                if(Greenhouseid.equals("")||Greenhouseid==null){
                    Toast.makeText(Greenhouselist2Activity.this,"请选择一个温室。",Toast.LENGTH_SHORT).show();
                }else{
                Datashow datashow=new Datashow();
                datashow.setUserId(userID);
                datashow.setGreenhouseId(Greenhouseid);
                datashow.setHuanwen(huanwen);
                datashow.setHuanshi(huanshi);
                datashow.setGuangzhao(guangzhao);
                datashow.setEryanghuatan(eryanghuatan);
                datashow.setTuwen(tuwen);
                datashow.setTushi(tushi);
                ContentActivity.actionStart(Greenhouselist2Activity.this,datashow);}
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
                Toast.makeText(Greenhouselist2Activity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
    //专家系统选择对话框
    private void choose_zuowu_dialog(){
        LayoutInflater inflater =getLayoutInflater();
        View v= LayoutInflater.from(Greenhouselist2Activity.this).inflate(R.layout.zhuanjiaxitong_dialog,null);
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
                            Toast.makeText(Greenhouselist2Activity.this,"抱歉，暂无该作物数据。",Toast.LENGTH_SHORT).show();
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
        View v= LayoutInflater.from(Greenhouselist2Activity.this).inflate(R.layout.zhuanjiaxitong_dialog1,null);
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
        new AlertDialog.Builder(Greenhouselist2Activity.this).setTitle("专家系统：").setView(v)
                .setPositiveButton("使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Greenhouselist2Activity.this,"设置成功",Toast.LENGTH_SHORT).show();
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
        adapter=new ArrayAdapter<String>(Greenhouselist2Activity.this,R.layout.support_simple_spinner_dropdown_item,dataList);
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
                    adapter=new ArrayAdapter<String>(Greenhouselist2Activity.this,R.layout.support_simple_spinner_dropdown_item,dataList1);
                    shiqi.setAdapter(adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void lishishujuadapter() {
        //配置历史数据spinner适配器
        lishishujuList = DataSupport.findAll(Datatype.class);
        if (lishishujuList.size() != 0) {
            dataList2.clear();
            for (Datatype datatype : lishishujuList) {
                dataList2.add(datatype.getDataname());
            }
        }
        adapter = new ArrayAdapter<String>(Greenhouselist2Activity.this, R.layout.support_simple_spinner_dropdown_item, dataList2);
        lishishuju.setAdapter(adapter);
        lishishuju.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choose_textview.setText(lishishuju.getSelectedItem().toString());
                Toast.makeText(Greenhouselist2Activity.this, lishishuju.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    //专家系统选择对话框
    private void choose_time(final Switch OpenSW, final String shebeiId, final String greenhouseID, final String shebeiName){
        LayoutInflater inflater =getLayoutInflater();
        View view2= LayoutInflater.from(Greenhouselist2Activity.this).inflate(R.layout.time_choose,null);

        new AlertDialog.Builder(this).setTitle("请设置关闭时间：").setView(view2)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Shebei shebei=new Shebei();
                        shebei.setIsopen("true");
                        shebei.updateAll("id=?",shebeiId);
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(System.currentTimeMillis());
                        // 根据用户选择的时间来设置Calendar对象
                        if (Build.VERSION.SDK_INT >= 23 )
                        {hour=choose_time_TimePicker.getHour();}
                        else {hour=choose_time_TimePicker.getCurrentHour(); }
                        if(Build.VERSION.SDK_INT >= 23 )
                        {Minute=choose_time_TimePicker.getMinute();}
                        else {Minute=choose_time_TimePicker.getCurrentMinute(); }
                        c.set(Calendar.HOUR, hour);
                        c.set(Calendar.MINUTE, Minute);
                        // ②设置AlarmManager在Calendar对应的时间启动Activity
                        manager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);

                        if(shebeiName.equals("卷帘机")||shebeiName.equals("卷膜机")){
                            kaiqijuli(OpenSW,greenhouseID,shebeiName);
                        }else {
                            Toast.makeText(Greenhouselist2Activity.this,greenhouseID+"号温室："+shebeiName+"设备"+"已开启", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OpenSW.setChecked(false);
                    }
                }).show();
    }
    //距离dialog
    public void kaiqijuli( final Switch OpenSW,final String greenhouseID, final String shebeiName){
        View view4=LayoutInflater.from(Greenhouselist2Activity.this).inflate(R.layout.juli_dialog,null);
        new AlertDialog.Builder(Greenhouselist2Activity.this).setTitle("开启大小：").setView(view4)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Greenhouselist2Activity.this,greenhouseID+"号温室："+shebeiName+"设备"+"已开启", Toast.LENGTH_SHORT).show();
                    }
                }) .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OpenSW.setChecked(false);
            }
        }).show();
    }
}

