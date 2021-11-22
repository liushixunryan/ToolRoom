package com.xql.news.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.ToastUtils;
import com.xql.basic.viewmodel.BaseViewModel;
import com.xql.news.bean.NewDetailBean;
import com.xql.news.bean.NewsListBean;
import com.xql.news.network.APIService;
import com.xql.news.network.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName: NewDetailVM
 * @Description: 新闻详情页VM
 * @CreateDate: 2021/11/20 13:05
 * @UpdateUser: RyanLiu
 */

public class NewDetailVM extends BaseViewModel {
    /**
     * 获取新闻列表详情
     *
     * @param uniquekey 新闻详情id
     * @return
     */
    public MutableLiveData<NewDetailBean> getNewDetail(String uniquekey) {
        final MutableLiveData<NewDetailBean> liveData = new MutableLiveData<>();
        RetrofitManager.newInstance().creat(APIService.class).getNewDetail(uniquekey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<NewDetailBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("sansuiban", "onSubscribe: " + d);
            }

            @Override
            public void onNext(@NonNull NewDetailBean newDetailBean) {
                liveData.postValue(newDetailBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                ToastUtils.showLong("网络错误");
                Log.e("sansuiban", "onError" + e);
            }

            @Override
            public void onComplete() {
                Log.e("sansuiban", "onComplete");
            }
        });
        return liveData;
    }
}
