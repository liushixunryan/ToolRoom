package com.xql.constellation.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.xql.basic.fragment.BaseFragment;
import com.xql.constellation.R;
import com.xql.constellation.bean.TomorrowBean;
import com.xql.constellation.databinding.ViewpagerTomorrowBinding;
import com.xql.constellation.vm.TomorrowVM;

/**
 * @ClassName: TomorrowFragment
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 9:57
 * @UpdateUser: RyanLiu
 */

public class TomorrowFragment extends BaseFragment<ViewpagerTomorrowBinding, TomorrowVM> {
    private String constellationname;
    @Override
    protected int layoutId() {
        return R.layout.viewpager_tomorrow;
    }

    @Override
    protected void initView(ViewpagerTomorrowBinding bindview) {
        Bundle bundle = getActivity().getIntent().getExtras();
        constellationname = bundle.getString("constellation_name");
    }

    @Override
    protected void initData(Context context) {
        showLoading();

        mViewModel.getTomorrow(constellationname).observe(getActivity(), new Observer<TomorrowBean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(TomorrowBean tomorrowBean) {
                mBinding.nametv.setText(tomorrowBean.getName() + "");
                mBinding.datetimetv.setText(tomorrowBean.getDatetime() + "");
                mBinding.numbertv.setText(tomorrowBean.getNumber() + "");
                mBinding.QFriendtv.setText(tomorrowBean.getQFriend() + "");
                mBinding.colortv.setText(tomorrowBean.getColor() + "");
                mBinding.healthtv.setText(tomorrowBean.getHealth() + "分");
                mBinding.lovetv.setText(tomorrowBean.getLove() + "分");
                mBinding.moneytv.setText(tomorrowBean.getMoney() + "分");
                mBinding.worktv.setText(tomorrowBean.getWork() + "分");
                mBinding.alltv.setText(tomorrowBean.getAll() + "分");
                mBinding.summarytv.setText(tomorrowBean.getSummary() + "");

                hideLoading();
            }
        });
    }
}
