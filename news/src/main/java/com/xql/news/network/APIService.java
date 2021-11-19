package com.xql.news.network;

import com.xql.news.bean.NewsListBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @ClassName: APIService
 * @Description: 接口参数
 * @CreateDate: 2021/6/7 9:17
 * @UpdateUser: RyanLiu
 */

public interface APIService {
    //聚合新闻头条Key
    String key = "62c6377834d8ee696c3bbce838da37b6";

    //获取新闻类别集合
    @POST("index?type=top&key=" + key)
    Observable<NewsListBean> getNewsList(@Query("type") String type, @Query("page") int page, @Query("page_size") int page_size, @Query("is_filter") int is_filter);

}