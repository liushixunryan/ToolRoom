package com.xql.constellation.viewpager;

import android.content.Context;

import com.xql.basic.fragment.BaseFragment;
import com.xql.constellation.R;
import com.xql.constellation.databinding.ViewpagerTomorrowBinding;
import com.xql.constellation.vm.TomorrowVM;

/**
 * @ClassName: TomorrowFragment
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 9:57
 * @UpdateUser: RyanLiu
 */

public class TomorrowFragment extends BaseFragment<ViewpagerTomorrowBinding, TomorrowVM> {
    @Override
    protected int layoutId() {
        return R.layout.viewpager_tomorrow;
    }

    @Override
    protected void initView(ViewpagerTomorrowBinding bindview) {

    }

    @Override
    protected void initData(Context context) {

    }
}
