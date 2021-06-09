package com.xql.constellation.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.ToastUtils;
import com.xql.basic.viewmodel.BaseViewModel;
import com.xql.common.network.RetrofitManager;
import com.xql.constellation.bean.TodayBean;
import com.xql.constellation.network.APIService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName: TodayVM
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 10:16
 * @UpdateUser: RyanLiu
 */

public class TodayVM extends BaseViewModel {
    /**
     * 今日运势网络请求
     */
    public MutableLiveData<TodayBean> getToday(String constellationname) {
        final MutableLiveData<TodayBean> liveData = new MutableLiveData<>();
        RetrofitManager.newInstance().creat(APIService.class).getToday(constellationname).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TodayBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("sansuiban", "aaaaa: " + d);
            }

            @Override
            public void onNext(@NonNull TodayBean todayBean) {
                liveData.postValue(todayBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                ToastUtils.showLong("网络错误");
            }

            @Override
            public void onComplete() {
                Log.e("sansuiban", "bbbb: ");
            }
        });
        return liveData;
    }
}
