package com.xql.news.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.ToastUtils;
import com.xql.basic.viewmodel.BaseViewModel;
import com.xql.news.bean.NewsListBean;
import com.xql.news.network.APIService;
import com.xql.news.network.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName: NewsInformationVM
 * @Description: java类作用描述
 * @CreateDate: 2021/11/4 11:53
 * @UpdateUser: RyanLiu
 */

public class NewsInformationVM extends BaseViewModel {
    /**
     * 新闻列表
     */
    public MutableLiveData<NewsListBean> getNewsList(String type, int page) {
        final MutableLiveData<NewsListBean> liveData = new MutableLiveData<>();
        RetrofitManager.newInstance().creat(APIService.class).getNewsList(type, page, 30, 1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<NewsListBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("sansuiban", "onSubscribe: " + d);
            }

            @Override
            public void onNext(@NonNull NewsListBean newsListBean) {
                liveData.postValue(newsListBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                ToastUtils.showLong("网络错误");
                Log.e("sansuiban", "onError"+ e);
            }

            @Override
            public void onComplete() {
                Log.e("sansuiban", "onComplete");
            }
        });
        return liveData;
    }
}
