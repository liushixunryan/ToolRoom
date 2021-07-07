package com.xql.weather;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.qweather.sdk.bean.IndicesBean;
import com.qweather.sdk.bean.WarningBean;
import com.qweather.sdk.bean.air.AirNowBean;
import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.bean.sunmoon.MoonBean;
import com.qweather.sdk.bean.sunmoon.SunBean;
import com.qweather.sdk.bean.weather.WeatherHourlyBean;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.xql.annotation.BindPath;
import com.xql.basic.activity.BaseActivity;
import com.xql.common.LineChartManager;
import com.xql.weather.adapter.LifeIndexAdapter;
import com.xql.weather.databinding.ActivityWeatherBinding;
import com.xql.weather.vm.WeatherVM;
import com.yanzhenjie.permission.runtime.Permission;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@BindPath(key = "weather/weather")
public class WeatherActivity extends BaseActivity<ActivityWeatherBinding, WeatherVM> {
    //生活指数需要的声明
    private LifeIndexAdapter lifeIndexAdapter;
    private List<IndicesBean.DailyBean> dailyBeans = new ArrayList<>();
    //定位需要的声明
    private AMapLocationClient mLocationClient = null;//定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数
    //省市名
    private String city;
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
        showLoading();
        /**
         * 高德地图定位服务
         */
        Positioningservice();
        /**
         * MpAndroidchart折线图
         */
        //        MpAndroidchart();
    }


    /**
     * 高德地图初始化配置
     */
    private void Positioningservice() {
        boolean granted = PermissionUtils.isGranted(Permission.ACCESS_COARSE_LOCATION, Permission.ACCESS_FINE_LOCATION);
        if (granted) {
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
        } else {
            ToastUtils.showLong("您没有授权将无法使用天气功能");
            hideLoading();
        }

    }

    //声明定位回调监听器
    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            String s = "纬度" + aMapLocation.getLatitude() + "/经度" + aMapLocation.getLongitude() + "/精度" + aMapLocation.getAccuracy() + "/地址描述" + aMapLocation.getAddress() + "/国家" + aMapLocation.getCountry() + "/省" + aMapLocation.getProvince() + "/城市" + aMapLocation.getCity() + "/城区" + aMapLocation.getDistrict() + "/街道" + aMapLocation.getStreet() + "/街道门牌号" + aMapLocation.getStreetNum();
            loction = aMapLocation.getDistrict();
            city = aMapLocation.getCity();
            GeoAPI();
        }
    };

    /**
     * GEOAPI获取城市id
     */
    private void GeoAPI() {
        Log.e(TAG, "GeoAPI: " + loction);
        mViewModel.getGeo(WeatherActivity.this, "大东区").observe(WeatherActivity.this, new Observer<GeoBean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(GeoBean geoBean) {
                mBinding.cityTitle.setText(city + loction);
                if (geoBean.getLocationBean() != null) {
                    Log.e(TAG, "GeoAPI: " + geoBean.getLocationBean().get(0).getId());
                    districtId = geoBean.getLocationBean().get(0).getId();
                    //实况天气
                    WeatherNow();
                    //24小时预报
                    Weather24Hourly();
                    //生活指数
                    Indices1D();
                    //日出日落
                    SunToday();
                    //月升月落月相
                    MoonToday();
                    //空气质量
                    AirNow();
                    //灾害预警
                    Warning();
                    hideLoading();
                } else {
                    hideLoading();
                }

            }
        });
    }

    /**
     * 获取24小时预报
     */
    private void Weather24Hourly() {
        mViewModel.getWeather24Hourly(WeatherActivity.this, districtId).observe(WeatherActivity.this, new Observer<WeatherHourlyBean>() {
            @Override
            public void onChanged(WeatherHourlyBean weatherHourlyBean) {
                String s = GsonUtils.toJson(weatherHourlyBean);
                Log.e(TAG, "24小时预报: " + s);

                new LineChartManager(mBinding.lineChart, weatherHourlyBean);

            }
        });
    }

    /**
     * 获取灾害预警
     */
    private void Warning() {
        mViewModel.getWarning(WeatherActivity.this, districtId).observe(WeatherActivity.this, new Observer<WarningBean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(WarningBean warningBean) {
                String s = GsonUtils.toJson(warningBean);
                Log.e(TAG, "灾害预警: " + s);
                if (!(warningBean.getWarningList().toString().equals("[]"))) {
                    mBinding.disasterEarlyWarningLayout.diasaterLl.setVisibility(View.VISIBLE);
                    mBinding.disasterEarlyWarningLayout.zwsjImg.setVisibility(View.GONE);
                    String PubTime = warningBean.getWarningList().get(0).getPubTime().substring(0, 10) + " " + warningBean.getWarningList().get(0).getPubTime().substring(11, 16);
                    String startTime = warningBean.getWarningList().get(0).getStartTime().substring(0, 10) + " " + warningBean.getWarningList().get(0).getPubTime().substring(11, 16);
                    String endTime = warningBean.getWarningList().get(0).getEndTime().substring(0, 10) + " " + warningBean.getWarningList().get(0).getPubTime().substring(11, 16);
                    mBinding.disasterEarlyWarningLayout.pubTimeTv.setText(PubTime);
                    mBinding.disasterEarlyWarningLayout.warnTitleTv.setText(warningBean.getWarningList().get(0).getTitle() + "");
                    mBinding.disasterEarlyWarningLayout.startTimeTv.setText(startTime);
                    mBinding.disasterEarlyWarningLayout.endTimeTv.setText(endTime);
                    mBinding.disasterEarlyWarningLayout.levelTv.setText(warningBean.getWarningList().get(0).getLevel() + "");
                    mBinding.disasterEarlyWarningLayout.typeNameTv.setText(warningBean.getWarningList().get(0).getTypeName() + "");
                    mBinding.disasterEarlyWarningLayout.warnTextTv.setText(warningBean.getWarningList().get(0).getText() + "");
                } else {
                    mBinding.disasterEarlyWarningLayout.diasaterLl.setVisibility(View.GONE);
                    mBinding.disasterEarlyWarningLayout.zwsjImg.setVisibility(View.VISIBLE);
//                    ToastUtils.showLong("灾害预警数据未刷新");
                }

            }
        });
    }

    /**
     * 获取空气质量
     */
    private void AirNow() {
        mViewModel.getAirNow(WeatherActivity.this, districtId).observe(WeatherActivity.this, new Observer<AirNowBean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(AirNowBean airNowBean) {
                String s = GsonUtils.toJson(airNowBean);
                Log.e(TAG, "空气质量: " + s);
                mBinding.airQualityLayout.aqiTv.setText(airNowBean.getNow().getAqi() + "");
                mBinding.airQualityLayout.primaryTv.setText(airNowBean.getNow().getPrimary() + "");
                mBinding.airQualityLayout.levelTv.setText(airNowBean.getNow().getLevel() + "");
                mBinding.airQualityLayout.categoryTv.setText(airNowBean.getNow().getCategory() + "");
                mBinding.airQualityLayout.pm10Tv.setText(airNowBean.getNow().getPm10() + "");
                mBinding.airQualityLayout.pm2p5Tv.setText(airNowBean.getNow().getPm2p5() + "");
                mBinding.airQualityLayout.no2Tv.setText(airNowBean.getNow().getNo2() + "");
                mBinding.airQualityLayout.so2Tv.setText(airNowBean.getNow().getSo2() + "");
                mBinding.airQualityLayout.coTv.setText(airNowBean.getNow().getCo() + "");
                mBinding.airQualityLayout.o3Tv.setText(airNowBean.getNow().getO3() + "");
            }
        });
    }

    /**
     * 获取月升月落月相
     */
    private void MoonToday() {
        mViewModel.getMoon(WeatherActivity.this, districtId).observe(WeatherActivity.this, new Observer<MoonBean>() {
            @Override
            public void onChanged(MoonBean moonBean) {
                String s = GsonUtils.toJson(moonBean);
                Log.e(TAG, "月升月落月相: " + s);
                String Moonrise = moonBean.getMoonrise().substring(11, 16);
                String Moonset = moonBean.getMoonset().substring(11, 16);
                mBinding.sunandmoonLayout.moonriseTimeTv.setText(Moonrise);
                mBinding.sunandmoonLayout.moonsetTimeTv.setText(Moonset);
                mBinding.sunandmoonLayout.phaseMoonTv.setText(moonBean.getMoonPhaseBeanList().get(0).getName());
            }
        });
    }

    /**
     * 获取日出日落时间
     */
    private void SunToday() {
        mViewModel.getSun(WeatherActivity.this, districtId).observe(WeatherActivity.this, new Observer<SunBean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(SunBean sunBean) {
                String s = GsonUtils.toJson(sunBean);
                Log.e(TAG, "日出日落: " + s);
                String Sunrise = sunBean.getSunrise().substring(11, 16);
                String Sunset = sunBean.getSunset().substring(11, 16);
                mBinding.sunandmoonLayout.sunriseTimeTv.setText(Sunrise);
                mBinding.sunandmoonLayout.sunsetTimeTv.setText(Sunset);
            }
        });
    }

    /**
     * 获取实况天气
     */
    private void WeatherNow() {
        mViewModel.getWeatherNow(WeatherActivity.this, districtId).observe(WeatherActivity.this, new Observer<WeatherNowBean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(WeatherNowBean weatherNowBean) {
                String s = GsonUtils.toJson(weatherNowBean);
                Log.e(TAG, "实况天气: " + s);
                mBinding.airTemperature.temperatureTv.setText(weatherNowBean.getNow().getTemp() + "℃");
                mBinding.airTemperature.weatherConditionsTv.setText("天气: " + weatherNowBean.getNow().getText());
                mBinding.airTemperature.windDirectionTv.setText("风向: " + weatherNowBean.getNow().getWindDir());
                mBinding.airTemperature.windPowerTv.setText("风力: " + weatherNowBean.getNow().getWindScale() + "级");
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
                Log.e(TAG, "生活指数: " + s);
                dailyBeans = indicesBean.getDailyList();

                lifeIndexAdapter = new LifeIndexAdapter(WeatherActivity.this,dailyBeans,R.layout.item_life_index);
                mBinding.lifeIndexLayout.lifeRv.setLayoutManager(new LinearLayoutManager(WeatherActivity.this));
                mBinding.lifeIndexLayout.lifeRv.setAdapter(lifeIndexAdapter);
            }
        });
    }


    @Override
    protected void initData() {
        mBinding.lifeIndexLayout.lifeRv.setNestedScrollingEnabled( false); //禁止recyclerView嵌套滑动
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
}