package com.example.pengpeng;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pengpeng.db.DataNow;
import com.example.pengpeng.db.Datashow;
import com.example.pengpeng.db.Shebei;

import org.litepal.crud.DataSupport;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static android.content.Context.ALARM_SERVICE;
import static com.example.pengpeng.R.id.choose_time_dialog;
import static com.example.pengpeng.R.layout.datashow;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class ContentFragment extends Fragment {
    private View view;
    private View view2;
    private TextView shebei_name;
    private TimePicker choose_time_TP;
    private int hour;
    private int Minute;
    private Calendar calendar;
    private AlarmManager manager;
    private PendingIntent sender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Intent intent = new Intent(getContext(), AlarmCloseShebeiReceiver.class);
        sender = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
        view=inflater.inflate(R.layout.content_frag,container,false);
        view2=inflater.inflate(R.layout.time_choose,(ViewGroup)view.findViewById(choose_time_dialog));
        choose_time_TP=(TimePicker)view2.findViewById(R.id.choose_time_TP);
        choose_time_TP.setIs24HourView(true);
        manager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();
        hour    = calendar.get(Calendar.HOUR_OF_DAY);
        Minute  = calendar.get(Calendar.MINUTE);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.shebei_recycler_view);
        shebei_name=(TextView)view.findViewById(R.id.shebei_name);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String greenhouseID = bundle.getString("greenhouseID");
            String userId = bundle.getString("userID");
        ShebeiAdapter adapter=new ShebeiAdapter(getSheBei(greenhouseID));
        recyclerView.setAdapter(adapter);

           List<DataNow> dataNowList=DataSupport.where("greenhouseId=? and chuanganqiId=? and isnew=?",greenhouseID,"1","true").find(DataNow.class);
            if(dataNowList.size()>0){
                Datashow datashow=new Datashow();
                datashow.setGreenhouseId(greenhouseID);
                for(DataNow dataNow:dataNowList){
                    if(dataNow.dataname.equals("环温")){
                        datashow.setHuanwen(dataNow.shuju);}else if(dataNow.dataname.equals("环湿")){
                        datashow.setHuanshi(dataNow.shuju);
                    }else if(dataNow.dataname.equals("光照")){
                        datashow.setGuangzhao(dataNow.shuju);
                    }else  if(dataNow.dataname.equals("二氧化碳")){
                        datashow.setEryanghuatan(dataNow.shuju);
                    }else if (dataNow.dataname.equals("土温")){
                        datashow.setTuwen(dataNow.shuju);
                    }else if(dataNow.dataname.equals("土湿")){
                        datashow.setTushi(dataNow.shuju);
                    }
                    datashow.setIsnew(dataNow.getIsnew());
                    datashow.setUpdatetime(dataNow.getUpdatetime());
                    datashow.setPicture(R.mipmap.wenshi);
                }
                datashow.setUserId(userId);
            refresh(datashow);}}
        return view;
    }
    private List<Shebei> getSheBei(String greenhouseid){
        List<Shebei> sheBeiList=new ArrayList<>();
        //String greenhouseid="1";
        List<Shebei> shebeis= DataSupport.where("greenhouseId=?",greenhouseid).find(Shebei.class);
        if(shebeis.size()>0){
            return shebeis;
           /* for(Shebei shebei:shebeis){
                sheBeiList.add(shebei);
            }
           return sheBeiList;*/
        }
        return null;
    }
    class ShebeiAdapter extends RecyclerView.Adapter<ShebeiAdapter.ViewHolder>{
        private List<Shebei> mShebeiList;
        class ViewHolder extends RecyclerView.ViewHolder{
            Switch OpenSW;
            TextView NameTV;
            public ViewHolder(View view){
                super(view);
                OpenSW=(Switch)view.findViewById(R.id.shebei_sw);
                NameTV=(TextView)view.findViewById(R.id.shebei_name);

                Bundle bundle = getArguments();
                if (bundle != null) {
                   final String greenhouseID = bundle.getString("greenhouseID");
                   final String userId = bundle.getString("userID");
                OpenSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String shebeiName= OpenSW.getTextOn().toString().replaceAll(" ","");
                        String shebeiId=OpenSW.getTextOff().toString();
                        /*List<Shebei> shebeiList=DataSupport.where("id=?",shebeiId).find(Shebei.class);
                        if(shebeiList.size()>0){
                        for(Shebei shebei:shebeiList){
                            String ifopen=shebei.getIsopen();
                        if(ifopen.equals("false")){*/
                        if(isChecked){
                            if(shebeiName.equals("开启")){}else {
                              /*  Shebei shebei=new Shebei();
                                shebei.setIsopen("true");
                                shebei.updateAll("id=?",shebeiId);
                               new TimePickerDialog(getContext(), 0, new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                                //设置当前时间
                                                Calendar c = Calendar.getInstance();
                                                c.setTimeInMillis(System.currentTimeMillis());
                                                // 根据用户选择的时间来设置Calendar对象
                                                c.set(Calendar.HOUR, hourOfDay);
                                                c.set(Calendar.MINUTE, minute);
                                                // ②设置AlarmManager在Calendar对应的时间启动Activity
                                                manager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
                                                Log.e("HEHE",c.getTimeInMillis()+"");   //这里的时间是一个unix时间戳
                                                // 提示闹钟设置完毕:
                                                Toast.makeText(getContext(), greenhouseID+"号温室："+"设备"+"已开启", Toast.LENGTH_SHORT).show();
                                            }

                                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();*/
                                choose_time(OpenSW,shebeiId,greenhouseID,shebeiName);
                            }
                        }else {
                            Shebei shebei1=new Shebei();
                            shebei1.setIsopen("false");
                            shebei1.updateAll("id=?",shebeiId);
                            Toast.makeText(getContext(),greenhouseID+"号温室："+shebeiName+"设备"+"未开启",Toast.LENGTH_SHORT).show();}
                        //重启详情页
                      /* List<DataNow> dataNowList=DataSupport.where("greenhouseId=? and chuanganqiId=? and isnew=?",greenhouseID,"1","true").find(DataNow.class);
                        if(dataNowList.size()>0){
                            Datashow datashow=new Datashow();
                            datashow.setGreenhouseId(greenhouseID);
                            for(DataNow dataNow:dataNowList){
                                if(dataNow.dataname.equals("环温")){
                                    datashow.setHuanwen(dataNow.shuju);}else if(dataNow.dataname.equals("环湿")){
                                    datashow.setHuanshi(dataNow.shuju);
                                }else if(dataNow.dataname.equals("光照")){
                                    datashow.setGuangzhao(dataNow.shuju);
                                }else  if(dataNow.dataname.equals("二氧化碳")){
                                    datashow.setEryanghuatan(dataNow.shuju);
                                }else if (dataNow.dataname.equals("土温")){
                                    datashow.setTuwen(dataNow.shuju);
                                }else if(dataNow.dataname.equals("土湿")){
                                    datashow.setTushi(dataNow.shuju);
                                }
                                datashow.setIsnew(dataNow.getIsnew());
                                datashow.setUpdatetime(dataNow.getUpdatetime());
                                datashow.setPicture(R.mipmap.wenshi);
                            }
                            datashow.setUserId(userId);
                            ContentActivity.actionStart(getContext(),datashow);
                        }*/
                    }
                });
            }}
        }
        public ShebeiAdapter(List<Shebei>ShebeiList){mShebeiList=ShebeiList;}
       @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
            final View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.shebei_item,parent,false);
            final ViewHolder holder=new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Shebei sheBei=mShebeiList.get(holder.getAdapterPosition());
                  /*   if(sheBei.isopen()){
                        Log.d("CountActivity","是否关闭设备");
                    }
                    else{
                        Log.d("CountActivity","是否打开设备");
                    }*/
                }
            });
            return holder;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder,int position){
            Shebei shebei=mShebeiList.get(position);
            if(shebei.getIsopen().equals("true")){holder.OpenSW.setChecked(true);}
            else if(shebei.getIsopen().equals("false")){holder.OpenSW.setChecked(false);}
            holder.OpenSW.setTextOn(shebei.getShebeiName());
            int i=shebei.getId();
            holder.OpenSW.setTextOff(String.valueOf(i));
            holder.NameTV.setText(shebei.getShebeiName());
        }
        @Override
        public int getItemCount(){return mShebeiList.size();}
    }
    public void refresh(Datashow datashow){

        //View visibilityLayout=view.findViewById(R.id.visibility_layout);

        TextView HuanWenText=(TextView)view.findViewById(R.id.huanwen_tv);
        TextView HuanShiText=(TextView)view.findViewById(R.id.huanshi_tv);
        TextView GuangZhaoText=(TextView)view.findViewById(R.id.guangzhao_tv);
        TextView ErYangHuaTanText=(TextView)view.findViewById(R.id.eryanghuatan_tv);
        TextView TuWenText=(TextView)view.findViewById(R.id.tuwen_tv);
        TextView TuShiText=(TextView)view.findViewById(R.id.tushi_tv);

        HuanWenText.setText(datashow.getHuanwen());
        HuanShiText.setText(datashow.getHuanshi());
        GuangZhaoText.setText(datashow.getGuangzhao());
        ErYangHuaTanText.setText(datashow.getEryanghuatan());
        TuWenText.setText(datashow.getTuwen());
        TuShiText.setText(datashow.getTushi());
    }
//选择时间对话框
    public void choose_time(final Switch OpenSW,final String shebeiId,final String greenhouseID,final String shebeiName){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view2=inflater.inflate(R.layout.time_choose,(ViewGroup)view.findViewById(choose_time_dialog));
        new AlertDialog.Builder(getContext()).setTitle("请设置关闭时间：").setView(view2)
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
                        {hour=choose_time_TP.getHour();}
                        else {hour=choose_time_TP.getCurrentHour(); }
                        if(Build.VERSION.SDK_INT >= 23 )
                        {Minute=choose_time_TP.getMinute();}
                        else {Minute=choose_time_TP.getCurrentMinute(); }
                        c.set(Calendar.HOUR, hour);
                        c.set(Calendar.MINUTE, Minute);
                        // ②设置AlarmManager在Calendar对应的时间启动Activity
                        manager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
                        Toast.makeText(getContext(), greenhouseID+"号温室："+shebeiName+"设备"+"已开启", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OpenSW.setChecked(false);
                    }
                }).show();
    }
}
