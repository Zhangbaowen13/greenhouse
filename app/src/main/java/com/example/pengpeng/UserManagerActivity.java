package com.example.pengpeng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pengpeng.db.User;

import org.litepal.crud.DataSupport;

import java.util.List;

public class UserManagerActivity extends AppCompatActivity {
    Button addData_Button;
    Button update_Button;
    Button delete_Button;
    Button require_Button;
    TextView userName_Text;
    TextView phone_Text;
    TextView Id_Text;
    TextView password_Text;
    TextView leibie_Text;
    TextView xueyuan_Text;
    TextView xuezhi_Text;
    TextView zhuanye_Text;
    TextView daoshi_Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);

        require_Button=(Button)findViewById(R.id.require_Button);
        delete_Button=(Button)findViewById(R.id.delete_Button);
        update_Button=(Button)findViewById(R.id.update_button);
        addData_Button=(Button)findViewById(R.id.add_data);

        userName_Text=(TextView)findViewById(R.id.user_name);
        phone_Text=(TextView)findViewById(R.id.phone);
        Id_Text=(TextView)findViewById(R.id.user_id);
        password_Text=(TextView)findViewById(R.id.password);
        leibie_Text=(TextView)findViewById(R.id.leibie);
        xueyuan_Text=(TextView)findViewById(R.id.xueyuan);
        xuezhi_Text=(TextView)findViewById(R.id.xuezhi);
        zhuanye_Text=(TextView)findViewById(R.id.zhuanye);
        daoshi_Text=(TextView)findViewById(R.id.daoshi);

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
}
