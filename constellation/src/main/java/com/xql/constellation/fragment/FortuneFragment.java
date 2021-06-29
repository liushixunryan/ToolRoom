package com.xql.constellation.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;

import com.xql.arouter.ARouter;
import com.xql.basic.fragment.BaseFragment;
import com.xql.constellation.R;
import com.xql.constellation.adapter.FortuneAdapter;
import com.xql.constellation.bean.FortuneBean;
import com.xql.constellation.databinding.FragmentFortuneBinding;
import com.xql.constellation.interfaces.IOnFortuneItemListener;
import com.xql.constellation.vm.FortuneVM;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: FortuneFragment
 * @Description: 星座类主页
 * @CreateDate: 2021/6/7 14:55
 * @UpdateUser: RyanLiu
 */

public class FortuneFragment extends BaseFragment<FragmentFortuneBinding, FortuneVM> {
    private FortuneAdapter mFortuneAdapter;
    private List<FortuneBean> mFortuneBeans;

    private final String[] constellation_date = {"1月20日-2月18日", "2月19日-3月20日", "3月21日-4月19日"
            , "4月20日-5月20日", "5月21日-6月21日", "6月22日-7月22日", "7月23日-8月22日"
            , "8月23日-9月22日", "9月23日-10月23日", "10月24日-11月22日", "11月23日-12月21日", "12月22日-1月19日"};
    private final int[] constellation_img = {R.mipmap.shuipingzuo, R.mipmap.shuangyuzuo, R.mipmap.baiyangzuo
            , R.mipmap.jinniuzuo, R.mipmap.shuangzizuo, R.mipmap.juxiezuo, R.mipmap.shizizuo, R.mipmap.chunvzuo
            , R.mipmap.tianpingzuo, R.mipmap.tianxiezuo, R.mipmap.sheshouzuo, R.mipmap.moxiezuo};
    private final String[] constellation_name = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};
    private final int[] constellation_ID = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    @Override
    protected int layoutId() {
        return R.layout.fragment_fortune;
    }

    @Override
    protected void initView(FragmentFortuneBinding bindview) {
        mFortuneBeans = new ArrayList<>();

        for (int i = 0; i < constellation_name.length; i++) {
            FortuneBean fortuneBean = new FortuneBean();
            fortuneBean.setConstellation_img(constellation_img[i]);
            fortuneBean.setConstellation_name(constellation_name[i]);
            fortuneBean.setConstellation_date(constellation_date[i]);
            fortuneBean.setConstellation_ID(constellation_ID[i]);
            mFortuneBeans.add(fortuneBean);
        }
    }

    @Override
    protected void initData(Context context) {
        mFortuneAdapter = new FortuneAdapter(getActivity(), mFortuneBeans,R.layout.item_fortune_layout);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        mBinding.constellationRv.setLayoutManager(layoutManager);
        mBinding.constellationRv.setAdapter(mFortuneAdapter);

        onClick();
    }

    private void onClick() {
        mFortuneAdapter.setOnItemClickListener(new IOnFortuneItemListener() {
            @Override
            public void OnFunctionItemClick(int position, int ID) {
                Bundle bundle = new Bundle();
                bundle.putString("constellation_name", constellation_name[position]);
                ARouter.getInstance().jumpActivity("fortunedetails/fortunedetails",bundle);
            }
        });
    }
}
