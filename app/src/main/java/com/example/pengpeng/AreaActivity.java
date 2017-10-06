package com.example.pengpeng;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pengpeng.gson.Weather;
import com.example.pengpeng.util.Utility;

public class AreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString=prefs.getString("weather",null);
      if(weatherString!=null){
            Weather weather= Utility.handleWeatherResponse(weatherString);
            String huancunId=weather.basic.weatherId;
          String userID=getIntent().getStringExtra("user_id");
          String dizhi=getIntent().getStringExtra("dizhi");
            Intent intent=new Intent(this,WeatherActivity.class);
            intent.putExtra("dizhi",dizhi);
            intent.putExtra("weather_id",huancunId);
            intent.putExtra("user_id",userID);
            startActivity(intent);
            finish();
        }
    }
}
