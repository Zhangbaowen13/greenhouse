package com.example.pengpeng;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class ListFragment extends Fragment {
    private boolean isTwoPane;
    private LinearLayout greenhouse_item_bc;
    private TextView number_tv;
    private ImageView picture_img;
    private TextView huanwen_tv;
    private TextView eryanghuatan_tv;
    private TextView huanshi_tv;
    private TextView guangzhao_tv;
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

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        listRecyclerView.setLayoutManager(layoutManager);
        DataAdapter adapter=new DataAdapter(getData());
        listRecyclerView.setAdapter(adapter);
        return view;}
    private List<DataNow> getData(){
        List<DataNow> dataNowList=new ArrayList<>();
        for(int i=1;i<50;i++){
            DataNow dataNow=new DataNow();

            dataNow.setPicture(R.mipmap.wenshi);
            dataNowList.add(dataNow);
        }
        return dataNowList;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       /* if (getActivity().findViewById(R.id.content_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }*/
    }
    class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
        private List<DataNow> mDataList;
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
        public DataAdapter(List<DataNow> dataNowList){
            mDataList=dataNowList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
            final View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
            final ViewHolder holder=new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataNow dataNow=mDataList.get(holder.getAdapterPosition());
                   /* if(isTwoPane){
                        ContentFragment contentFragment=(ContentFragment)getFragmentManager().findFragmentById(R.id.content_fragment);
                        contentFragment.refresh(dataNow.getHuanwen(),dataNow.getHuanshi(),dataNow.getGuangzhao(),dataNow.getEryanghuatan(),dataNow.getTuwen(),dataNow.getTushi());
                    }
                    else {
                        ContentActivity.actionStart(getActivity(),dataNow.getHuanwen(),dataNow.getHuanshi(),dataNow.getGuangzhao(),dataNow.getEryanghuatan(),dataNow.getTuwen(),dataNow.getTushi());
                    }*/
                }
            });
            return holder;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder,int position){
            DataNow dataNow=mDataList.get(position);
            holder.PictureText.setImageResource(dataNow.getPicture());
        }
        @Override
        public int getItemCount(){
            return mDataList.size();
        }
    }
}
