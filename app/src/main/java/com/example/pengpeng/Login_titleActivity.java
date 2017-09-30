package com.example.pengpeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pengpeng.db.Greenhouse;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class Login_titleActivity extends AppCompatActivity {
    Button addData_Button;
    Button update_Button;
    Button delete_Button;
    Button require_Button;
    Button usermanage_Button;
    EditText greenhouseName_EditText;
    EditText greenhouseNumb_EditText;
    EditText greenhouseProvince_EdiText;
    EditText greenhouseCity_EditText;
    EditText greenhouseCounty_EditText;
    EditText xiangxidizhi_EditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_title);
        Toolbar toolbar=(Toolbar)findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);

        greenhouseName_EditText=(EditText)findViewById(R.id.greenhouse_name);
        greenhouseNumb_EditText=(EditText)findViewById(R.id.greenhouse_numb);
        greenhouseProvince_EdiText=(EditText)findViewById(R.id.greenhouse_province);
        greenhouseCity_EditText=(EditText)findViewById(R.id.greenhouse_city);
        greenhouseCounty_EditText=(EditText)findViewById(R.id.greenhouse_county);
        xiangxidizhi_EditText=(EditText)findViewById(R.id.greenhouse_xiangxi);
        require_Button=(Button)findViewById(R.id.require_Button);
        delete_Button=(Button)findViewById(R.id.delete_Button);
        update_Button=(Button)findViewById(R.id.update_button);
        addData_Button=(Button)findViewById(R.id.add_data);
        usermanage_Button=(Button)findViewById(R.id.usermanage);

        usermanage_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_titleActivity.this,UserManagerActivity.class);
                startActivity(intent);
                finish();
            }
        });
        require_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Greenhouse> greenhouses=DataSupport.where("id=?","1").find(Greenhouse.class);
                for(Greenhouse greenhouse:greenhouses) {
                    greenhouseName_EditText.setText(greenhouse.getGreenhouseName());
                    greenhouseNumb_EditText.setText(greenhouse.getGreenhouseNumb());
                    greenhouseProvince_EdiText.setText(greenhouse.getProvinceName());
                    greenhouseCity_EditText.setText(greenhouse.getCityName());
                    greenhouseCounty_EditText.setText(greenhouse.getCountyName());
                    xiangxidizhi_EditText.setText(greenhouse.getXiangxiaddress());
                }
            }
        });

        delete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Greenhouse.class,"id=?","3");
            }
        });

        update_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Greenhouse greenhouse=new Greenhouse();
                greenhouse.setGreenhouseNumb("新1");
                greenhouse.updateAll("id=?","3");
            }
        });
        Button jianbiao=(Button)findViewById(R.id.jianbiao);
        jianbiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });


        addData_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Greenhouse greenhouse=new Greenhouse();
                String greenhouseName=greenhouseName_EditText.getText().toString();
                String greenhouseNumb=greenhouseNumb_EditText.getText().toString();
                String province=greenhouseProvince_EdiText.getText().toString();
                String city=greenhouseCity_EditText.getText().toString();
                String county=greenhouseCounty_EditText.getText().toString();
                String xiangxi=xiangxidizhi_EditText.getText().toString();

                greenhouse.setGreenhouseName(greenhouseName);
                greenhouse.setGreenhouseNumb(greenhouseNumb);
                greenhouse.setProvinceName(province);
                greenhouse.setCityName(city);
                greenhouse.setCountyName(county);
                greenhouse.setXiangxiaddress(xiangxi);
                greenhouse.save();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
     @Override
   public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
            case R.id.zhuce:
                Toast.makeText(this,"注册",Toast.LENGTH_SHORT).show();
                break;
            case R.id.aaa:
                Toast.makeText(this,"aaa",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
