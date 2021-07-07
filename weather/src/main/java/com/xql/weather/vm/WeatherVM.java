package com.xql.weather.vm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.TimeUtils;
import com.qweather.sdk.bean.IndicesBean;
import com.qweather.sdk.bean.WarningBean;
import com.qweather.sdk.bean.air.AirNowBean;
import com.qweather.sdk.bean.base.IndicesType;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.bean.sunmoon.MoonBean;
import com.qweather.sdk.bean.sunmoon.SunBean;
import com.qweather.sdk.bean.weather.WeatherHourlyBean;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.QWeather;
import com.xql.basic.viewmodel.BaseViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: WeatherVM
 * @Description: java类作用描述
 * @CreateDate: 2021/6/15 8:23
 * @UpdateUser: RyanLiu
 */

public class WeatherVM extends BaseViewModel {
    /**
     * GeoAPI网络请求
     */
    public MutableLiveData<GeoBean> getGeo(Context context, String location) {
        final MutableLiveData<GeoBean> liveData = new MutableLiveData<>();
        QWeather.getGeoCityLookup(context, location, new QWeather.OnResultGeoListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "GeoAPI: " + throwable.getMessage());
                liveData.postValue(new GeoBean());
            }

            @Override
            public void onSuccess(GeoBean geoBean) {
                liveData.postValue(geoBean);
            }
        });
        return liveData;
    }

    /**
     * 生活指数网络请求
     */
    public MutableLiveData<IndicesBean> getIndices1D(Context context, String location) {
        final MutableLiveData<IndicesBean> liveData = new MutableLiveData<>();
        QWeather.getIndices1D(context, location, Lang.ZH_HANS, Collections.singletonList(IndicesType.ALL), new QWeather.OnResultIndicesListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "生活指数: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(IndicesBean indicesBean) {
                liveData.postValue(indicesBean);
            }
        });
        return liveData;
    }

    /**
     * 实况天气网络请求
     */
    public MutableLiveData<WeatherNowBean> getWeatherNow(Context context, String location) {
        final MutableLiveData<WeatherNowBean> liveData = new MutableLiveData<>();
        QWeather.getWeatherNow(context, location, new QWeather.OnResultWeatherNowListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "实况天气: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(WeatherNowBean weatherNowBean) {
                liveData.postValue(weatherNowBean);
            }
        });
        return liveData;
    }

    /**
     * 日出日落网络请求
     */
    public MutableLiveData<SunBean> getSun(Context context, String location) {
        //获取当前时间
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        final MutableLiveData<SunBean> liveData = new MutableLiveData<>();
        QWeather.getSun(context, location, simpleDateFormat.format(new Date()), new QWeather.OnResultSunListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "日出日落: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(SunBean sunBean) {
                liveData.postValue(sunBean);
            }
        });
        return liveData;
    }

    /**
     * 月升月落月相网络请求
     */
    public MutableLiveData<MoonBean> getMoon(Context context, String location) {
        //获取当前时间
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        final MutableLiveData<MoonBean> liveData = new MutableLiveData<>();
        QWeather.getMoon(context, location, simpleDateFormat.format(new Date()), new QWeather.OnResultMoonListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "月升月落月相: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(MoonBean moonBean) {
                liveData.postValue(moonBean);
            }
        });

        return liveData;
    }

    /**
     * 空气质量网络请求
     */
    public MutableLiveData<AirNowBean> getAirNow(Context context, String location) {
        final MutableLiveData<AirNowBean> liveData = new MutableLiveData<>();
        QWeather.getAirNow(context, location, Lang.ZH_HANS, new QWeather.OnResultAirNowListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "空气质量: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(AirNowBean airNowBean) {
                liveData.postValue(airNowBean);
            }
        });
        return liveData;
    }

    /**
     * 灾害预警网络请求
     */
    public MutableLiveData<WarningBean> getWarning(Context context, String location) {
        final MutableLiveData<WarningBean> liveData = new MutableLiveData<>();
        QWeather.getWarning(context, location, new QWeather.OnResultWarningListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "灾害预警: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(WarningBean warningBean) {
                liveData.postValue(warningBean);
            }
        });
        return liveData;
    }

    /**
     * 24小时预报网络请求
     */
    public MutableLiveData<WeatherHourlyBean> getWeather24Hourly(Context context, String location) {
        final MutableLiveData<WeatherHourlyBean> liveData = new MutableLiveData<>();
        QWeather.getWeather24Hourly(context, location, new QWeather.OnResultWeatherHourlyListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "灾害预警: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(WeatherHourlyBean weatherHourlyBean) {
                liveData.postValue(weatherHourlyBean);
            }
        });

        return liveData;
    }

}
