package com.xql.weather.vm;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.view.QWeather;
import com.xql.basic.viewmodel.BaseViewModel;

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
                Log.e(TAG, "onError: " + throwable.getMessage() );
            }

            @Override
            public void onSuccess(GeoBean geoBean) {
                liveData.postValue(geoBean);
            }
        });
        return liveData;
    }
}
