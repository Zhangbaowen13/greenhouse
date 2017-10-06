package com.example.pengpeng;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.pengpeng.db.Datashow;
import com.example.pengpeng.db.Shebei;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class ContentFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.content_frag,container,false);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.shebei_recycler_view);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ShebeiAdapter adapter=new ShebeiAdapter(getSheBei());
        recyclerView.setAdapter(adapter);
        return view;
    }
    private List<Shebei> getSheBei(){
        List<Shebei> sheBeiList=new ArrayList<>();
        String greenhouseid="1";
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
            }
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
                    if(sheBei.isopen()){
                        Log.d("CountActivity","是否关闭设备");
                    }
                    else{
                        Log.d("CountActivity","是否打开设备");
                    }
                }
            });
            return holder;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder,int position){
            Shebei shebei=mShebeiList.get(position);
            holder.OpenSW.setChecked(shebei.isopen());
            holder.NameTV.setText(shebei.getShebeiName());
        }
        @Override
        public int getItemCount(){return mShebeiList.size();}
    }
    public void refresh(Datashow datashow){

        View visibilityLayout=view.findViewById(R.id.visibility_layout);

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
}
