package com.xql.news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.GsonUtils;
import com.xql.annotation.BindPath;
import com.xql.basic.activity.BaseActivity;
import com.xql.news.bean.NewDetailBean;
import com.xql.news.databinding.ActivityNewDetailBinding;
import com.xql.news.vm.NewDetailVM;

@BindPath(key = "newsdetail/newsdetail")
public class NewDetailActivity extends BaseActivity<ActivityNewDetailBinding, NewDetailVM> {
    private String uniquekey;

    @Override
    protected int layoutId() {
        return R.layout.activity_new_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        // 实例化一个Bundle
        Bundle bundle = intent.getExtras();
        uniquekey = bundle.getString("uniquekey");
    }

    @Override
    protected void initData() {
        mViewModel.getNewDetail(uniquekey).observe(this, new Observer<NewDetailBean>() {
            @Override
            public void onChanged(NewDetailBean newDetailBean) {
                String s = GsonUtils.toJson(newDetailBean);
                Log.e(TAG, "详情: " + s);
                mBinding.newsTitleTv.setText(newDetailBean.getResult().getDetail().getTitle());
                mBinding.newsTimeTv.setText(newDetailBean.getResult().getDetail().getDate());
                mBinding.newsSourceTv.setText(newDetailBean.getResult().getDetail().getAuthor_name());
                mBinding.newsContentWv.loadDataWithBaseURL(null,newDetailBean.getResult().getContent(),"text/html","utf-8",null);
            }
        });
    }
}