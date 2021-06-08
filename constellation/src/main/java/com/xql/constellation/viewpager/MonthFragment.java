package com.xql.constellation.viewpager;

import android.content.Context;

import com.xql.basic.fragment.BaseFragment;
import com.xql.constellation.R;
import com.xql.constellation.databinding.ViewpagerMonthBinding;
import com.xql.constellation.vm.MonthVM;

/**
 * @ClassName: MonthFragment
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 9:57
 * @UpdateUser: RyanLiu
 */

public class MonthFragment extends BaseFragment<ViewpagerMonthBinding, MonthVM> {
    @Override
    protected int layoutId() {
        return R.layout.viewpager_month;
    }

    @Override
    protected void initView(ViewpagerMonthBinding bindview) {

    }

    @Override
    protected void initData(Context context) {

    }
}
