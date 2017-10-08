package com.example.pengpeng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.pengpeng.db.Shebei;

public class ShebeiManagerActivity extends AppCompatActivity {
private EditText ShebeiName_EditText;
    private EditText Greenhouseid_EditText;
    private CheckBox isopen_Checkbox;
    private Button addShebei_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shebei_manager);

        ShebeiName_EditText=(EditText)findViewById(R.id.shebeiName_et);
        Greenhouseid_EditText=(EditText)findViewById(R.id.greenhouseId_et);
        isopen_Checkbox=(CheckBox)findViewById(R.id.isopen_cb);
        addShebei_Button=(Button)findViewById(R.id.add_shebei);

        addShebei_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shebei shebei=new Shebei();

                String shebeiName=ShebeiName_EditText.getText().toString();
                String greenhouseId=Greenhouseid_EditText.getText().toString();
                boolean isOpen=isopen_Checkbox.isChecked();
                String IsOpen=String.valueOf(isOpen);
                shebei.setShebeiName(shebeiName);
                shebei.setGreenhouseId(greenhouseId);
                shebei.setIsopen(IsOpen);
                shebei.save();
            }
        });

    }
}
