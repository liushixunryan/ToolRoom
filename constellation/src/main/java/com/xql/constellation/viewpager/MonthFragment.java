package com.xql.constellation.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.xql.basic.fragment.BaseFragment;
import com.xql.constellation.R;
import com.xql.constellation.bean.MonthBean;
import com.xql.constellation.databinding.ViewpagerMonthBinding;
import com.xql.constellation.vm.MonthVM;

/**
 * @ClassName: MonthFragment
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 9:57
 * @UpdateUser: RyanLiu
 */

public class MonthFragment extends BaseFragment<ViewpagerMonthBinding, MonthVM> {
    private String constellationname;
    @Override
    protected int layoutId() {
        return R.layout.viewpager_month;
    }

    @Override
    protected void initView(ViewpagerMonthBinding bindview) {
        Bundle bundle = getActivity().getIntent().getExtras();
        constellationname = bundle.getString("constellation_name");
    }

    @Override
    protected void initData(Context context) {
        showLoading();
        mViewModel.getMonth(constellationname).observe(getActivity(), new Observer<MonthBean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(MonthBean monthBean) {
                mBinding.nametv.setText(monthBean.getName() + "");
                mBinding.datetv.setText(monthBean.getDate() +"");
                mBinding.monthtv.setText(monthBean.getMonth() + "");
                mBinding.healthtv.setText(monthBean.getHealth() + "");
                mBinding.worktv.setText(monthBean.getWork() + "");
                mBinding.lovetv.setText(monthBean.getLove() + "");
                mBinding.moneytv.setText(monthBean.getMoney() + "");
                mBinding.alltv.setText(monthBean.getAll() + "");

                hideLoading();
            }
        });
    }
}
