package com.xql.constellation;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.view.View;

import com.xql.annotation.BindPath;
import com.xql.basic.activity.BaseActivity;
import com.xql.constellation.fragment.FortuneFragment;
import com.xql.constellation.databinding.ActivityConstellationBinding;
import com.xql.constellation.fragment.PairFragment;
import com.xql.constellation.vm.ConstellationVM;

@BindPath(key = "constellation/constellation")
public class ConstellationActivity extends BaseActivity<ActivityConstellationBinding,ConstellationVM> {
    private FragmentManager manager;
    private FragmentTransaction fragmentTransaction;

    private FortuneFragment fortuneFragment;
    private PairFragment pairFragment;

    @Override
    protected int layoutId() {
        return R.layout.activity_constellation;
    }

    @Override
    protected void initView() {
        mBinding.titleTv.setText("星座模块");
        mBinding.backImg.setVisibility(View.VISIBLE);

        fortuneFragment = new FortuneFragment();
        pairFragment = new PairFragment();

        manager = getSupportFragmentManager();
        //设置默认的Fragment
        initSetNormalFragment();
    }

    /**
     * 设置默认的Fragment
     */

    private void initSetNormalFragment() {
        fragmentTransaction = manager.beginTransaction();
        if (!fortuneFragment.isAdded()) {
            fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.add(R.id.fl_layout, fortuneFragment);
            fragmentTransaction.commit();
        } else {
            fragmentTransaction.show(fortuneFragment).commit();
        }
    }

    @Override
    protected void initData() {
        onClick();
    }

    private void onClick() {
        mBinding.backImg.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                onBackPressed();
            }
        });
        mBinding.fortuneLl.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                fragmentTransaction = manager.beginTransaction();
                hidefragment();
                switchColor(true);
                if (!fortuneFragment.isAdded()) {
                    fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.add(R.id.fl_layout, fortuneFragment);
                    fragmentTransaction.commit();
                } else {
                    fragmentTransaction.show(fortuneFragment).commit();
                }
            }
        });

        mBinding.pairLl.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                fragmentTransaction = manager.beginTransaction();
                hidefragment();
                switchColor(false);
                if (!pairFragment.isAdded()) {
                    fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.add(R.id.fl_layout, pairFragment);
                    fragmentTransaction.commit();
                } else {
                    fragmentTransaction.show(pairFragment).commit();
                }
            }
        });

    }

    /**
     * 点击切换颜色方法
     */
    public void switchColor(Boolean select) {
        if (select) {
            mBinding.fortuneImg.setImageResource(R.mipmap.fortune_select_icon);
            mBinding.fortuneTv.setTextColor(Color.parseColor("#1296db"));
            mBinding.pairImg.setImageResource(R.mipmap.pair_icon);
            mBinding.pairTv.setTextColor(Color.parseColor("#707070"));
        } else {
            mBinding.fortuneImg.setImageResource(R.mipmap.fortune_icon);
            mBinding.fortuneTv.setTextColor(Color.parseColor("#707070"));
            mBinding.pairImg.setImageResource(R.mipmap.pair_select_icon);
            mBinding.pairTv.setTextColor(Color.parseColor("#1296db"));
        }
    }

    /**
     * 设置隐藏fragment
     */
    private void hidefragment() {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        //如果Fragment不为空并且已经添加,就隐藏
        if (fortuneFragment != null && fortuneFragment.isAdded()) {
            fragmentTransaction.hide(fortuneFragment);
        }
        if (pairFragment != null && pairFragment.isAdded()) {
            fragmentTransaction.hide(pairFragment);
        }
        //提交
        fragmentTransaction.commit();
    }
}