package com.xql.weather.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.qweather.sdk.bean.IndicesBean;
import com.xql.basic.adapter.BaseAdapter;
import com.xql.basic.adapter.BaseHolder;
import com.xql.weather.R;

import java.util.List;

/**
 * @ClassName: LifeIndexAdapter
 * @Description: 生活指数adapter
 * @CreateDate: 2021/7/5 8:21
 * @UpdateUser: RyanLiu
 */

public class LifeIndexAdapter extends BaseAdapter<IndicesBean.DailyBean> {
    public LifeIndexAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void onBindData(BaseHolder baseHolder, IndicesBean.DailyBean dailyBean, int postion) {
        baseHolder.setText(R.id.life_name, dailyBean.getName() + "");
        baseHolder.setText(R.id.life_category, dailyBean.getCategory() + "");
        baseHolder.setText(R.id.life_text, dailyBean.getText() + "");
    }
}
