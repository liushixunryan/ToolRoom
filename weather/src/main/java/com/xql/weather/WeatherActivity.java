package com.xql.weather;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.GsonUtils;
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
import com.xql.common.ChartManager;
import com.xql.weather.adapter.LifeIndexAdapter;
import com.xql.weather.databinding.ActivityWeatherBinding;
import com.xql.weather.vm.WeatherVM;

import java.util.ArrayList;
import java.util.List;

@BindPath(key = "weather/weather")
public class WeatherActivity extends BaseActivity<ActivityWeatherBinding, WeatherVM> {
    //生活指数需要的声明
    private LifeIndexAdapter lifeIndexAdapter;
    private List<IndicesBean.DailyBean> dailyBeans = new ArrayList<>();
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
        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                loction = aMapLocation.getDistrict();
                city = aMapLocation.getCity();
                GeoAPI();
            }
        });
    }

    @Override
    protected void initData() {
        mBinding.lifeIndexLayout.lifeRv.setNestedScrollingEnabled(false); //禁止recyclerView嵌套滑动
    }

    /**
     * GEOAPI获取城市id
     */
    private void GeoAPI() {
        Log.e(TAG, "GeoAPI: " + loction);
        mViewModel.getGeo(WeatherActivity.this, loction).observe(WeatherActivity.this, new Observer<GeoBean>() {
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

                //折线图
                new ChartManager().LineChartManager(mBinding.lineChart, weatherHourlyBean);
                //柱状图
                //                new ChartManager().BarChartManager(mBinding.barChart, weatherHourlyBean);

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

                lifeIndexAdapter = new LifeIndexAdapter(WeatherActivity.this, dailyBeans, R.layout.item_life_index);
                mBinding.lifeIndexLayout.lifeRv.setLayoutManager(new LinearLayoutManager(WeatherActivity.this));
                mBinding.lifeIndexLayout.lifeRv.setAdapter(lifeIndexAdapter);
            }
        });
    }
}