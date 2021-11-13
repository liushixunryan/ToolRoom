package com.xql.news.viewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xql.basic.fragment.BaseFragment;
import com.xql.news.R;
import com.xql.news.adapter.NewListAdapter;
import com.xql.news.bean.TJBean;
import com.xql.news.databinding.ViewpagerNewslistLayoutBinding;
import com.xql.news.interfaces.IOnNewsListener;
import com.xql.news.vm.NewsInformationVM;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TJViewPager
 * @Description: 新闻列表adapter
 * @CreateDate: 2021/11/4 16:12
 * @UpdateUser: RyanLiu
 */

public class TJViewPager extends BaseFragment<ViewpagerNewslistLayoutBinding, NewsInformationVM> {
    private NewListAdapter newListAdapter;
    private List<TJBean.ResultBean.DataBean> tjBeans;
    //获取新闻列表类型
    private String type = "top";
    //获取哪一页
    private int page = 1;

    @Override
    protected int layoutId() {
        return R.layout.viewpager_newslist_layout;
    }

    @Override
    protected void initView(ViewpagerNewslistLayoutBinding bindview) {
        //实例化
        tjBeans = new ArrayList<>();
        getTJData(type, page);

    }

    /**
     *获取推荐列表的数据接口
     */
    private void getTJData(String type, int pages) {
        mViewModel.getNewsList("top", pages).observe(this, new Observer<TJBean>() {
            @Override
            public void onChanged(TJBean tjBean) {
                String s = GsonUtils.toJson(tjBean);
                Log.e(TAG, "推薦: " + s);
                //赋值给新闻列表
                tjBeans.addAll(tjBean.getResult().getData());
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
        //recyclerview 设置 布局
        mBinding.newslist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.newslist.setAdapter(newListAdapter);

        //点击事件
        onclick();
        //上拉加载
        uploading();
        //下拉刷新
        downRefresh();


    }

    /**
     * 点击事件
     */
    private void onclick() {
        //adapter点击事件
        newListAdapter.setiOnNewListener(new IOnNewsListener() {
            @Override
            public void OnNewsItemClick(int position, String ID) {
                Log.e(TAG, "OnNewsItemClick: key:" + ID);
            }
        });
    }

    /**
     * 下拉刷新事件
     */
    private void downRefresh() {
        mBinding.swiperLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                //每次清空一次數據
                tjBeans.clear();
                getTJData(type, page);
            }
        });
    }

    /**
     * 上拉加载
     */
    private void uploading() {
        mBinding.newslist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    Log.e(TAG, "onScrollStateChanged: " + lastVisibleItem + totalItemCount);
                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        //滚动到底部加载更多功能的代码
                        page = page + 1;
                        if (page > 11) {
                            ToastUtils.showLong("没有更多数据了");
                        } else {
                            getTJData(type, page);
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dy > 0) {
                    //大于0表示正在向下滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0表示停止或向上滚动
                    isSlidingToLast = false;
                }
            }
        });
    }
}
