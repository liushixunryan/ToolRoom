package com.xql.news.viewpager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xql.arouter.ARouter;
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
 * @ClassName: CJViewPager
 * @Description: 新闻财经页Viewpager
 * @CreateDate: 2021/11/24 9:49
 * @UpdateUser: RyanLiu
 */

public class CJViewPager extends BaseFragment<ViewpagerNewslistLayoutBinding, NewsInformationVM>{
    private NewListAdapter newListAdapter;
    private List<NewsListBean.ResultBean.DataBean> dataBeans;
    //获取新闻列表类型
    private String type = "caijing";
    //获取当前是哪一页
    private int page = 1;

    @Override
    protected int layoutId() {
        return R.layout.viewpager_newslist_layout;
    }

    @Override
    protected void initView(ViewpagerNewslistLayoutBinding bindview) {
        //实例化
        dataBeans = new ArrayList<>();
        getData(type, page);
    }

    /**
     * 国外页新闻列表
     *
     * @param type
     * @param pages
     */
    private void getData(String type, int pages) {
        mViewModel.getNewsList(type, pages).observe(this, new Observer<NewsListBean>() {
            @Override
            public void onChanged(NewsListBean newsListBean) {
                String s = GsonUtils.toJson(newsListBean);
                Log.e(TAG, "财经: " + s);
                //复制给新新闻列表
                dataBeans.addAll(newsListBean.getResult().getData());
                newListAdapter.notifyDataSetChanged();
                if (mBinding.swiperLayout.isRefreshing()) {
                    mBinding.swiperLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    protected void initData(Context context) {
        newListAdapter = new NewListAdapter(getActivity(), dataBeans, R.layout.item_newslist);
        //去掉上拉刷新和下拉加载的阴影
        mBinding.newslist.setOverScrollMode(View.OVER_SCROLL_NEVER);
        //recyclerview 设置布局
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
     * 下拉刷新
     */
    private void downRefresh() {
        mBinding.swiperLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                //每次一清空一次数据
                dataBeans.clear();
                getData(type, page);
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
                //当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPostition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    Log.e(TAG, "onScrollStateChanged: " + lastVisibleItem + totalItemCount);
                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        //滚动到底部加载更多功能的代码
                        page = page + 1;
                        if (page > 50) {
                            ToastUtils.showLong("没有更多数据了");
                        } else {
                            getData(type, page);
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向, dy用来判断纵向滑动方向
                if (dy > 0) {
                    //大于0表示向下滑动
                    isSlidingToLast = true;
                } else {
                    //小于0表示停止或向上滚动
                    isSlidingToLast = false;
                }
            }
        });
    }

    /**
     * 点击事件
     */
    private void onclick() {
        newListAdapter.setiOnNewListener(new IOnNewsListener() {
            @Override
            public void OnNewsItemClick(int position, String ID) {
                Log.e(TAG, "OnNewsItemClick: " + ID);
                Bundle bundle = new Bundle();
                bundle.putString("uniquekey", ID);
                ARouter.getInstance().jumpActivity("newsdetail/newsdetail", bundle);

            }
        });
    }
}
