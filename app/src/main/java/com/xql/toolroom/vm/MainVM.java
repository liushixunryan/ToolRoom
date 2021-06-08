package com.xql.toolroom.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xql.basic.viewmodel.BaseViewModel;
import com.xql.common.LiveDataBus;
import com.xql.toolroom.R;
import com.xql.toolroom.bean.FunctionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MainVM
 * @Description: MainActivity的MainVM
 * @CreateDate: 2021/6/4 14:41
 * @UpdateUser: RyanLiu
 */

public class MainVM extends BaseViewModel {

//
//    public MutableLiveData<FunctionBean> getFunction() {
//        mFunctionBean = new ArrayList<>();
//
//        MutableLiveData<List<FunctionBean>> liveData = new MutableLiveData<>();
//
//        liveData.postValue(mFunctionBean);
//        return null;
//    }

    public MutableLiveData<String> getString() {
        final LiveDataBus.BusMutableLiveData<String> liveData = LiveDataBus.getInstance().with("iswuliu", String.class);
        liveData.postValue("111");
        return liveData;
    }
}
