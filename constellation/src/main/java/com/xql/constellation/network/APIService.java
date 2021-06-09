package com.xql.constellation.network;

import com.xql.constellation.bean.MonthBean;
import com.xql.constellation.bean.TodayBean;
import com.xql.constellation.bean.TomorrowBean;
import com.xql.constellation.bean.WeekBean;
import com.xql.constellation.bean.YearBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @ClassName: APIService
 * @Description: 网络接口
 * @CreateDate: 2021/6/8 13:36
 * @UpdateUser: RyanLiu
 */

public interface APIService {
    //星座运势key
    String constellation_key = "243cfbed804a8bd6904fb059871b2a41";

    /**
     * 今天
     * @param consName 星座名
     * @return
     */
    @GET("getAll?type=today&key="+ constellation_key)
    Observable<TodayBean> getToday(@Query("consName") String consName);

    /**
     *  明天
     * @param consName 星座名
     * @return
     */
    @GET("getAll?type=tomorrow&key="+ constellation_key)
    Observable<TomorrowBean> getTomorrow(@Query("consName") String consName);

    /**
     *  本周
     * @param consName 星座名
     * @return
     */
    @GET("getAll?type=week&key="+ constellation_key)
    Observable<WeekBean> getWeek(@Query("consName") String consName);

    /**
     *  本月
     * @param consName 星座名
     * @return
     */
    @GET("getAll?type=month&key="+ constellation_key)
    Observable<MonthBean> getMonth(@Query("consName") String consName);

    /**
     *  今年
     * @param consName 星座名
     * @return
     */
    @GET("getAll?type=year&key="+ constellation_key)
    Observable<YearBean> getYear(@Query("consName") String consName);
}
