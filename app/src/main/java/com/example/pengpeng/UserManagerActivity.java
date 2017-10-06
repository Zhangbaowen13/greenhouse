package com.example.pengpeng;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pengpeng.db.Greenhouse;
import com.example.pengpeng.db.User;
import com.example.pengpeng.db.UserGroup;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import static org.litepal.LitePalApplication.getContext;

public class UserManagerActivity extends AppCompatActivity {
    private Button addData_Button;
    private Button update_Button;
    private Button delete_Button;
    private Button require_Button;
    private Button add_usergroup_Button;
    private CheckBox iszuzhang_CheckBox;
    private EditText userName_Text;
    private EditText phone_Text;
    private EditText Id_Text;
    private EditText password_Text;
    private EditText leibie_Text;
    private EditText xueyuan_Text;
    private EditText xuezhi_Text;
    private EditText zhuanye_Text;
    private EditText daoshi_Text;
    private EditText groupNumb_Text;
    private EditText huanwenMax_Text;
    private EditText huanwenMin_Text;
    private EditText huanshiMax_Text;
    private EditText huanshiMin_Text;
    private EditText tuwenMax_Text;
    private EditText tuwenMin_Text;
    private EditText tushiMax_Text;
    private EditText tushiMin_Text;
    private EditText guangzhaoMax_Text;
    private EditText guangzhaoMin_Text;
    private EditText eryanghuatanMax_Text;
    private EditText eryanghuatanMin_Text;
    private EditText greenhouseid_text;
    private Spinner greenhouseList_spinner;
    private ArrayAdapter<String> adapter;
    private  List<Greenhouse> greenhouseList;
    private List<String> dataList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);

        require_Button=(Button)findViewById(R.id.require_Button);
        delete_Button=(Button)findViewById(R.id.delete_Button);
        update_Button=(Button)findViewById(R.id.update_button);
        addData_Button=(Button)findViewById(R.id.add_data);
        add_usergroup_Button=(Button)findViewById(R.id.add_usergroup);
        iszuzhang_CheckBox=(CheckBox)findViewById(R.id.iszuzhang_cb);

        huanwenMax_Text=(EditText)findViewById(R.id.huanwenMax_et);
        huanwenMin_Text=(EditText)findViewById(R.id.huanwenMin_et);
        huanshiMax_Text=(EditText)findViewById(R.id.huanshiMax_et);
        huanshiMin_Text=(EditText)findViewById(R.id.huanshiMin_et);
        tuwenMax_Text=(EditText)findViewById(R.id.tuwenMax_et);
        tuwenMin_Text=(EditText)findViewById(R.id.tuwenMin_et);
        tushiMax_Text=(EditText)findViewById(R.id.tushiMax_et);
        tushiMin_Text=(EditText)findViewById(R.id.tushiMin_et);
        guangzhaoMax_Text=(EditText)findViewById(R.id.guangzhaoMax_et);
        guangzhaoMin_Text=(EditText)findViewById(R.id.guangzhaoMin_et);
        eryanghuatanMax_Text=(EditText)findViewById(R.id.eryanghuatanMax_et);
        eryanghuatanMin_Text=(EditText)findViewById(R.id.eryanghuatanMin_et);
        userName_Text=(EditText)findViewById(R.id.user_name);
        phone_Text=(EditText)findViewById(R.id.phone);
        Id_Text=(EditText)findViewById(R.id.user_id);
        password_Text=(EditText)findViewById(R.id.password);
        leibie_Text=(EditText)findViewById(R.id.leibie);
        xueyuan_Text=(EditText)findViewById(R.id.xueyuan);
        xuezhi_Text=(EditText)findViewById(R.id.xuezhi);
        zhuanye_Text=(EditText)findViewById(R.id.zhuanye);
        daoshi_Text=(EditText)findViewById(R.id.daoshi);
        groupNumb_Text=(EditText)findViewById(R.id.groupNumb_Et);
        greenhouseid_text=(EditText)findViewById(R.id.greenhouseid_et);
        greenhouseList_spinner=(Spinner)findViewById(R.id.greenhouselist_spinner);
        //String xuehao=getIntent().getStringExtra("user_id");
        greenhouseList=DataSupport.findAll(Greenhouse.class);
        if(greenhouseList.size()!=0){
            for(Greenhouse greenhouse:greenhouseList){
                dataList.add(greenhouse.getGreenhouseName());
            }
        }
        adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,dataList);
        greenhouseList_spinner.setAdapter(adapter);

        greenhouseList_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int greenhouseId=greenhouseList.get(position).getId();
                String GreenID=String.valueOf(greenhouseId);
                Intent intent=new Intent(UserManagerActivity.this,UserManagerActivity.class);
                intent.putExtra("greenhouse_id_Spinner",GreenID);
                String greenid=getIntent().getStringExtra("greenhouse_id_Spinner");
                //startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               /* Intent intent=new Intent();
                intent.putExtra("greenhouse_id_Spinner","");*/
            }
        });
add_usergroup_Button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        UserGroup userGroup=new UserGroup();
        //String userid=getIntent().getStringExtra("user_id");
        String groupnumb=groupNumb_Text.getText().toString();
       // String greenid=getIntent().getStringExtra("greenhouse_id_Spinner");
        boolean iszuzhang=iszuzhang_CheckBox.isChecked();
        String zuzhang=String.valueOf(iszuzhang);
        String huanwenMax=huanwenMax_Text.getText().toString();
        String huanwenMin=huanwenMin_Text.getText().toString();
        String tuwenMax=tuwenMax_Text.getText().toString();
        String tuwenMin=tuwenMin_Text.getText().toString();
        String huanshiMax=huanshiMax_Text.getText().toString();
        String huanshiMin=huanshiMin_Text.getText().toString();
        String tushiMax=tushiMax_Text.getText().toString();
        String tushiMin=tushiMin_Text.getText().toString();
        String guangzhaoMax=guangzhaoMax_Text.getText().toString();
        String guangzhaoMin=guangzhaoMin_Text.getText().toString();
        String eryanghuatanMax=eryanghuatanMax_Text.getText().toString();
        String eryanghuatanMin=eryanghuatanMin_Text.getText().toString();
        String greenid=greenhouseid_text.getText().toString();
        String userid=Id_Text.getText().toString();

        userGroup.setUserId(userid);
        userGroup.setGroupnumber(groupnumb);
        userGroup.setGreenhouseId(greenid);
        userGroup.setIszuzhang(zuzhang);
        userGroup.setHuanwenMax(huanwenMax);
        userGroup.setHuanwenMin(huanwenMin);
        userGroup.setTuwenMax(tuwenMax);
        userGroup.setTuwenMin(tuwenMin);
        userGroup.setHuanshiMax(huanshiMax);
        userGroup.setHuanshiMin(huanshiMin);
        userGroup.setTushiMax(tushiMax);
        userGroup.setTushiMin(tushiMin);
        userGroup.setGuangzhaoMax(guangzhaoMax);
        userGroup.setGuangzhaoMin(guangzhaoMin);
        userGroup.setEryanghuatanMax(eryanghuatanMax);
        userGroup.setEryanghuatanMin(eryanghuatanMin);
        userGroup.save();
    }
});
        addData_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();
                String userName=userName_Text.getText().toString();
                String phone=phone_Text.getText().toString();
                String userId=Id_Text.getText().toString();
                String password=password_Text.getText().toString();
                String leiBie=leibie_Text.getText().toString();
                String xueYuan=xueyuan_Text.getText().toString();
                String xueZhi=xuezhi_Text.getText().toString();
                String zhuanYe=zhuanye_Text.getText().toString();
                String daoShi=daoshi_Text.getText().toString();

                user.setXuehao(userId);
                user.setPhone(phone);
                user.setDaoshi(daoShi);
                user.setUserName(userName);
                user.setPassword(password);
                user.setLeibie(leiBie);
                user.setXueyuan(xueYuan);
                user.setXuezhi(xueZhi);
                user.setZhuanye(zhuanYe);
                user.save();
            }
        });
        require_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users= DataSupport.where("id=?","3").find(User.class);
                for(User user:users){
                    userName_Text.setText(user.getUserName());
                    phone_Text.setText(user.getPhone());
                    Id_Text.setText(user.getXuehao());
                    password_Text.setText(user.getPassword());
                    leibie_Text.setText(user.getLeibie());
                    xueyuan_Text.setText(user.getXueyuan());
                    xuezhi_Text.setText(user.getXuezhi());
                    zhuanye_Text.setText(user.getZhuanye());
                    daoshi_Text.setText(user.getDaoshi());
                }
            }
        });
        update_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();
                user.setDaoshi("宗哲英");
                user.updateAll("id=?","3");
            }
        });
        delete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(User.class,"id=?","5");
            }
        });
    }

    /*private class MyAdapter extends BaseAdapter {
        private List<Greenhouse> mList;
        private Context mContext;
        public MyAdapter(Context gContext,List<Greenhouse> gList){
            this.mContext=gContext;
            this.mList=gList;
        }
        @Override
        public int getCount(){
            return mList.size();
        }
        @Override
        public Object getItem(int position){
            return mList.get(position);
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater layoutInflater=LayoutInflater.from(mContext);
            convertView=layoutInflater.inflate(R.layout.spinner_item,null);
            if(convertView!=null){
                TextView greenhouseName=(TextView)convertView.findViewById(R.id.spinner_text);
                List<Greenhouse> greenhouses=DataSupport.findAll(Greenhouse.class);
                for (Greenhouse greenhouse:greenhouses) {
                    greenhouseName.setText(greenhouse.getGreenhouseName());
                }
            }
            return convertView;
        }
    }*/


}
