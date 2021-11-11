package com.xql.news.interfaces;

/**
 * @ClassName: IOnNewsListener
 * @Description: 新闻类接口
 * @CreateDate: 2021/11/8 14:24
 * @UpdateUser: RyanLiu
 */

public interface IOnNewsListener {
    void OnNewsItemClick(int position, String ID);
}
