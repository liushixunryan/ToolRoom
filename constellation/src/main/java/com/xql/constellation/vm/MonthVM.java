package com.xql.constellation.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.ToastUtils;
import com.xql.basic.viewmodel.BaseViewModel;
import com.xql.common.network.RetrofitManager;
import com.xql.constellation.bean.MonthBean;
import com.xql.constellation.bean.WeekBean;
import com.xql.constellation.network.APIService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName: MonthVM
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 10:26
 * @UpdateUser: RyanLiu
 */

public class MonthVM extends BaseViewModel {
    /**
     * 月运势网络请求
     */
    public MutableLiveData<MonthBean> getMonth(String constellationname) {
        final MutableLiveData<MonthBean> liveData = new MutableLiveData<>();
        RetrofitManager.newInstance().creat(APIService.class).getMonth(constellationname).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MonthBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("sansuiban", "aaaaa: " + d);
            }

            @Override
            public void onNext(@NonNull MonthBean monthBean) {
                liveData.postValue(monthBean);
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
