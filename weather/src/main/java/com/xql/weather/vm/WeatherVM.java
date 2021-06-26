package com.xql.weather.vm;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.qweather.sdk.bean.IndicesBean;
import com.qweather.sdk.bean.base.IndicesType;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.view.QWeather;
import com.xql.basic.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.Collections;
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
                Log.e(TAG, "onError: " + throwable.getMessage());
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
        QWeather.getIndices1D(context, location, Lang.ZH_HANS , Collections.singletonList(IndicesType.ALL), new QWeather.OnResultIndicesListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "onError: " + throwable.getMessage() );
            }

            @Override
            public void onSuccess(IndicesBean indicesBean) {
                liveData.postValue(indicesBean);
            }
        });
        return liveData;
    }


}
