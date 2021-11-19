package com.xql.news.viewpager;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.GsonUtils;
import com.xql.basic.fragment.BaseFragment;
import com.xql.news.R;
import com.xql.news.adapter.NewListAdapter;
import com.xql.news.bean.NewsListBean;
import com.xql.news.databinding.ViewpagerNewslistLayoutBinding;
import com.xql.news.interfaces.IOnNewsListener;
import com.xql.news.vm.NewsInformationVM;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TJViewPager
 * @Description: 新闻推荐页Viewpager
 * @CreateDate: 2021/11/4 16:12
 * @UpdateUser: RyanLiu
 */

public class TJViewPager extends BaseFragment<ViewpagerNewslistLayoutBinding, NewsInformationVM> {
    private NewListAdapter newListAdapter;
    private List<NewsListBean.ResultBean.DataBean> tjBeans;

    @Override
    protected int layoutId() {
        return R.layout.viewpager_newslist_layout;
    }

    @Override
    protected void initView(ViewpagerNewslistLayoutBinding bindview) {
        //实例化
        tjBeans = new ArrayList<>();
        getTJData();


    }

    //获取推荐列表的数据接口
    private void getTJData() {
        mViewModel.getNewsList("top", 1).observe(this, new Observer<NewsListBean>() {
            @Override
            public void onChanged(NewsListBean newsListBean) {
                String s = GsonUtils.toJson(newsListBean);
                Log.e(TAG, "推薦: " + s);
                //赋值给新闻列表
                tjBeans.addAll(newsListBean.getResult().getData());
                newListAdapter.notifyDataSetChanged();
                if (mBinding.swiperLayout.isRefreshing()) {
                    mBinding.swiperLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    protected void initData(Context context) {
        newListAdapter = new NewListAdapter(getActivity(), tjBeans, R.layout.item_newslist);
        //去掉上拉刷新和下拉加载的阴影
        mBinding.newslist.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mBinding.newslist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.newslist.setAdapter(newListAdapter);

        newListAdapter.setiOnNewListener(new IOnNewsListener() {
            @Override
            public void OnNewsItemClick(int position, String ID) {
                Log.e(TAG, "OnNewsItemClick: key:" + ID);
            }
        });

        mBinding.swiperLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //每次清空一次數據
                tjBeans.clear();
                getTJData();
            }
        });
    }
}
