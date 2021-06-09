package com.xql.constellation.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.xql.basic.fragment.BaseFragment;
import com.xql.constellation.R;
import com.xql.constellation.bean.WeekBean;
import com.xql.constellation.databinding.ViewpagerWeekBinding;
import com.xql.constellation.vm.WeekVM;

/**
 * @ClassName: WeekFragment
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 9:57
 * @UpdateUser: RyanLiu
 */

public class WeekFragment extends BaseFragment<ViewpagerWeekBinding, WeekVM> {
    private String constellationname;
    @Override
    protected int layoutId() {
        return R.layout.viewpager_week;
    }

    @Override
    protected void initView(ViewpagerWeekBinding bindview) {
        Bundle bundle = getActivity().getIntent().getExtras();
        constellationname = bundle.getString("constellation_name");
    }

    @Override
    protected void initData(Context context) {
        showLoading();
        mViewModel.getWeek(constellationname).observe(getActivity(), new Observer<WeekBean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(WeekBean weekBean) {
                mBinding.nametv.setText(weekBean.getName() + "");
                mBinding.datetv.setText(weekBean.getDate() + "");
                mBinding.weekthtv.setText(weekBean.getWeekth() + "");
                mBinding.healthtv.setText(weekBean.getHealth() + "");
                mBinding.jobtv.setText(weekBean.getJob() + "");
                mBinding.lovetv.setText(weekBean.getLove() + "");
                mBinding.moneytv.setText(weekBean.getMoney() + "");

                hideLoading();
            }
        });
    }
}
