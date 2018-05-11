package com.example.pengpeng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.pengpeng.db.DataNow;
import com.example.pengpeng.db.Datatype;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DataManagerActivity extends AppCompatActivity {
private EditText chuanganqiId_EditText;
    private EditText dataNaeme_EditText;
    private EditText shuju_EditText;
    private  EditText greenhouseId_EditText;
    private CheckBox isNew_Checkbox;
    private Button addType_Button;
    private Button addData_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_manager);

        chuanganqiId_EditText=(EditText)findViewById(R.id.chuanganqiid_et);
        dataNaeme_EditText=(EditText)findViewById(R.id.dataname_et);
        shuju_EditText=(EditText)findViewById(R.id.shuju_et);
        greenhouseId_EditText=(EditText)findViewById(R.id.greenhouseid_et);
        isNew_Checkbox=(CheckBox)findViewById(R.id.isnew_cb);
        addData_Button=(Button)findViewById(R.id.add_data);
        addType_Button=(Button)findViewById(R.id.add_type);
        addType_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Datatype datatype=new Datatype();
                String dataName=dataNaeme_EditText.getText().toString();
                datatype.setDataname(dataName);
                datatype.save();
            }
        });
        addData_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataNow dataNow=new DataNow();
                String chuanganqiId=chuanganqiId_EditText.getText().toString();
                String dataName=dataNaeme_EditText.getText().toString();
                String shuju=shuju_EditText.getText().toString();
                String greenhouseId=greenhouseId_EditText.getText().toString();
                boolean isnew=isNew_Checkbox.isChecked();
                String isNew=String.valueOf(isnew);
                SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date curDate =  new Date(System.currentTimeMillis());//获取当前时间
                String str  = dFormat.format(curDate);

                dataNow.setChuanganqiId(chuanganqiId);
                dataNow.setDataname(dataName);
                dataNow.setShuju(shuju);
                dataNow.setUpdatetime(str);
                dataNow.setGreenhouseId(greenhouseId);
                dataNow.setPicture(R.mipmap.wenshi);
                dataNow.setIsnew(isNew);
                dataNow.save();
            }
        });
    }
}
