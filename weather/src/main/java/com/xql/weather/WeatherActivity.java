package com.xql.weather;

import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.GsonUtils;
import com.xql.annotation.BindPath;
import com.xql.basic.activity.BaseActivity;
import com.xql.weather.databinding.ActivityWeatherBinding;
import com.xql.weather.vm.WeatherVM;

import java.text.SimpleDateFormat;
import java.util.Date;

@BindPath(key = "weather/weather")
public class WeatherActivity extends BaseActivity<ActivityWeatherBinding, WeatherVM> {
    //定位需要的声明
    private AMapLocationClient mLocationClient = null;//定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数
    //声明定位回调监听器
    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            String s = GsonUtils.toJson(aMapLocation);
            Log.e(TAG, "onChanged: " + s);
        }
    };

    @Override
    protected int layoutId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void initView() {
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
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
//           mViewModel.getGeo(WeatherActivity.this,"沈阳").observe(this, new Observer<GeoBean>() {
//               @Override
//               public void onChanged(GeoBean geoBean) {
//                   Log.e(TAG, "onChanged: " + geoBean.getLocationBean().get(0).getId() );
//               }
//           });
//
//            mViewModel.getIndices1D(WeatherActivity.this,"101070101").observe(this, new Observer<IndicesBean>() {
//                @Override
//                public void onChanged(IndicesBean indicesBean) {
//                    String s = GsonUtils.toJson(indicesBean);
//                    Log.e(TAG, "onChanged: " + s);
//                }
//            });


@Override protected void initData(){

        }

        }