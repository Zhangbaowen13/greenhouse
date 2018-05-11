package com.example.pengpeng;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pengpeng.db.UserGroup;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/10/7 0007.
 */

public class GreenhouseEditorFragment extends Fragment {  private TextView greenhouseid_textview;
    private EditText huanwenMin_textview;
    private EditText huanwenMax_textview;
    private EditText huanshiMin_textview;
    private EditText huanshiMax_textview;
    private EditText guangzhaoMin_textview;
    private EditText guangzhaoMax_textview;
    private EditText eryanghuatanMin_textview;
    private EditText eryanghuatanMax_textview;
    private EditText tuwenMin_textview;
    private EditText tuwenMax_textview;
    private EditText tushiMin_textview;
    private EditText tushiMax_textview;
    private Button xiugai_button;
    private ImageView mPhotoView;
    private ImageButton mPhotoButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view=inflater.inflate(R.layout.greenhouse_editor_frag,container,false);
        //初始化控件
        greenhouseid_textview=(TextView)view.findViewById(R.id.greenhouseid_tv);
        huanwenMin_textview=(EditText)view.findViewById(R.id.huanwenMin_tv);
        huanwenMax_textview=(EditText)view.findViewById(R.id.huanwenMax_tv);
        huanshiMin_textview=(EditText)view.findViewById(R.id.huanshiMin_tv);
        huanshiMax_textview=(EditText)view.findViewById(R.id.huanshiMax_tv);
        guangzhaoMin_textview=(EditText)view.findViewById(R.id.guangzhaoMin_tv);
        guangzhaoMax_textview=(EditText)view.findViewById(R.id.guangzhaoMax_tv);
        eryanghuatanMin_textview=(EditText)view.findViewById(R.id.eryanghuaTanMin_tv);
        eryanghuatanMax_textview=(EditText)view. findViewById(R.id.eryanghuaTanMax_tv);
        tuwenMin_textview=(EditText)view.findViewById(R.id.tuwenMin_tv);
        tuwenMax_textview=(EditText)view.findViewById(R.id.tuwenMax_tv);
        tushiMin_textview=(EditText)view.findViewById(R.id.tuShiMin_tv);
        tushiMax_textview=(EditText)view. findViewById(R.id.tuShiMax_tv);
        xiugai_button=(Button) view.findViewById(R.id.xiugai_bt);
        mPhotoView=(ImageView) view.findViewById(R.id.greenhouse_photo_Img);
        mPhotoButton=(ImageButton)view.findViewById(R.id.camera_Imgbt);
        Bundle bundle = getArguments();
        if (bundle != null) {
            final String usergroupID = bundle.getString("UserGroup_Id");
            datashow(usergroupID);
            xiugai_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<UserGroup> userGroupList= DataSupport.where("id=?",usergroupID).find(UserGroup.class);
                    if(userGroupList.size()>0){
                        for(UserGroup userGroup:userGroupList){
                            String GroupNumber=userGroup.getGroupnumber();
                            if(userGroup.getIszuzhang().equals("true")){
                                UserGroup userGroup1=new UserGroup();
                                userGroup1.setHuanwenMin(huanwenMin_textview.getText().toString());
                                userGroup1.setHuanwenMax(huanwenMax_textview.getText().toString());
                                userGroup1.setHuanshiMin(huanshiMin_textview.getText().toString());
                                userGroup1.setHuanshiMax(huanshiMax_textview.getText().toString());
                                userGroup1.setGuangzhaoMin(guangzhaoMin_textview.getText().toString());
                                userGroup1.setGuangzhaoMax(guangzhaoMax_textview.getText().toString());
                                userGroup1.setEryanghuatanMin(eryanghuatanMin_textview.getText().toString());
                                userGroup1.setEryanghuatanMax(eryanghuatanMax_textview.getText().toString());
                                userGroup1.setTuwenMin(tuwenMin_textview.getText().toString());
                                userGroup1.setTuwenMax(tuwenMax_textview.getText().toString());
                                userGroup1.setTushiMin(tushiMin_textview.getText().toString());
                                userGroup1.setTushiMax(tushiMax_textview.getText().toString());
                                userGroup1.updateAll("groupnumber=?",GroupNumber);
                                Toast.makeText(getContext(),"管理方案修改成功！",Toast.LENGTH_SHORT).show();
                            }
                            else if(userGroup.getIszuzhang().equals("false")){
                                Toast.makeText(getContext(),"抱歉，您不是组长，没有修改权限！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        }return view;
    }
    //获取照片文件名
   /* public File getPhotoFile(DataNow dataNow) {
        File externalFilesDir = mContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) {
            return null;
        }
        return new File(externalFilesDir, dataNow.getPhotoFilename());
    }*/
    //显示数据
    private void datashow(String usergroupId){
        List<UserGroup> userGroupList= DataSupport.where("id=?",usergroupId).find(UserGroup.class);
        if(userGroupList.size()>0){
            for(UserGroup userGroup:userGroupList){
                greenhouseid_textview.setText(userGroup.getGreenhouseId()+"号温室");
                huanwenMin_textview.setText(userGroup.getHuanwenMin());
                huanwenMax_textview.setText(userGroup.getHuanwenMax());
                huanshiMin_textview.setText(userGroup.getHuanshiMin());
                huanshiMax_textview.setText(userGroup.getHuanshiMax());
                guangzhaoMin_textview.setText(userGroup.getGuangzhaoMin());
                guangzhaoMax_textview.setText(userGroup.getGuangzhaoMax());
                eryanghuatanMin_textview.setText(userGroup.getEryanghuatanMin());
                eryanghuatanMax_textview.setText(userGroup.getEryanghuatanMax());
                tuwenMin_textview.setText(userGroup.getTuwenMin());
                tuwenMax_textview.setText(userGroup.getTuwenMax());
                tushiMin_textview.setText(userGroup.getTushiMin());
                tushiMax_textview.setText(userGroup.getTushiMax());
            }
        }
    }
}
