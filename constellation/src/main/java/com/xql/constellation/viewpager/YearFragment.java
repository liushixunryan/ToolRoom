package com.xql.constellation.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.xql.basic.fragment.BaseFragment;
import com.xql.constellation.R;
import com.xql.constellation.bean.YearBean;
import com.xql.constellation.databinding.ViewpagerYearBinding;
import com.xql.constellation.vm.YearVM;

/**
 * @ClassName: YearFragment
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 9:57
 * @UpdateUser: RyanLiu
 */

public class YearFragment extends BaseFragment<ViewpagerYearBinding, YearVM> {
    private String constellationname;
    @Override
    protected int layoutId() {
        return R.layout.viewpager_year;
    }

    @Override
    protected void initView(ViewpagerYearBinding bindview) {
        Bundle bundle = getActivity().getIntent().getExtras();
        constellationname = bundle.getString("constellation_name");
    }

    @Override
    protected void initData(Context context) {
        showLoading();
        mViewModel.getYear(constellationname).observe(getActivity(), new Observer<YearBean>() {
            @Override
            public void onChanged(YearBean yearBean) {
                mViewModel.getYear(constellationname).observe(getActivity(), new Observer<YearBean>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onChanged(YearBean yearBean) {
                        mBinding.nametv.setText(yearBean.getName() + "");
                        mBinding.datetv.setText(yearBean.getDate() + "");
                        mBinding.yearthtv.setText(yearBean.getYear() + "");
                        mBinding.luckeyStonetv.setText(yearBean.getLuckeyStone() + "");
                        mBinding.infotv.setText(yearBean.getMima().getInfo() + "");
                        mBinding.careertv.setText(yearBean.getCareer() + "");
                        mBinding.lovetv.setText(yearBean.getLove() + "");
                        mBinding.healthtv.setText(yearBean.getHealth() + "");
                        mBinding.financetv.setText(yearBean.getFinance() + "");
                        mBinding.texttv.setText(yearBean.getMima().getText() + "");
                    }
                });

                hideLoading();
            }
        });
    }
}
