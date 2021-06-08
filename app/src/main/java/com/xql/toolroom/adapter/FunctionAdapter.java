package com.xql.toolroom.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xql.basic.adapter.BaseAdapter;
import com.xql.basic.adapter.BaseHolder;
import com.xql.toolroom.R;
import com.xql.toolroom.bean.FunctionBean;
import com.xql.toolroom.interfaces.IOnFunctionItemListener;

import java.util.List;

/**
 * @ClassName: FunctionAdapter
 * @Description: 功能类adapter
 * @CreateDate: 2021/6/7 9:30
 * @UpdateUser: RyanLiu
 */

public class FunctionAdapter extends BaseAdapter {
    private List<FunctionBean> mFunctionBeans;
    private IOnFunctionItemListener mIOnFunctionItemListener;


    public FunctionAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        this.mFunctionBeans = datas;
    }

    public void setOnItemClickListener(IOnFunctionItemListener IOnFunctionItemListener) {
        mIOnFunctionItemListener = IOnFunctionItemListener;
    }

    @Override
    protected void onBindData(BaseHolder baseHolder, Object o, int postion) {
        ImageView function_img = baseHolder.getView(R.id.function_img);
        TextView function_tv = baseHolder.getView(R.id.function_tv);
        LinearLayout function_ll = baseHolder.getView(R.id.function_ll);
        function_img.setImageResource(mFunctionBeans.get(postion).getFunction_icon());
        function_tv.setText(mFunctionBeans.get(postion).getFunction_name());
        function_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIOnFunctionItemListener.OnFunctionItemClick(postion,mFunctionBeans.get(postion).getFunction_ID());
            }
        });
    }

}
