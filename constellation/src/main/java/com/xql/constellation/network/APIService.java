package com.xql.constellation.network;

import com.xql.constellation.bean.TodayBean;
import com.xql.constellation.bean.TomorrowBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @ClassName: APIService
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 13:36
 * @UpdateUser: RyanLiu
 */

public interface APIService {
    //星座运势key
    String constellation_key = "243cfbed804a8bd6904fb059871b2a41";

    /**
     *
     * @param consName 星座名
     * @return
     */
    @GET("getAll?type=today&key="+ constellation_key)
    Observable<TodayBean> getToday(@Query("consName") String consName);

    /**
     *
     * @param consName 星座名
     * @return
     */
    @GET("getAll?type=tomorrow&key="+ constellation_key)
    Observable<TomorrowBean> getTomorrow(@Query("consName") String consName);
}
