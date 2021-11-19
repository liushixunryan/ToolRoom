package com.xql.news.adapter;

import android.content.Context;
import android.view.View;

import com.xql.basic.adapter.BaseAdapter;
import com.xql.basic.adapter.BaseHolder;
import com.xql.news.R;
import com.xql.news.bean.NewsListBean;
import com.xql.news.interfaces.IOnNewsListener;

import java.util.List;

/**
 * @ClassName: NewListAdapter
 * @Description: 新闻列表adapter
 * @CreateDate: 2021/11/4 16:20
 * @UpdateUser: RyanLiu
 */

public class NewListAdapter extends BaseAdapter<NewsListBean.ResultBean.DataBean> {
    //实例化点击新闻详情接口
    private IOnNewsListener onNewsListener;


    public void setiOnNewListener(IOnNewsListener onNewListener){
        onNewsListener = onNewListener;
    }


    public NewListAdapter(Context context, List<NewsListBean.ResultBean.DataBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }


    @Override
    protected void onBindData(BaseHolder baseHolder, NewsListBean.ResultBean.DataBean dataBean, int postion) {
        baseHolder.setText(R.id.newsTitle,dataBean.getTitle());
        baseHolder.setText(R.id.newsSource,dataBean.getAuthor_name());
        baseHolder.setText(R.id.newsTime,dataBean.getDate());
        baseHolder.setImageView(R.id.newsImg,dataBean.getThumbnail_pic_s());
        baseHolder.setOnclickListioner(R.id.newsDetail, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNewsListener.OnNewsItemClick(postion,dataBean.getUniquekey());
            }
        });
    }
}
