package com.xql.news.viewpager;

import android.content.Context;

import com.xql.basic.fragment.BaseFragment;
import com.xql.news.R;
import com.xql.news.databinding.ViewpagerNewslistLayoutBinding;
import com.xql.news.vm.NewsInformationVM;

/**
 * @ClassName: TJViewPager
 * @Description: java类作用描述
 * @CreateDate: 2021/11/4 16:12
 * @UpdateUser: RyanLiu
 */

public class TJViewPager extends BaseFragment<ViewpagerNewslistLayoutBinding, NewsInformationVM> {
    @Override
    protected int layoutId() {
        return R.layout.viewpager_newslist_layout;
    }

    @Override
    protected void initView(ViewpagerNewslistLayoutBinding bindview) {

    }

    @Override
    protected void initData(Context context) {

    }
}
