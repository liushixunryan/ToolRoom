package com.xql.constellation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xql.annotation.BindPath;
import com.xql.basic.activity.BaseActivity;
import com.xql.common.MyFragmentPagerAdapter;
import com.xql.constellation.databinding.ActivityFortuneDetailsBinding;
import com.xql.constellation.viewpager.MonthFragment;
import com.xql.constellation.viewpager.TodayFragment;
import com.xql.constellation.viewpager.TomorrowFragment;
import com.xql.constellation.viewpager.WeekFragment;
import com.xql.constellation.viewpager.YearFragment;
import com.xql.constellation.vm.FortuneDetailsVM;

import java.util.ArrayList;
import java.util.List;

@BindPath(key = "fortunedetails/fortunedetails")
public class FortuneDetailsActivity extends BaseActivity<ActivityFortuneDetailsBinding, FortuneDetailsVM> {
    private String [] title = new String[] {"今日","明日","本周","本月","今年"};
    private List<Fragment> fragments;
    private TabLayoutMediator mediator;

    @Override
    protected int layoutId() {
        return R.layout.activity_fortune_details;
    }

    @Override
    protected void initView() {
        fragments = new ArrayList<>();
        fragments.add(new TodayFragment());
        fragments.add(new TomorrowFragment());
        fragments.add(new WeekFragment());
        fragments.add(new MonthFragment());
        fragments.add(new YearFragment());

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(FortuneDetailsActivity.this, fragments);
        //设置的预加载数量      ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT 禁用预加载
        mBinding.mViewpager.setOffscreenPageLimit(5);
        mBinding.mViewpager.setAdapter(adapter);
        mediator = new TabLayoutMediator(mBinding.mytab, mBinding.mViewpager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(title[position]);
            }
        });
        mediator.attach();
    }

    private void viewPagerTouch() {
        mBinding.mViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                //手指按下
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
//                //滑动动画完成
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                //手指离开
            }
        });
    }

    @Override
    protected void initData() {

    }
}