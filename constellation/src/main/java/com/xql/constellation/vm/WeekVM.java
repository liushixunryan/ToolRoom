package com.xql.constellation.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.ToastUtils;
import com.xql.basic.viewmodel.BaseViewModel;
import com.xql.common.network.RetrofitManager;
import com.xql.constellation.bean.TomorrowBean;
import com.xql.constellation.bean.WeekBean;
import com.xql.constellation.network.APIService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName: WeekVM
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 10:26
 * @UpdateUser: RyanLiu
 */

public class WeekVM extends BaseViewModel {
    /**
     * 本周运势网络请求
     */
    public MutableLiveData<WeekBean> getWeek(String constellationname) {
        final MutableLiveData<WeekBean> liveData = new MutableLiveData<>();
        RetrofitManager.newInstance().creat(APIService.class).getWeek(constellationname).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<WeekBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("sansuiban", "aaaaa: " + d);
            }

            @Override
            public void onNext(@NonNull WeekBean weekBean) {
                liveData.postValue(weekBean);
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
