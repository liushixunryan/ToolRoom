package com.xql.news;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;

import com.blankj.utilcode.util.GsonUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xql.annotation.BindPath;
import com.xql.basic.activity.BaseActivity;
import com.xql.common.MyFragmentPagerAdapter;
import com.xql.news.bean.TJBean;
import com.xql.news.databinding.ActivityNewsInformationBinding;
import com.xql.news.viewpager.TJViewPager;
import com.xql.news.vm.NewsInformationVM;

import java.util.ArrayList;
import java.util.List;

@BindPath(key = "news/news")
public class NewsInformationActivity extends BaseActivity<ActivityNewsInformationBinding, NewsInformationVM> {

    private String[] title = new String[]{"推荐", "国内", "国际", "娱乐", "体育","军事","科技","财经","时尚","游戏","汽车","健康"};
    private List<Fragment> fragments;
    private TabLayoutMediator mediator;

    @Override
    protected int layoutId() {
        return R.layout.activity_news_information;
    }

    @Override
    protected void initView() {
        mBinding.titleTv.setText("运势详情");
        mBinding.backImg.setVisibility(View.VISIBLE);

        fragments = new ArrayList<>();
        fragments.add(new TJViewPager());
//        fragments.add(new TomorrowFragment());
//        fragments.add(new WeekFragment());
//        fragments.add(new MonthFragment());
//        fragments.add(new YearFragment());

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(NewsInformationActivity.this, fragments);
        //设置的预加载数量      ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT 禁用预加载
        mBinding.mViewpager.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        mBinding.mViewpager.setAdapter(adapter);
        mediator = new TabLayoutMediator(mBinding.mytab, mBinding.mViewpager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(title[position]);
            }
        });
        mediator.attach();
    }

    @Override
    protected void initData() {
        mViewModel.getNewsList("top", 1).observe(this, new Observer<TJBean>() {
            @Override
            public void onChanged(TJBean tjBean) {
                String s = GsonUtils.toJson(tjBean);
                Log.e(TAG, "推薦: " + s);
            }
        });
    }
}