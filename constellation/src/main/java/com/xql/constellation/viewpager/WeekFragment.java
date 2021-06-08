package com.xql.constellation.viewpager;

import android.content.Context;

import com.xql.basic.fragment.BaseFragment;
import com.xql.constellation.R;
import com.xql.constellation.databinding.ViewpagerWeekBinding;
import com.xql.constellation.vm.WeekVM;

/**
 * @ClassName: WeekFragment
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 9:57
 * @UpdateUser: RyanLiu
 */

public class WeekFragment extends BaseFragment<ViewpagerWeekBinding, WeekVM> {
    @Override
    protected int layoutId() {
        return R.layout.viewpager_week;
    }

    @Override
    protected void initView(ViewpagerWeekBinding bindview) {

    }

    @Override
    protected void initData(Context context) {

    }
}
