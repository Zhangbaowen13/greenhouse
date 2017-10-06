package com.example.pengpeng;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pengpeng.db.Greenhouse;
import com.example.pengpeng.db.User;
import com.example.pengpeng.db.Xueyuan;
import com.example.pengpeng.db.Zhuanye;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import static org.litepal.LitePalApplication.getContext;

public class PersonalActivity extends AppCompatActivity {
private TextView account_tv;
    private EditText username_tv;
    private EditText phone_tv;
    private Spinner xueyuan_spinner;
    private Spinner leibie_spinner;
    private Spinner zhuanye_spinner;
    private EditText daoshi_et;
    private ArrayAdapter<String> adapter;
    private  List<Xueyuan> xueyuanList;
    private List<Zhuanye> zhuanyeList;
    private ImageView beijing_image;
    private Button xiugai_bt;
    private FloatingActionButton password_fab;
    private List<String> dataList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        //控件初始化
        account_tv=(TextView)findViewById(R.id.account_tv);
        username_tv=(EditText)findViewById(R.id.username_tv);
        phone_tv=(EditText)findViewById(R.id.phone_tv);
        xueyuan_spinner=(Spinner)findViewById(R.id.xueyuan_spinner);
        leibie_spinner=(Spinner)findViewById(R.id.leibie_spinner);
        zhuanye_spinner=(Spinner)findViewById(R.id.zhuanye_spinner);
        daoshi_et=(EditText)findViewById(R.id.daoshi_et);
        beijing_image=(ImageView)findViewById(R.id.beijing_image);
        password_fab=(FloatingActionButton)findViewById(R.id.password_fab);
        xiugai_bt=(Button)findViewById(R.id.xiugai_bt);
        //修改个人信息点击事件监听
        xiugai_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID=getIntent().getStringExtra("user_id");
                List<User>userList1=DataSupport.where("xuehao=?",userID).find(User.class);
                List<User>userList2=DataSupport.where("phone=?",userID).find(User.class);
                User user1=new User();
                user1.setUserName(username_tv.getText().toString());
                user1.setPhone(phone_tv.getText().toString());
                user1.setXueyuan(xueyuan_spinner.getSelectedItem().toString());
                user1.setXuezhi(leibie_spinner.getSelectedItem().toString());
                user1.setZhuanye(zhuanye_spinner.getSelectedItem().toString());
                user1.setDaoshi(daoshi_et.getText().toString());
                if(userList1.size()!=0){user1.updateAll("xuehao=?",userID);}
                else if(userList2.size()!=0){user1.updateAll("phone=?",userID);}
                Toast.makeText(PersonalActivity.this, "个人信息修改成功", Toast.LENGTH_SHORT).show();
            }
        });
        //悬浮按钮修改密码
        password_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
        //配置学院spinner适配器
        xueyuanList=DataSupport.findAll(Xueyuan.class);
        if(xueyuanList.size()!=0){
            for(Xueyuan xueyuan:xueyuanList){
                dataList.add(xueyuan.getXueyuan());
            }
        }
        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,dataList);
        xueyuan_spinner.setAdapter(adapter);
        xueyuan_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //配置专业spinner适配器
                String xueyuanName=xueyuanList.get(position).getXueyuan();
                zhuanyeAdapter(xueyuanName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //配置学制spinner适配器
        final List<String> dataList2=new ArrayList<>();
        dataList2.add("专硕");
        dataList2.add("学硕");
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,dataList2);
        leibie_spinner.setAdapter(adapter2);
        leibie_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String xuezhi=dataList2.get(position).toString();
                Intent intent=new Intent(PersonalActivity.this,PersonalActivity.class);
                intent.putExtra("xuezhi",xuezhi);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Toolbar导航栏
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.personal_collapsing_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //显示数据
        String userID=getIntent().getStringExtra("user_id");
        account_tv.setText(userID);
        List<User> userList= DataSupport.where("xuehao=?",userID).find(User.class);
        List<User> userList2= DataSupport.where("phone=?",userID).find(User.class);
        if(userList.size()>0){
            for(User user:userList){
                showData(user);
            }
        }else if(userList2.size()>0){
            for(User user:userList2){
                showData(user);
            }
        }
        collapsingToolbar.setTitle("个人信息");
    }
    public boolean onCreateOptionsMenu(Menu menu){
        return true;
    }
    public void zhuanyeAdapter(String xueyuanName){
        zhuanyeList=DataSupport.where("xueyuan=?",xueyuanName).find(Zhuanye.class);
        List<String> dataList1=new ArrayList<>();
        if (zhuanyeList.size()!=0){
            for (Zhuanye zhuanye:zhuanyeList){
                dataList1.add(zhuanye.getZhuanye());
            }
        }
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,dataList1);
        zhuanye_spinner.setAdapter(adapter1);
        zhuanye_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String zhuanyeName=zhuanyeList.get(position).getZhuanye();
                String xueyuanName=zhuanyeList.get(position).getXueyuan();
                Intent intent=new Intent(PersonalActivity.this,PersonalActivity.class);
                intent.putExtra("zhuanye",zhuanyeName);
                intent.putExtra("xueyuan",xueyuanName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void showData(User user){
        username_tv.setText(user.getUserName());
        phone_tv.setText(user.getPhone());
        int i=getposition("xueyuan",user.getXueyuan(),null);
        xueyuan_spinner.setSelection(i);
        //配置专业spinner适配器
        zhuanyeAdapter(user.getXueyuan());
        int n=getposition("zhuanye",user.getXueyuan(),user.getZhuanye());
        zhuanye_spinner.setSelection(n);
        if(user.getXuezhi().equals("专硕")){leibie_spinner.setSelection(0);}
        else if(user.getXuezhi().equals("学硕")){leibie_spinner.setSelection(1);}
        daoshi_et.setText(user.getDaoshi());
        beijing_image.setImageResource(R.mipmap.daohangbeijing);
    }
    public int getposition(String className,String xueyuanName,String zhuanyeName){
        int i=0;
        if(className.equals("xueyuan")){
            List<Xueyuan> xueyuanList1=DataSupport.findAll(Xueyuan.class);
            for(Xueyuan xueyuan:xueyuanList1){
                if(xueyuan.getXueyuan().equals(xueyuanName)){return i;}
                else {i=i+1;}
            }
        }else if(className.equals("zhuanye")){
            List<Zhuanye>zhuanyeList1=DataSupport.where("xueyuan=?",xueyuanName).find(Zhuanye.class);
            for(Zhuanye zhuanye:zhuanyeList1){
                if(zhuanye.getZhuanye().equals(zhuanyeName)){return i;}
                else {i=i+1;}
            }
        }
        return i;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String dizhi=getIntent().getStringExtra("dizhi");

        switch(item.getItemId()){
            case R.id.back:
                if(dizhi.equals("GreenhouselistActivity")){
                Intent intent=new Intent(PersonalActivity.this,GreenhouselistActivity.class);
                    String userID=getIntent().getStringExtra("user_id");
                    intent.putExtra("user_id",userID);
                startActivity(intent);
                finish();}else if(dizhi.equals("ContentActivity")){
                finish();
            }
                break;
            case android.R.id.home:
                if(dizhi.equals("GreenhouselistActivity")){
                    Intent intent=new Intent(PersonalActivity.this,GreenhouselistActivity.class);
                    String userID=getIntent().getStringExtra("user_id");
                    intent.putExtra("user_id",userID);
                    startActivity(intent);
                    finish();}else if(dizhi.equals("ContentActivity")){
                    finish();}
            default:
        }
        return super.onOptionsItemSelected(item);
    }
    //显示对话框dialog
    private void showInputDialog() {
        final EditText editText = new EditText(PersonalActivity.this);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(PersonalActivity.this);
        inputDialog.setTitle("请输入原密码：").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userID=getIntent().getStringExtra("user_id");
                        List<User> userList1=DataSupport.where("xuehao=?",userID).find(User.class);
                        List<User>userList2=DataSupport.where("phone=?",userID).find(User.class);
                        List<User>users;
                        if(userList1.size()!=0){users=userList1;
                            for (User user:users){
                                String password=user.getPassword().toString();
                                if(editText.getText().toString().equals(password)){
                                    showInputDialog2("xuehao");
                                }
                                else {
                                    Toast.makeText(PersonalActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                                }}
                        }
                        else if(userList2.size()!=0){users=userList2;
                            for (User user:users){
                                String password=user.getPassword().toString();
                                if(editText.getText().toString().equals(password)){
                                    showInputDialog2("phone");
                                }
                                else {
                                    Toast.makeText(PersonalActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                                }}

                        }

                    }
                }).show();
    }
    //显示对话框dialog
    private void showInputDialog2(final String IdType) {
        final EditText editText = new EditText(PersonalActivity.this);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(PersonalActivity.this);
        inputDialog.setTitle("请输入新密码：").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userID=getIntent().getStringExtra("user_id");
                        User user1=new User();
                        user1.setPassword(editText.getText().toString());
                        if(IdType.equals("xuehao")){user1.updateAll("xuehao=?",userID);}
                        else if(IdType.equals("phone")){user1.updateAll("phone=?",userID);}
                        Toast.makeText(PersonalActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}
