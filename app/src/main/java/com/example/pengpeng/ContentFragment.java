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
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pengpeng.db.DataNow;
import com.example.pengpeng.db.Datashow;
import com.example.pengpeng.db.Datatype;
import com.example.pengpeng.db.Datetime;
import com.example.pengpeng.db.Shebei;
import com.example.pengpeng.db.UserGroup;
import com.example.pengpeng.db.Xueyuan;
import com.example.pengpeng.db.Zhuanjiaxitong;
import com.example.pengpeng.db.Zuowu;

import org.litepal.crud.DataSupport;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static android.content.Context.ALARM_SERVICE;
//import static com.example.pengpeng.R.id.choose_time_dialog;
import static com.example.pengpeng.R.layout.datashow;
import static org.litepal.LitePalApplication.getContext;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class ContentFragment extends Fragment {
    private View view;
    private View view2;
    private TextView shebei_name;
    private TimePicker choose_time_TimePicker;
    private int hour;
    private int Minute;
    private Calendar calendar;
    private AlarmManager manager;
    private PendingIntent sender;
    private RecyclerView recyclerView;
    private Button startTime_button;
    private Datetime mDatetime;
    private List<String> dataList2=new ArrayList<>();
    private List<Datatype> lishishujuList;
    private ArrayAdapter<String> adapter;
    private Spinner lishishuju;
    private TextView choose_textview;
    private Button chaxun;
    private static final String DIALOG_DATE="DialogDate";
    private static final int REQUEST_DATE=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Intent intent = new Intent(getContext(), AlarmCloseShebeiReceiver.class);
        sender = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
        view=inflater.inflate(R.layout.content_frag,container,false);
        //view2=inflater.inflate(R.layout.time_choose,(ViewGroup)view.findViewById(choose_time_TP));
        view2=LayoutInflater.from(getActivity()).inflate(R.layout.time_choose,null);
       // View v= LayoutInflater.from(getActivity()).inflate(R.layout.time_choose,null);
        lishishuju=(Spinner) view.findViewById(R.id.shujuleixing_spinner);
        lishishujuadapter();
        chaxun=(Button)view.findViewById(R.id.lishidata_bt);
        chaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),lishishuju.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        choose_time_TimePicker=(TimePicker)view2.findViewById(R.id.choose_time_TP);
        choose_time_TimePicker.setIs24HourView(true);
        manager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();
        hour    = calendar.get(Calendar.HOUR_OF_DAY);
        Minute  = calendar.get(Calendar.MINUTE);
        recyclerView=(RecyclerView)view.findViewById(R.id.shebei_recycler_view);
        shebei_name=(TextView)view.findViewById(R.id.shebei_name);
        startTime_button=(Button)view.findViewById(R.id.startTime_bt);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //获取activity传来的数据
        Bundle bundle = getArguments();
        if (bundle != null) {
            String greenhouseID = bundle.getString("greenhouseID");
            String userId = bundle.getString("userID");
            String dizhi = bundle.getString("dizhi");
        ShebeiAdapter adapter=new ShebeiAdapter(getSheBei(greenhouseID));
        recyclerView.setAdapter(adapter);

            //历史数据查询开始时间button
            startTime_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager manager=getFragmentManager();
                    DatePickerFragment dialog=new DatePickerFragment();
                  //  DatePickerFragment dialog=DatePickerFragment
                  //         .newInstance(mDatetime.getmDate());
                    dialog.setTargetFragment(ContentFragment.this,REQUEST_DATE);
                    dialog.show(manager,DIALOG_DATE);
                }
            });

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
            refresh(datashow);}
        }

        return view;
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode!=Activity.RESULT_OK){
            return;
        }
        if(requestCode==REQUEST_DATE){
            Date date=(Date)data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mDatetime.setmDate(date);
            startTime_button.setText(mDatetime.getmDate().toString());
        }
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
        TextView YcGreenhouseid=(TextView)view.findViewById(R.id.ycgreenhouseid_tv);

        HuanWenText.setText(datashow.getHuanwen());
        HuanShiText.setText(datashow.getHuanshi());
        GuangZhaoText.setText(datashow.getGuangzhao());
        ErYangHuaTanText.setText(datashow.getEryanghuatan());
        TuWenText.setText(datashow.getTuwen());
        TuShiText.setText(datashow.getTushi());
        YcGreenhouseid.setText(datashow.getGreenhouseId());
        ShebeiAdapter adapter=new ShebeiAdapter(getSheBei(datashow.getGreenhouseId()));
        recyclerView.setAdapter(adapter);
    }
//选择时间对话框
    public void choose_time(final Switch OpenSW,final String shebeiId,final String greenhouseID,final String shebeiName){
        LayoutInflater inflater = getActivity().getLayoutInflater();
       // view2=inflater.inflate(R.layout.time_choose,(ViewGroup)view.findViewById(choose_time_dialog));
        //View v= LayoutInflater.from(getActivity()).inflate(R.layout.time_choose,null);
        view2=LayoutInflater.from(getActivity()).inflate(R.layout.time_choose,null);
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
                        {hour=choose_time_TimePicker.getHour();}
                        else {hour=choose_time_TimePicker.getCurrentHour(); }
                        if(Build.VERSION.SDK_INT >= 23 )
                        {Minute=choose_time_TimePicker.getMinute();}
                        else {Minute=choose_time_TimePicker.getCurrentMinute(); }
                        c.set(Calendar.HOUR, hour);
                        c.set(Calendar.MINUTE, Minute);
                        // ②设置AlarmManager在Calendar对应的时间启动Activity
                        manager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
                        Toast.makeText(getContext(),greenhouseID+"号温室："+shebeiName+"设备"+"已开启", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OpenSW.setChecked(false);
                    }
                }).show();
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
        adapter=new ArrayAdapter<String>(getContext(),R.layout.spinner_text_style,dataList2);
        lishishuju.setAdapter(adapter);
        lishishuju.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // choose_textview.setText(lishishuju.getSelectedItem().toString());
               // Toast.makeText(getContext(),lishishuju.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
