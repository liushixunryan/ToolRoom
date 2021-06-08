package com.xql.constellation.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.Observer;

import com.xql.basic.fragment.BaseFragment;
import com.xql.constellation.R;
import com.xql.constellation.bean.TodayBean;
import com.xql.constellation.databinding.ViewpagerTodayBinding;
import com.xql.constellation.vm.TodayVM;

/**
 * @ClassName: TodayFragment
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 9:56
 * @UpdateUser: RyanLiu
 */

public class TodayFragment extends BaseFragment<ViewpagerTodayBinding, TodayVM> {
    private String constellationname;
    @Override
    protected int layoutId() {
        return R.layout.viewpager_today;
    }

    @Override
    protected void initView(ViewpagerTodayBinding bindview) {
        Bundle bundle = getActivity().getIntent().getExtras();
        constellationname = bundle.getString("constellation_name");
    }

    @Override
    protected void initData(Context context) {
        mViewModel.getToday(constellationname).observe(getActivity(), new Observer<TodayBean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(TodayBean todayBean) {
                mBinding.nametv.setText(todayBean.getName() + "");
                mBinding.datetimetv.setText(todayBean.getDatetime() + "");
                mBinding.numbertv.setText(todayBean.getNumber() + "");
                mBinding.QFriendtv.setText(todayBean.getQFriend() + "");
                mBinding.colortv.setText(todayBean.getColor() + "");
                mBinding.healthtv.setText(todayBean.getHealth() + "分");
                mBinding.lovetv.setText(todayBean.getLove() + "分");
                mBinding.moneytv.setText(todayBean.getMoney() + "分");
                mBinding.worktv.setText(todayBean.getWork() + "分");
                mBinding.alltv.setText(todayBean.getAll() + "分");
                mBinding.summarytv.setText(todayBean.getSummary() + "");
            }
        });
    }
}
