package com.xql.constellation.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xql.basic.adapter.BaseAdapter;
import com.xql.basic.adapter.BaseHolder;
import com.xql.constellation.R;
import com.xql.constellation.bean.FortuneBean;
import com.xql.constellation.interfaces.IOnFortuneItemListener;

import java.util.List;

/**
 * @ClassName: FortuneAdapter
 * @Description: java类作用描述
 * @CreateDate: 2021/6/7 16:25
 * @UpdateUser: RyanLiu
 */

public class FortuneAdapter extends BaseAdapter {
    private List<FortuneBean> mFortuneBeans;
    private IOnFortuneItemListener mIOnFortuneItemListener;

    public FortuneAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        this.mFortuneBeans = datas;
    }

    public void setOnItemClickListener(IOnFortuneItemListener IOnFortuneItemListener) {
        mIOnFortuneItemListener = IOnFortuneItemListener;
    }

    @Override
    protected void onBindData(BaseHolder baseHolder, Object o, int postion) {
        ImageView constellation_img = baseHolder.getView(R.id.constellation_img);
        TextView constellation_date = baseHolder.getView(R.id.constellation_date);
        TextView constellation_name = baseHolder.getView(R.id.constellation_name);
        LinearLayout constellation_ll = baseHolder.getView(R.id.constellation_ll);

        constellation_img.setImageResource(mFortuneBeans.get(postion).getConstellation_img());
        constellation_date.setText(mFortuneBeans.get(postion).getConstellation_date());
        constellation_name.setText(mFortuneBeans.get(postion).getConstellation_name());
        constellation_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIOnFortuneItemListener.OnFunctionItemClick(postion,mFortuneBeans.get(postion).getConstellation_ID());
            }
        });
    }

}
