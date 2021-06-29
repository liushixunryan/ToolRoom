package com.xql.weather;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.Observer;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.GsonUtils;
import com.qweather.sdk.bean.IndicesBean;
import com.qweather.sdk.bean.geo.GeoBean;
import com.xql.annotation.BindPath;
import com.xql.basic.activity.BaseActivity;
import com.xql.weather.databinding.ActivityWeatherBinding;
import com.xql.weather.vm.WeatherVM;

@BindPath(key = "weather/weather")
public class WeatherActivity extends BaseActivity<ActivityWeatherBinding, WeatherVM> {
    //定位需要的声明
    private AMapLocationClient mLocationClient = null;//定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数
    //城市名
    private String loction;
    //和风天气城市id
    private String districtId;

    @Override
    protected int layoutId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void initView() {

        /**
         * 高德地图定位服务
         */
        Positioningservice();
    }

    private void Positioningservice() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(60000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //声明定位回调监听器
    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            String s = "纬度" + aMapLocation.getLatitude() + "/经度" + aMapLocation.getLongitude() + "/精度" + aMapLocation.getAccuracy() + "/地址描述" + aMapLocation.getAddress() + "/国家" + aMapLocation.getCountry() + "/省" + aMapLocation.getProvince() + "/城市" + aMapLocation.getCity() + "/城区" + aMapLocation.getDistrict() + "/街道" + aMapLocation.getStreet() + "/街道门牌号" + aMapLocation.getStreetNum();
            loction = aMapLocation.getDistrict();
            mBinding.cityTitle.setText(aMapLocation.getCity() + aMapLocation.getDistrict());
            GeoAPI();
        }
    };

    /**
     * GEOAPI获取城市id
     */
    private void GeoAPI() {
        mViewModel.getGeo(WeatherActivity.this, loction).observe(WeatherActivity.this, new Observer<GeoBean>() {
            @Override
            public void onChanged(GeoBean geoBean) {
                Log.e(TAG, "onChanged: " + geoBean.getLocationBean().get(0).getId());
                districtId = geoBean.getLocationBean().get(0).getId();
                Indices1D();

            }
        });
    }

    /**
     * 获取当天生活指数
     */
    private void Indices1D() {
        mViewModel.getIndices1D(WeatherActivity.this, districtId).observe(WeatherActivity.this, new Observer<IndicesBean>() {
            @Override
            public void onChanged(IndicesBean indicesBean) {
                String s = GsonUtils.toJson(indicesBean);
                Log.e(TAG, "onChanged: " + s);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
}