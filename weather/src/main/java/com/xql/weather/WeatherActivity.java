package com.xql.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.Gson;
import com.qweather.sdk.bean.base.Code;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Unit;
import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.HeConfig;
import com.qweather.sdk.view.QWeather;
import com.xql.annotation.BindPath;
import com.xql.basic.activity.BaseActivity;
import com.xql.weather.databinding.ActivityWeatherBinding;
import com.xql.weather.vm.WeatherVM;

@BindPath(key = "weather/weather")
public class WeatherActivity extends BaseActivity<ActivityWeatherBinding, WeatherVM>{

    @Override
    protected int layoutId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void initView() {
       mViewModel.getGeo(WeatherActivity.this,"沈阳").observe(this, new Observer<GeoBean>() {
           @Override
           public void onChanged(GeoBean geoBean) {
               Log.e(TAG, "onChanged: " + geoBean.getLocationBean().get(0).getId() );
           }
       });
    }

    @Override
    protected void initData() {

    }
}