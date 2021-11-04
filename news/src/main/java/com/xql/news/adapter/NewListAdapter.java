package com.xql.news.adapter;

import android.content.Context;

import com.xql.basic.adapter.BaseAdapter;
import com.xql.basic.adapter.BaseHolder;
import com.xql.news.bean.TJBean;

import java.util.List;

/**
 * @ClassName: NewListAdapter
 * @Description: java类作用描述
 * @CreateDate: 2021/11/4 16:20
 * @UpdateUser: RyanLiu
 */

public class NewListAdapter extends BaseAdapter<TJBean> {
    public NewListAdapter(Context context, List<TJBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void onBindData(BaseHolder baseHolder, TJBean tjBean, int postion) {

    }
}
