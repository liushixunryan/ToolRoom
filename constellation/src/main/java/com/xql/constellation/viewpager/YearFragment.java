package com.xql.constellation.viewpager;

import android.content.Context;

import com.xql.basic.fragment.BaseFragment;
import com.xql.constellation.R;
import com.xql.constellation.databinding.ViewpagerYearBinding;
import com.xql.constellation.vm.YearVM;

/**
 * @ClassName: YearFragment
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 9:57
 * @UpdateUser: RyanLiu
 */

public class YearFragment extends BaseFragment<ViewpagerYearBinding, YearVM> {
    @Override
    protected int layoutId() {
        return R.layout.viewpager_year;
    }

    @Override
    protected void initView(ViewpagerYearBinding bindview) {

    }

    @Override
    protected void initData(Context context) {

    }
}
