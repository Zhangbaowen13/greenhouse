package com.example.pengpeng;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pengpeng.db.User;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.StringTokenizer;

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
                String userID=userId.getText().toString();
                List<User> users1= DataSupport.where("xuehao=?",userID).find(User.class);
                for(User user:users1){
                intent.putExtra("user_id",userID);
                startActivity(intent);
                finish();}
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
    }
}
