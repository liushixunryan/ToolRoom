package com.xql.constellation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.xql.arouter.ARouter;
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
    private final String[] constellation_name = {"水瓶", "双鱼", "白羊", "金牛", "双子", "巨蟹", "狮子", "处女", "天秤", "天蝎", "射手", "摩羯"};

    private String men;
    private String women;
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

        onClick();
    }

    private void onClick() {
        mBinding.pairMESpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //男方
                men = constellation_name[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mBinding.pairTASpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //女方
                women = constellation_name[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mBinding.searchBtn.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                Log.i(TAG, "onItemSelected: " + men + women);

                Bundle bundle = new Bundle();
                bundle.putString("men", men);
                bundle.putString("women", women);
                ARouter.getInstance().jumpActivity("querydetails/querydetails",bundle);

            }
        });
    }
}
