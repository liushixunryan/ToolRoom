package com.xql.constellation.fragment;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.xql.basic.fragment.BaseFragment;
import com.xql.constellation.R;
import com.xql.constellation.databinding.FragmentPairBinding;
import com.xql.constellation.vm.PairVM;

/**
 * @ClassName: PairFragment
 * @Description: java类作用描述
 * @CreateDate: 2021/6/7 15:16
 * @UpdateUser: RyanLiu
 */

public class PairFragment extends BaseFragment<FragmentPairBinding, PairVM> {
    private final String[] constellation_name = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};
    @Override
    protected int layoutId() {
        return R.layout.fragment_pair;
    }

    @Override
    protected void initView(FragmentPairBinding bindview) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_layout, constellation_name);
        mBinding.pairTASpinner.setAdapter(adapter);
        mBinding.pairMESpinner.setAdapter(adapter);
    }

    @Override
    protected void initData(Context context) {

    }
}
