package com.example.pengpeng;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pengpeng.db.DataNow;
import com.example.pengpeng.db.Datashow;
import com.example.pengpeng.db.Greenhouse;
import com.example.pengpeng.db.User;
import com.example.pengpeng.db.UserGroup;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/6 0006.
 */

public class ListManageFragment extends Fragment {
    private boolean isTwoPane;
    private LinearLayout greenhouse_item_bc;
    private TextView number_tv;
    private ImageView picture_img;
    private TextView huanwen_tv;
    private TextView eryanghuatan_tv;
    private TextView huanshi_tv;
    private TextView guangzhao_tv;
    private List<Greenhouse> greenhouseList;
    private List<UserGroup> userGroupList;
    private List<DataNow>dataNowList;
    private List<Datashow> datashows;
    private List<String> dataList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        //初始化控件
        View view=inflater.inflate(R.layout.list_manage_frag,container,false);
        RecyclerView listRecyclerView=(RecyclerView)view.findViewById(R.id.list_manage_recycler_view);
        greenhouse_item_bc=(LinearLayout)view.findViewById(R.id.greenhouse_manage_item_background);
        number_tv=(TextView)view.findViewById(R.id.number_manage_tv);
        picture_img=(ImageView) view.findViewById(R.id.picture_manage_img);
        huanwen_tv=(TextView)view.findViewById(R.id.huanwen_manage_tv);
        eryanghuatan_tv=(TextView)view.findViewById(R.id.eryanghuatan_manage_tv);
        huanshi_tv=(TextView)view.findViewById(R.id.huanshi_manage_tv);
        guangzhao_tv=(TextView)view.findViewById(R.id.guangzhao_manage_tv);
//Intent intent=Intent.getIntent()
        // final String weatherId = getIntent().getStringExtra("weather_id");

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        listRecyclerView.setLayoutManager(layoutManager);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String userID = bundle.getString("userID");
            String dizhi=bundle.getString("dizhi");
            ListManageFragment.DataAdapter adapter=new ListManageFragment.DataAdapter(getData(userID));
            listRecyclerView.setAdapter(adapter);
        }
        //Intent intent=new Intent();
        //String userId=intent.getStringExtra("user_id");

        return view;
    }
    private List<Datashow> getData(String userId ){
        List<Datashow> datashows=new ArrayList<>();
        //String userId="2016212050048";
        userGroupList= DataSupport.where("userId=?",userId).find(UserGroup.class);
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
       /* if (getActivity().findViewById(R.id.content_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }*/
    }
    class DataAdapter extends RecyclerView.Adapter<ListManageFragment.DataAdapter.ViewHolder>{
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
                HuanWenText=(TextView)view.findViewById(R.id.huanwen_manage_tv);
                HuanShiText=(TextView)view.findViewById(R.id.huanshi_manage_tv);
                GuangZhaoText=(TextView)view.findViewById(R.id.guangzhao_manage_tv);
                ErYangHuaTanText=(TextView)view.findViewById(R.id.eryanghuatan_manage_tv);
                NumberText=(TextView)view.findViewById(R.id.number_manage_tv);
                PictureText=(ImageView)view.findViewById(R.id.picture_manage_img);
            }
        }
        public DataAdapter(List<Datashow> datashowList){
            mDataList=datashowList;
        }
        @Override
        public ListManageFragment.DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            final View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_manage_item,parent,false);
            final ListManageFragment.DataAdapter.ViewHolder holder=new ListManageFragment.DataAdapter.ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Datashow dataShow=mDataList.get(holder.getAdapterPosition());
                    /* if(isTwoPane){
                        ContentFragment contentFragment=(ContentFragment)getFragmentManager().findFragmentById(R.id.content_fragment);
                        contentFragment.refresh(dataShow);
                    }
                    else {
                        ContentActivity.actionStart(getActivity(),dataShow);
                    }*/
                    Bundle bundle = getArguments();
                    if (bundle != null) {
                        String dizhi=bundle.getString("dizhi");
                    showNormalDialog(dataShow.getUserId(),dataShow.getGreenhouseId(),dizhi);}
                }
            });
            return holder;
        }
        @Override
        public void onBindViewHolder(ListManageFragment.DataAdapter.ViewHolder holder, int position){
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
        //对话框
        private void showNormalDialog(final String userid, final String greenhouseId,final String dizhi){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
            final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getContext());
            //normalDialog.setIcon(R.drawable.icon_dialog);
            normalDialog.setTitle("温室管理");
            normalDialog.setMessage("你要选择哪个操作?");
            normalDialog.setPositiveButton("编辑",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getContext(),"编辑",Toast.LENGTH_SHORT).show();
                        }
                    });
            normalDialog.setNegativeButton("删除",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showNormalDialog2(userid,greenhouseId,dizhi);
                        }
                    });
            // 显示
            normalDialog.show();
        }
        //对话框
        private void showNormalDialog2(final String userid, final String greenhouseId,final String dizhi){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
            final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getContext());
            //normalDialog.setIcon(R.drawable.icon_dialog);
            final List<UserGroup> userGroups=DataSupport.where("userId=? and greenhouseId=?",userid,greenhouseId).find(UserGroup.class);
            normalDialog.setTitle("温室管理");
            for(UserGroup userGroup:userGroups){
                String iszuzhang=userGroup.getIszuzhang().toString();
                if(iszuzhang.equals("true")){
                    normalDialog.setMessage("组长，确定解散该组吗?");
                }else {normalDialog.setMessage("确定删除吗?");
                }
            }
            normalDialog.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for(UserGroup userGroup:userGroups){
                                String iszuzhang=userGroup.getIszuzhang().toString();
                                if(iszuzhang.equals("true")){
                                    String groupnumber=userGroup.getGroupnumber().toString();
                                    DataSupport.deleteAll(UserGroup.class,"groupnumber=?",groupnumber);
                                    Toast.makeText(getContext(),"该组解散成功！",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getContext(),GreenhouseActivity.class);
                                    intent.putExtra("dizhi",dizhi);
                                    intent.putExtra("user_id",userid);
                                   // intent.putExtra("dizhi","ContentActivity");
                                    startActivity(intent);
                                }else if(iszuzhang.equals("false")){
                                    int id=userGroup.getId();
                                    String Id=String.valueOf(id);
                                    DataSupport.deleteAll(UserGroup.class,"id=?",Id);
                                    Toast.makeText(getContext(),"删除成功！",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getContext(),GreenhouseActivity.class);
                                    intent.putExtra("dizhi",dizhi);
                                    intent.putExtra("user_id",userid);
                                    // intent.putExtra("dizhi","ContentActivity");
                                    startActivity(intent);
                                }

                            }
                        }
                    });
            normalDialog.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //...To-do
                        }
                    });
            // 显示
            normalDialog.show();
        }
    }
}
