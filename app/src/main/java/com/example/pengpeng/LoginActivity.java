package com.example.pengpeng;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pengpeng.db.User;
import com.example.pengpeng.gson.Weather;
import com.example.pengpeng.util.HttpUtil;
import com.example.pengpeng.util.Utility;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.StringTokenizer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private Button fanhui;
    private Button zhuce;
    private Button login;
    private EditText userId;
    private EditText password_et;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fanhui=(Button)findViewById(R.id.fanhui_button);
        zhuce=(Button)findViewById(R.id.zhuce_button);
        login=(Button)findViewById(R.id.login_bt);
        //监听登录button点击事件
       // login.setOnClickListener(this);
        userId=(EditText)findViewById(R.id.account_et);
        password_et=(EditText)findViewById(R.id.password_et);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass=(CheckBox)findViewById(R.id.remember_pass);
        boolean isRemember=pref.getBoolean("remember_password",false);
        if(isRemember){
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            userId.setText(account);
            password_et.setText(password);
            rememberPass.setChecked(true);
        }

        fanhui.setVisibility(View.INVISIBLE);
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,Login_titleActivity.class);
                //String userID=userId.getText().toString();
                //List<User> users1= DataSupport.where("xuehao=?",userID).find(User.class);
               // for(User user:users1){
                //intent.putExtra("user_id",userID);
                startActivity(intent);
                //finish();}
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userId.getText().toString().equals("")||password_et.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this,"请输入用户名密码",Toast.LENGTH_SHORT).show();}
                else {
                    String userID=userId.getText().toString();
                    String passWord=password_et.getText().toString();
                    List<User> users1= DataSupport.where("xuehao=?",userID).find(User.class);
                    List<User> users2= DataSupport.where("phone=?",userID).find(User.class);
                    Intent intent = new Intent(LoginActivity.this, GreenhouselistActivity.class);
                    if(users1.size()==0&&users2.size()==0){
                        Toast.makeText(LoginActivity.this,"该用户不存在",Toast.LENGTH_SHORT).show();
                    }else if (users1!=null){
                        for(User user:users1){
                           String mima=user.getPassword();
                            if (mima.equals(passWord))
                            {
                                editor=pref.edit();
                                if(rememberPass.isChecked()){
                                    editor.putBoolean("remember_password",true);
                                    editor.putString("account",userID);
                                    editor.putString("password",passWord);
                                    //获取屏幕分辨率

                                    DisplayMetrics dm = getResources().getDisplayMetrics();
                                    int screenWidth = dm.widthPixels;
                                    int screenHeight = dm.heightPixels;
                                    if(screenWidth>=900){
                                        intent = new Intent(LoginActivity.this, Greenhouselist2Activity.class);
                                    }
                                    intent.putExtra("user_id",userID);
                                    startActivity(intent);
                                    finish();
                                }else {editor.clear();}editor.apply();
                            }
                            else {
                                Toast.makeText(LoginActivity.this,"密码不正确",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }else if(users2!=null){
                        for(User user:users2){
                            String mima=user.getPassword();
                            if (mima.equals(passWord))
                            {editor=pref.edit();
                                if(rememberPass.isChecked()){
                                    editor.putBoolean("remember_password",true);
                                    editor.putString("account",userID);
                                    editor.putString("password",passWord);
                                }else {editor.clear();}editor.apply();
                                intent.putExtra("user_id",userID);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this,"密码不正确",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                
                
            }
        });
    } /*
    根据天气Id请求城市天气信息
     */
  /*  public  void requestUserGH(final String xuehao){
        String userUrl="192.168.56.112:8080/kyle_original/servlet/UserServlet?opt=findUser&username=张宝雯";
        HttpUtil.sendOkHttpRequest(userUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText=response.body().string();
                String password=(String) request.getAttribute("password");
                       // Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(user!=null){
                            SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit();
                            editor.putString("user",responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        }else {
                            Toast.makeText(LoginActivity.this,"该用户不存在",Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });}*/
    //实现按钮点击事件
  /*  public void onClick(View v){
        switch (v.getId()){
            case R.id.login_bt:
                new Thread(){
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        super.run();
                        try {
                            JSONObject json = new JSONObject();
                            json.put("UserName",userId.getText().toString().trim());
                            json.put("PassWord", password_et.getText().toString().trim());
                            //                      httpPostMethod(json);
                            HttpUtils.httpPostMethod(url, json, handler);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            Log.d("json", "解析JSON出错");
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            //} catch (ClientProtocolException e) {
                            // TODO Auto-generated catch block
                           // e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
        }
    }*/
}
