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

public class LifeIndexAdapter extends BaseAdapter {
    private List<IndicesBean.DailyBean> dailyBeans;
    public LifeIndexAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        this.context = context;
        this.dailyBeans = datas;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindData(BaseHolder baseHolder, Object o, int postion) {
        TextView life_name = baseHolder.getView(R.id.life_name);
        TextView life_category = baseHolder.getView(R.id.life_category);
        TextView life_text = baseHolder.getView(R.id.life_text);

        life_name.setText(dailyBeans.get(postion).getName() + "");
        life_category.setText(dailyBeans.get(postion).getCategory() + "");
        life_text.setText(dailyBeans.get(postion).getText() + "");
    }
}
