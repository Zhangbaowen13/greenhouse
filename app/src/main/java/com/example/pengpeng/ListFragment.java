package com.example.pengpeng;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pengpeng.db.DataNow;
import com.example.pengpeng.db.Datashow;
import com.example.pengpeng.db.Greenhouse;
import com.example.pengpeng.db.UserGroup;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/26 0026.
 */
@SuppressLint("NewApi")
public class ListFragment extends Fragment {
    private boolean isTwoPane;
    private LinearLayout greenhouse_item_bc;
    private TextView number_tv;
    private ImageView picture_img;
    private TextView huanwen_tv;
    private TextView eryanghuatan_tv;
    private TextView huanshi_tv;
    private TextView guangzhao_tv;
    private  List<Greenhouse> greenhouseList;
    private List<UserGroup> userGroupList;
    private List<DataNow>dataNowList;
    private List<Datashow> datashows;
    private List<String> dataList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        //初始化控件
        View view=inflater.inflate(R.layout.list_frag,container,false);
        RecyclerView listRecyclerView=(RecyclerView)view.findViewById(R.id.list_recycler_view);
        greenhouse_item_bc=(LinearLayout)view.findViewById(R.id.greenhouse_item_background);
        number_tv=(TextView)view.findViewById(R.id.number_tv);
        picture_img=(ImageView) view.findViewById(R.id.picture_img);
        huanwen_tv=(TextView)view.findViewById(R.id.huanwen_tv);
        eryanghuatan_tv=(TextView)view.findViewById(R.id.eryanghuatan_tv);
        huanshi_tv=(TextView)view.findViewById(R.id.huanshi_tv);
        guangzhao_tv=(TextView)view.findViewById(R.id.guangzhao_tv);
//Intent intent=Intent.getIntent()
       // final String weatherId = getIntent().getStringExtra("weather_id");

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        listRecyclerView.setLayoutManager(layoutManager);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String userID = bundle.getString("userID");
            DataAdapter adapter=new DataAdapter(getData(userID));
            listRecyclerView.setAdapter(adapter);
        }
        //Intent intent=new Intent();
        //String userId=intent.getStringExtra("user_id");


        return view;
        }
   private List<Datashow> getData(String userId){
       List<Datashow> datashows=new ArrayList<>();
       //String userId="2016212050048";
       userGroupList=DataSupport.where("userId=?",userId).find(UserGroup.class);
       if(userGroupList.size()>0){
           for(UserGroup userGroup:userGroupList){
               String greenhouseId=userGroup.getGreenhouseId().toString();
               dataNowList=DataSupport.where("greenhouseId=? and chuanganqiId=? and isnew=?",greenhouseId,"1","1").find(DataNow.class);
               if(dataNowList.size()>0){
                   Datashow datashow=new Datashow();
                   datashow.setGreenhouseId(greenhouseId);
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
                       datashow.setIsnew(dataNow.isnew());
                       datashow.setUpdatetime(dataNow.getUpdatetime());
                       datashow.setPicture(R.mipmap.wenshi);
                   }
                   datashow.setUserId(userId);
                   datashows.add(datashow);
                   List<Datashow> datashowList=datashows;
               }
           }
       }
        return datashows;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.content_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }
    class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
        private List<Datashow> mDataList;
        class ViewHolder extends RecyclerView.ViewHolder{
            TextView HuanWenText;
            TextView HuanShiText;
            TextView GuangZhaoText;
            TextView ErYangHuaTanText;
            TextView NumberText;
            ImageView PictureText;
            public ViewHolder(View view){
                super(view);
                HuanWenText=(TextView)view.findViewById(R.id.huanwen_tv);
                HuanShiText=(TextView)view.findViewById(R.id.huanshi_tv);
                GuangZhaoText=(TextView)view.findViewById(R.id.guangzhao_tv);
                ErYangHuaTanText=(TextView)view.findViewById(R.id.eryanghuatan_tv);
                NumberText=(TextView)view.findViewById(R.id.number_tv);
                PictureText=(ImageView)view.findViewById(R.id.picture_img);
            }
        }
        public DataAdapter(List<Datashow> datashowList){
            mDataList=datashowList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
            final View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
            final ViewHolder holder=new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Datashow dataShow=mDataList.get(holder.getAdapterPosition());
                    if(isTwoPane){
                        ContentFragment contentFragment=(ContentFragment)getFragmentManager().findFragmentById(R.id.content_fragment);
                        contentFragment.refresh(dataShow);
                    }
                    else {
                        ContentActivity.actionStart(getActivity(),dataShow);
                   }
                }
            });
            return holder;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder,int position){
            Datashow datashow=mDataList.get(position);

                holder.HuanWenText.setText(datashow.getHuanwen());
                holder.HuanShiText.setText(datashow.getHuanshi());
                holder.GuangZhaoText.setText(datashow.getGuangzhao());
                holder.ErYangHuaTanText.setText(datashow.getEryanghuatan());
            holder.PictureText.setImageResource(datashow.getPicture());
            holder.NumberText.setText(datashow.getGreenhouseId());
        }
        @Override
        public int getItemCount(){
            return mDataList.size();
        }
    }
}
