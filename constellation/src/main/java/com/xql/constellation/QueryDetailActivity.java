package com.xql.constellation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.xql.annotation.BindPath;
import com.xql.basic.activity.BaseActivity;
import com.xql.constellation.bean.PairBean;
import com.xql.constellation.databinding.ActivityQueryBinding;
import com.xql.constellation.vm.QueryDetailVM;

@BindPath(key = "querydetails/querydetails")
public class QueryDetailActivity extends BaseActivity<ActivityQueryBinding, QueryDetailVM> {

    private String men;
    private String women;
    @Override
    protected int layoutId() {
        return R.layout.activity_query;
    }

    @Override
    protected void initView() {
        mBinding.titleTv.setText("配对详情");
        mBinding.backImg.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();
        men = bundle.getString("men");
        women = bundle.getString("women");

    }

    @Override
    protected void initData() {
        showLoading();
        mViewModel.getPair(men,women).observe(QueryDetailActivity.this, new Observer<PairBean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(PairBean pairBean) {
                mBinding.mentv.setText(pairBean.getResult().getMen() + "座");
                mBinding.womentv.setText(pairBean.getResult().getWomen() + "座");
                mBinding.zhishutv.setText(pairBean.getResult().getZhishu() + "");
                mBinding.bizhongtv.setText(pairBean.getResult().getBizhong() + "");
                mBinding.xiangyuetv.setText(pairBean.getResult().getXiangyue() + "");
                mBinding.tcdjtv.setText(pairBean.getResult().getTcdj() + "");
                mBinding.jieguotv.setText(pairBean.getResult().getJieguo() + "");
                mBinding.lianaitv.setText(pairBean.getResult().getLianai() + "");
                mBinding.zhuyitv.setText(pairBean.getResult().getZhuyi() + "");
                hideLoading();
            }
        });

        onClick();
    }

    private void onClick() {
        mBinding.backImg.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                onBackPressed();
            }
        });
    }
}