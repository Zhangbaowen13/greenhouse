package com.example.pengpeng;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.pengpeng.R.layout.activity_login;

public class GreenhouselistActivity extends AppCompatActivity {
private DrawerLayout mDrawerLayout;
    FragmentManager fm;
    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greenhouselist);
        //向fragment传值
        fm = getSupportFragmentManager();
        FragmentTransaction shiwu = fm.beginTransaction();

        String userID=getIntent().getStringExtra("user_id");
        Bundle bundle = new Bundle();
        bundle.putString("userID",userID);
        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(bundle);
        shiwu.replace(R.id.list_Framelayout, listFragment);
        shiwu.commit();
       /* Fragment fragment = fm.findFragmentById(R.id.list_fragment);
        Bundle bundle = new Bundle();
        String userID=getIntent().getStringExtra("user_id");
        bundle.putString(GreenhouselistActivity.Arguments, userID);
        ListManageFragment listManageFragment = new ListManageFragment();
        listManageFragment.setArguments(bundle);  //通过setArguments传值
        fm.beginTransaction().add(R.id.list_Framelayout, listManageFragment).commit();*/
//Toolbar导航栏
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.person);
        }
        //navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_weather:
                        String userID=getIntent().getStringExtra("user_id");
                        Intent intent3=new Intent(GreenhouselistActivity.this,AreaActivity.class);
                        intent3.putExtra("dizhi","GreenhouselistActivity");
                        intent3.putExtra("user_id",userID);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.nav_personal:
                        Intent intent=new Intent(GreenhouselistActivity.this,PersonalActivity.class);
                        String userID1=getIntent().getStringExtra("user_id");
                        intent.putExtra("user_id",userID1);
                        intent.putExtra("dizhi","GreenhouselistActivity");
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_greenhouse:
                        Intent intent5=new Intent(GreenhouselistActivity.this,GreenhouseActivity.class);
                        String userID2=getIntent().getStringExtra("user_id");
                        intent5.putExtra("user_id",userID2);
                        intent5.putExtra("dizhi","GreenhouselistActivity");
                        startActivity(intent5);
                        break;
                    case R.id.nav_shift:
                        Intent intent1=new Intent(GreenhouselistActivity.this,LoginActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_exit:
                        Intent intent2=new Intent(GreenhouselistActivity.this,LoginActivity.class);
                        startActivity(intent2);
                        break;
                    default:
                }
                return true;
            }
        });
    }

        public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar1,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent=new Intent(GreenhouselistActivity.this,LoginActivity.class);
        switch(item.getItemId()){
            case R.id.back:
                startActivity(intent);
                finish();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;

    }
    @SuppressLint("NewApi")
    public void click(View v)
    {
        String userID=getIntent().getStringExtra("user_id");
        Bundle bundle = new Bundle();
        bundle.putString("userID",userID);
        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(bundle);
        FragmentTransaction shiwu =fm.beginTransaction();
        shiwu.replace(R.id.list_Framelayout, listFragment);
        shiwu.commit();}
}
